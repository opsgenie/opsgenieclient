package com.ifountain.opsgenie.client.marid.alert;

import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.script.util.ScriptProxy;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AlertActionUtils {
    public static final String MAPPED_ACTION_V2 = "mappedActionV2";
    private static Logger logger = Logger.getLogger(AlertActionUtils.class);

    public static void executeActionScript(AlertActionBean actionBean) throws Exception {
        Map params = actionBean.params;
        String mappedAction = "";
        if (params != null) {
            mappedAction = (String) ((Map) params.get(MAPPED_ACTION_V2)).get("name");
        }

        File scriptFile = getScriptFile(actionBean.action, mappedAction);
        if (scriptFile != null) {
            Map<String, Object> bindings = new HashMap<String, Object>();
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT, actionBean.alertProps);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE, actionBean.source);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION, actionBean.action);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS, actionBean.params);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_MAPPED_ACTION, mappedAction);
            Properties  maridConfProps = new Properties();
            if(MaridConfig.getInstance().getConfiguration() != null){
                maridConfProps.putAll(MaridConfig.getInstance().getConfiguration());
            }
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF, maridConfProps);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT, new ScriptProxy(MaridConfig.getInstance().getApiClient()));
            ScriptManager.getInstance().runScript(scriptFile.getName(), bindings);
        } else {
            throw new Exception("No script file found for action [" + actionBean.action + "]");
        }
    }

    public static File getScriptFile(String action, String mappedAction) {
        String safeFileName;
        String propertyKey;
        if (mappedAction != null && !"".equals(mappedAction)) {
            safeFileName = mappedAction.replaceAll("\\W", "");
            propertyKey = ("mappedActions." + safeFileName + ".script").toLowerCase();
        } else {
            safeFileName = action.replaceAll("\\W", "");
            propertyKey = ("actions." + safeFileName + ".script").toLowerCase();
        }

        String fileName = MaridConfig.getInstance().getLowercasedConfiguration().get(propertyKey);
        File scriptsDirectory = ScriptManager.getInstance().getScriptsDirectory();
        if(fileName == null){
            fileName = safeFileName;
            File[] files = scriptsDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (StringUtils.substringBeforeLast(file.getName(), ".").equalsIgnoreCase(fileName)) {
                        return file;
                    }
                }
            }
        }
        else{
            File scriptFile = new File(scriptsDirectory, fileName);
            if(scriptFile.exists()){
                return scriptFile;
            }
        }

        return null;
    }

    public static class AlertActionBean {
        public static final String ACTION = "action";
        public static final String ALERT = "alert";
        public static final String SOURCE = "source";
        public static final String SOURCES = "sources";
        public static final String PARAMS = "params";
        public String action;
        public Map params;
        public Map source;
        public Map alertProps;
        public String alertId;
        public String username;

        public AlertActionBean(String action, Map alertProps, Map source) {
            this.action = action;
            this.source = source;
            this.alertProps = alertProps;
            alertId = (String) this.alertProps.get("alertId");
            username = (String) this.alertProps.get("username");
        }

        public AlertActionBean(Map params) {
            this.params = params;
        }

        public static AlertActionBean createAlertAction(String jsonMessage) throws Exception {
            Map jsonMessageMap = null;
            try {
                jsonMessageMap = JsonUtils.parse(jsonMessage);
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not parse message:"+jsonMessage);
            }
            return createAlertAction(jsonMessageMap);
        }

        public static AlertActionBean createAlertAction(Map jsonMessageMap) throws Exception {
            String action = (String) jsonMessageMap.get(ACTION);
            Object alert = jsonMessageMap.get(ALERT);
            Object source = jsonMessageMap.get(SOURCE);
            try{
                return createAlertAction(action, alert, source, null);
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage()+" Ignoring message :"+jsonMessageMap, ex);
            }
        }

        public static AlertActionBean createAlertAction(JSONObject jsonMessageMap) throws Exception {
            String action=null;
            String alert = null;
            String source = null;
            String params = null;
            if(jsonMessageMap.has(ACTION))
            action = (String) jsonMessageMap.get(ACTION);
            if(jsonMessageMap.has(ALERT))
            alert = (String) jsonMessageMap.get(ALERT);
            if(jsonMessageMap.has(SOURCE))
                source = (String) jsonMessageMap.get(SOURCE);
            if (jsonMessageMap.has(PARAMS))
                params = (String) jsonMessageMap.get(PARAMS);
            try{
                return createAlertAction(action, alert, source, params);
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage()+" Ignoring message :"+jsonMessageMap, ex);
            }
        }

        public static AlertActionBean createAlertAction(String action, Object alert, Object source, Object params) throws Exception {
            if (params == null) {
                Map sourceMap = null;
                if (action == null) {
                    throw new NoActionSpecifiedException();
                }
                if (alert == null) {
                    throw new IllegalArgumentException("No alert specified.");
                }
                Map alertMap;
                try {
                    if (alert instanceof Map) {
                        alertMap = (Map) alert;
                    } else {
                        alertMap = JsonUtils.parse(String.valueOf(alert));
                    }
                    if (source != null) {
                        //List type source is because of backward compatability
                        if (source instanceof Map) {
                            sourceMap = (Map) source;
                        } else {
                            sourceMap = JsonUtils.parse(String.valueOf(source));
                        }
                    }
                } catch (IOException e) {
                    throw new IllegalArgumentException("Could not parse alert content.", e);
                }
                return new AlertActionBean(action, alertMap, sourceMap);
            } else {
                Map integrationParams;
                if (params instanceof Map) {
                    integrationParams = (Map) params;
                } else {
                    integrationParams = JsonUtils.parse(String.valueOf(params));
                }
                return new AlertActionBean(integrationParams);
            }
        }
    }
}
