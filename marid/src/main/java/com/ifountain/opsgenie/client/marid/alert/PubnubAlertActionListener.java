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
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private int actionExecutorThreadCount = 20;
    private long shutdownWaitTime = 90000;
    private ExecutorService actionExecutionService;
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
        logger.info("Starting action executor service with " + actionExecutorThreadCount + " number of threads.");
        actionExecutionService = Executors.newFixedThreadPool(actionExecutorThreadCount);
        subscribeToOpsGenie(params);
    }

    private void subscribeToOpsGenie(final PubnubChannelParameters params) {
        logger.debug(getLogPrefix() + "Subscribing to OpsGenie.");
        this.pubnubChannelParameters = params;
        pubnub = new Pubnub(params.getPublishKey(), params.getSubscribeKey(), params.getSecretKey(), params.getCipherKey(), params.isSslOn()) {
            @Override
            protected AsyncHttpClientConfig.Builder createHttpClientBuilder() {
                AsyncHttpClientConfig.Builder builder = super.createHttpClientBuilder();
                if (params.isProxyEnabled()) {
                    ProxyServer proxyServer;
                    if (params.getProxyProtocol() == null) {
                        proxyServer = new ProxyServer(params.getProxyHost(), params.getProxyPort(), params.getProxyUsername(), params.getProxyPassword());
                    } else {
                        ProxyServer.Protocol protocol = ProxyServer.Protocol.valueOf(params.getProxyProtocol().toUpperCase());
                        proxyServer = new ProxyServer(protocol, params.getProxyHost(), params.getProxyPort(), params.getProxyUsername(), params.getProxyPassword());
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
                    public boolean subscribeCallback(final String s, final Object o) {
                        actionExecutionService.submit(new Runnable() {
                            @Override
                            public void run() {
                                processMessage(o);
                            }
                        });
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

    public long getShutdownWaitTime() {
        return shutdownWaitTime;
    }

    public void setShutdownWaitTime(long shutdownWaitTime) {
        this.shutdownWaitTime = shutdownWaitTime;
    }

    public int getActionExecutorThreadCount() {
        return actionExecutorThreadCount;
    }

    public void setActionExecutorThreadCount(int actionExecutorThreadCount) {
        this.actionExecutorThreadCount = actionExecutorThreadCount;
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
                    sendResultToOpsGenie(actionBean.action, actionBean.alertId, actionBean.params, null);
                } catch (Exception e) {
                    logger.warn(getLogPrefix() + "Could not process message " + jsonMessage + "Reason: " + e.getMessage());
                    sendResultToOpsGenie(actionBean.action, actionBean.alertId, actionBean.params, e.getMessage());
                }
            } catch (Exception ex) {
                logger.error(getLogPrefix() + ex.getMessage(), ex);
            }

        } else {
            logger.warn(getLogPrefix() + "Unexpected message content: " + message);
        }
    }

    private void sendResultToOpsGenie(String action, String alertId, Map params, String failureMessage) {
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        if (params != null) {
            String mappedAction = (String) ((Map) params.get("mappedActionV2")).get("name");
            logger.debug(getLogPrefix() + "Sending result to OpsGenie for action: " + mappedAction);
            parameters.add(new BasicNameValuePair("mappedAction", mappedAction));

            alertId = (String) params.get("alertId");
        } else {
            logger.debug(getLogPrefix() + "Sending result to OpsGenie for action: " + action);
            parameters.add(new BasicNameValuePair("alertAction", action));
        }
        parameters.add(new BasicNameValuePair("apiKey", MaridConfig.getInstance().getApiKey()));
        boolean success = failureMessage == null;
        parameters.add(new BasicNameValuePair("success", String.valueOf(success)));
        parameters.add(new BasicNameValuePair("alertId", alertId));
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
        if (actionExecutionService != null) {
            actionExecutionService.shutdown();
            try {
                logger.info("Shuttingdown action execution service");
                actionExecutionService.awaitTermination(shutdownWaitTime, TimeUnit.MILLISECONDS);
                if (!actionExecutionService.isTerminated()) {
                    actionExecutionService.shutdownNow();
                }
                logger.info("Shutdown action execution service");
            } catch (InterruptedException e) {
                logger.info("Could not shutdown action execution service in " + shutdownWaitTime + " msecs.");
                actionExecutionService.shutdownNow();
            }
        }
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
