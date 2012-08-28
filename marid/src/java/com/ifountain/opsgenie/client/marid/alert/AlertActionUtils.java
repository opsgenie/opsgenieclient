package com.ifountain.opsgenie.client.marid.alert;

import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 8/27/12
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlertActionUtils {
    public static void executeActionScript(AlertActionBean actionBean) throws Exception {
        File scriptFile = getScriptFile(actionBean.action);
        if (scriptFile != null) {
            Map<String, Object> bindings = new HashMap<String, Object>();
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT, actionBean.alertProps);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION, actionBean.action);
            Properties  maridConfProps = new Properties();
            if(MaridConfig.getInstance().getConfiguration() != null){
                maridConfProps.putAll(MaridConfig.getInstance().getConfiguration());
            }
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF, maridConfProps);
            bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT, MaridConfig.getInstance().getOpsGenieClient());
            ScriptManager.getInstance().runScript(scriptFile.getName(), bindings);
        } else {
            throw new Exception("No script file found for action [" + actionBean.action + "]");
        }
    }

    public static File getScriptFile(String action) {
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

    public static class AlertActionBean {
        public static final String ACTION = "action";
        public static final String ALERT = "alert";
        public String action;
        public Map alertProps;
        public String alertId;
        public String username;

        public AlertActionBean(String action, Map alertProps) {
            this.action = action;
            this.alertProps = alertProps;
            alertId = (String) this.alertProps.get("alertId");
            username = (String) this.alertProps.get("username");
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
            String alert = (String) jsonMessageMap.get(ALERT);
            try{
                return createAlertAction(action, alert);
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage()+" Ignoring message :"+jsonMessageMap);
            }
        }

        public static AlertActionBean createAlertAction(JSONObject jsonMessageMap) throws Exception {
            String action=null;
            String alert = null;
            if(jsonMessageMap.has(ACTION))
            action = (String) jsonMessageMap.get(ACTION);
            if(jsonMessageMap.has(ALERT))
            alert = (String) jsonMessageMap.get(ALERT);
            try{
                return createAlertAction(action, alert);
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage()+" Ignoring message :"+jsonMessageMap);
            }
        }

        public static AlertActionBean createAlertAction(String action, String alert) throws Exception {
            if(action == null){
                throw new IllegalArgumentException("No action specified.");
            }
            if(alert == null){
                throw new IllegalArgumentException("No alert specified.");
            }
            try {
                Map alertMap = JsonUtils.parse(alert);
                return new AlertActionBean(action, alertMap);
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not parse alert content.");
            }
        }
    }
}
