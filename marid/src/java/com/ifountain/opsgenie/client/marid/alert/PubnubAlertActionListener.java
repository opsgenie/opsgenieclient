package com.ifountain.opsgenie.client.marid.alert;

import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ProxyServer;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import pubnub.api.Callback;
import pubnub.api.Pubnub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/6/12 11:45 AM
 */
public class PubnubAlertActionListener {
    private static PubnubAlertActionListener pubnubAlertActionListener;

    private PubnubAlertActionListener() {
    }

    private Logger logger = Logger.getLogger(PubnubAlertActionListener.class);
    private Pubnub pubnub;
    private PubnubChannelParameters pubnubChannelParameters;
    private Thread pubnubSubscribeThread;
    private boolean isSubscribed = false;
    private StringBuffer lastErrorMessage = new StringBuffer();

    public static PubnubAlertActionListener getInstance() {
        if (pubnubAlertActionListener == null) {
            pubnubAlertActionListener = new PubnubAlertActionListener();
        }
        return pubnubAlertActionListener;
    }

    public static void destroyInstance() {
        if (pubnubAlertActionListener != null) {
            pubnubAlertActionListener.destroy();
        }
        pubnubAlertActionListener = null;
    }

    public void initialize(PubnubChannelParameters params) {
        subscribeToOpsGenie(params);
    }

    private void subscribeToOpsGenie(final PubnubChannelParameters params) {
        logger.debug(getLogPrefix() + "Subscribing to OpsGenie.");
        this.pubnubChannelParameters = params;
        pubnub = new Pubnub(params.getPublishKey(), params.getSubscribeKey(), params.getSecretKey(), params.getCipherKey(), params.isSslOn()){
            @Override
            protected AsyncHttpClientConfig.Builder createHttpClientBuilder() {
                AsyncHttpClientConfig.Builder builder = super.createHttpClientBuilder();
                if(params.isProxyEnabled()){
                    ProxyServer proxyServer;
                    if(params.getProxyProtocol() == null){
                        proxyServer = new ProxyServer(params.getProxyHost(), params.getProxyPort(), params.getProxyUsername(), params.getProxyPassword());
                    }
                    else{
                        proxyServer = new ProxyServer(ProxyServer.Protocol.valueOf(params.getProxyProtocol()), params.getProxyHost(), params.getProxyPort(), params.getProxyUsername(), params.getProxyPassword());
                    }
                    builder.setProxyServer(proxyServer);
                }
                return builder;
            }
        };
        pubnubSubscribeThread = new Thread() {
            @Override
            public void run() {
                pubnub.subscribe(params.getChannel(), new Callback() {
                    @Override
                    public boolean presenceCallback(String s, Object o) {
                        return false;
                    }

                    @Override
                    public boolean subscribeCallback(String s, Object o) {
                        processMessage(o);
                        return true;
                    }

                    @Override
                    public void errorCallback(String channel, Object message) {
                        logger.warn(getLogPrefix() + "Error occurred on channel [" + channel + "]: " + message.toString());
                        lastErrorMessage.delete(0, lastErrorMessage.length());
                        lastErrorMessage.append(message);
                    }

                    @Override
                    public void connectCallback(String channel) {
                        isSubscribed = true;
                        logger.info(getLogPrefix() + "Connected to channel: " + channel);
                    }

                    @Override
                    public void reconnectCallback(String channel) {
                        logger.info(getLogPrefix() + "Re-connectting to channel: " + channel);
                    }

                    @Override
                    public void disconnectCallback(String channel) {
                        isSubscribed = false;
                        logger.info(getLogPrefix() + "Disconnected from channel: " + channel);
                    }
                });
            }
        };
        pubnubSubscribeThread.start();
    }

    public String getLastErrorMessage() {
        return lastErrorMessage.toString();
    }


    protected void processMessage(Object message) {
        if (message instanceof JSONObject) {
            JSONObject jsonMessage = ((JSONObject) message);
            if (logger.isDebugEnabled()) {
                logger.debug(getLogPrefix() + "Processing message: " + jsonMessage.toString());
            }
            try {
                AlertActionUtils.AlertActionBean actionBean = AlertActionUtils.AlertActionBean.createAlertAction(jsonMessage);
                try {
                    AlertActionUtils.executeActionScript(actionBean);
                    sendResultToOpsGenie(actionBean.action, actionBean.alertId, actionBean.username, null);
                } catch (Exception e) {
                    logger.warn(getLogPrefix() + "Could not process message " + jsonMessage + "Reason: " + e.getMessage());
                    sendResultToOpsGenie(actionBean.action, actionBean.alertId, actionBean.username, e.getMessage());
                }
            } catch (Exception ex) {
                logger.warn(getLogPrefix() + ex.getMessage());
            }

        } else {
            logger.warn(getLogPrefix() + "Unexpected message content: " + message);
        }
    }

    private void sendResultToOpsGenie(String action, String alertId, String username, String failureMessage) {
        logger.debug(getLogPrefix() + "Sending result to OpsGenie for action: " + action);
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("customerKey", MaridConfig.getInstance().getCustomerKey()));
        parameters.add(new BasicNameValuePair("alertAction", action));
        boolean success = failureMessage == null;
        parameters.add(new BasicNameValuePair("success", String.valueOf(success)));
        if (alertId != null) parameters.add(new BasicNameValuePair("alertId", alertId));
        if (username != null) parameters.add(new BasicNameValuePair("username", username));
        if (!success) parameters.add(new BasicNameValuePair("failureMessage", failureMessage));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
            OpsGenieHttpResponse response = MaridConfig.getInstance().getOpsGenieHttpClient().post(MaridConfig.getInstance().getOpsgenieApiUrl() + "/v1/json/marid/actionExecutionResult", entity);
            if (response.getStatusCode() != HttpStatus.SC_OK) {
                String logSuffix = "";
                if (response.getContent() != null && response.getContent().length > 0) {
                    logSuffix = ", Content: " + new String(response.getContent());
                }
                logger.warn(getLogPrefix() + "Could not send action result to OpsGenie. HttpStatus: " + response.getStatusCode() + logSuffix);
            } else {
                logger.info(getLogPrefix() + "Successfully sent result to OpsGenie.");
            }
        } catch (IOException e) {
            logger.warn(getLogPrefix() + "Could not send action result to OpsGenie. Reason: " + e.getMessage());
        }
    }

    private void destroy() {
        unsubscribe();
    }

    private void unsubscribe() {
        if (pubnub != null) {
            logger.debug(getLogPrefix() + "Unsubscribing from OpsGenie.");
            HashMap<String, Object> args = new HashMap<String, Object>();
            args.put("channel", pubnubChannelParameters.getChannel());
            pubnub.unsubscribe(args);
            try {
                pubnubSubscribeThread.join(60 * 1000L);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private String getLogPrefix() {
        return "[PubnubAlertActionListener]: ";
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
