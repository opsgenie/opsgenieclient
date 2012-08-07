package com.ifountain.opsgenie.client.marid.alert;

import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import pubnub.Callback;
import pubnub.Pubnub;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/6/12 11:45 AM
 */
public class AlertActionExecutor {
    private static AlertActionExecutor alertActionExecutor;

    private AlertActionExecutor() {
    }

    private Logger logger = Logger.getLogger(AlertActionExecutor.class);
    private Pubnub pubnub;
    private PubnubChannelParameters pubnubChannelParameters;
    private Thread pubnubSubscribeThread;
    private boolean isSubscribed = false;

    public static AlertActionExecutor getInstance() {
        if (alertActionExecutor == null) {
            alertActionExecutor = new AlertActionExecutor();
        }
        return alertActionExecutor;
    }

    public static void destroyInstance() {
        if (alertActionExecutor != null) {
            alertActionExecutor.destroy();
        }
        alertActionExecutor = null;
    }

    public void initialize(PubnubChannelParameters params) {
        subscribeToOpsGenie(params);
    }

    private void subscribeToOpsGenie(final PubnubChannelParameters params) {
        logger.debug(getLogPrefix() + "Subscribing to OpsGenie.");
        this.pubnubChannelParameters = params;
        pubnub = new Pubnub(params.getPublishKey(), params.getSubscribeKey(), params.getSecretKey(), params.getCipherKey(), params.isSslOn());
        pubnubSubscribeThread = new Thread() {
            @Override
            public void run() {
                pubnub.subscribe(params.getChannel(), new Callback() {
                    @Override
                    public boolean subscribeCallback(String s, Object o) {
                        processMessage(o);
                        return true;
                    }

                    @Override
                    public void errorCallback(String channel, Object message) {
                        logger.warn(getLogPrefix() + "Error occurred on channel [" + channel + "]: " + message.toString());
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

    protected void processMessage(Object message) {
        if (message instanceof JSONObject) {
            JSONObject jsonMessage = ((JSONObject) message);
            if(logger.isDebugEnabled()){
                logger.debug(getLogPrefix() + "Processing message: " + jsonMessage.toString());
            }
            try {
                if (!jsonMessage.isNull("action")) {
                    String action = (String) jsonMessage.get("action");
                    File scriptFile = getScriptFile(action);
                    if (scriptFile != null) {
                        if (!jsonMessage.isNull("alert")) {
                            String alertJson = (String) jsonMessage.get("alert");
                            Map alert = JsonUtils.parse(alertJson);
                            Map<String, Object> bindings = new HashMap<String, Object>();
                            bindings.put("alert", alert);
                            ScriptManager.getInstance().runScript(scriptFile.getName(), bindings);
                        } else {
                            logger.warn(getLogPrefix() + "No alert specified. Ignoring message: " + jsonMessage.toString());
                        }
                    } else {
                        logger.warn(getLogPrefix() + "No script file found for action: " + action);
                    }

                } else {
                    logger.warn(getLogPrefix() + "No action specified. Ignoring message: " + jsonMessage.toString());
                }
            } catch (Exception e) {
                logger.warn(getLogPrefix() + "Could not process message " + jsonMessage + ". Reason: " + e.getMessage());
            }
        } else {
            logger.warn(getLogPrefix() + "Unexpected message content: " + message);
        }
    }

    private File getScriptFile(String action) {
        String fileName = action.replaceAll("\\s", "");
        File scriptsDirectory = ScriptManager.getInstance().getScriptsDirectory();
        File[] files = scriptsDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (StringUtils.substringBeforeLast(file.getName(), ".").equalsIgnoreCase(fileName)) {
                    return file;
                }
            }
        }
        return null;
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
        return "[AlertActionExecutor]: ";
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
