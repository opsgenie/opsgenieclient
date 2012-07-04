package com.ifountain.opsgenie.client.cli.utils;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.ExecuteScriptCommand;
import com.ifountain.opsgenie.client.model.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 6/18/12
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class OpsGenieClientScriptingProxy {
    IOpsGenieClient opsGenieClient;
    final CommonCommandOptions options;
    public OpsGenieClientScriptingProxy(IOpsGenieClient opsGenieClient, CommonCommandOptions options){
        this.opsGenieClient = opsGenieClient;
        this.options = options;
    }
    
    public Map createAlert(Map params) throws Exception {
        populateCommonProps(params);
        CreateAlertRequest request = new CreateAlertRequest();
        request.setActions(OpsGenieClientScriptingBridgeUtils.getAsList(params, OpsGenieClientConstants.API.ACTIONS));
        request.setTags(OpsGenieClientScriptingBridgeUtils.getAsList(params, OpsGenieClientConstants.API.TAGS));
        request.setRecipients(OpsGenieClientScriptingBridgeUtils.getAsList(params, OpsGenieClientConstants.API.RECIPIENTS));
        request.setDetails(OpsGenieClientScriptingBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        request.setMessage(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        request.setDescription(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setAlias(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setEntity(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        request.setNote(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setUser(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        CreateAlertResponse resp = this.opsGenieClient.createAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ALERT_ID, resp.getAlertId());
        return mapResponse;
    }

    public Map closeAlert(Map params) throws Exception{
        populateCommonProps(params);
        CloseAlertRequest request = new CloseAlertRequest();
        request.setAlertId(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setUser(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        CloseAlertResponse resp = this.opsGenieClient.closeAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }

    public Map attach(Map params) throws Exception{
        populateCommonProps(params);
        AttachResponse resp;
        if(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT) != null){
            FileAttachRequest fileAttachRequest = new FileAttachRequest();
            String attachmentFilePath = OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT);
            fileAttachRequest.setFile(new File(attachmentFilePath));
            populateAttachmentRequestCommonProps(fileAttachRequest, params);
            resp = this.opsGenieClient.attach(fileAttachRequest);
        }
        else{
            InputStreamAttachRequest inputStreamAttachRequest = new InputStreamAttachRequest();
            InputStream inputStream = (InputStream) params.get(OpsGenieClientConstants.ScriptProxy.INPUT_STREAM);
            String fileName = OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.ScriptProxy.FILE_NAME);
            inputStreamAttachRequest.setInputStream(inputStream);
            inputStreamAttachRequest.setFileName(fileName);
            populateAttachmentRequestCommonProps(inputStreamAttachRequest, params);
            resp = this.opsGenieClient.attach(inputStreamAttachRequest);
        }
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }
    private void populateAttachmentRequestCommonProps(AttachRequest request, Map params){
        request.setAlertId(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setUser(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setIndexFile(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
    }

    
    public Map addNote(Map params) throws Exception{
        populateCommonProps(params);
        AddNoteRequest request = new AddNoteRequest();
        request.setAlertId(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setNote(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddNoteResponse resp = this.opsGenieClient.addNote(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }

    
    public void executeScript(String script, Map params) throws Exception {
        executeScript(script, params, null);
    }
    public void executeScript(String script, Map params, Map config) throws Exception {
        CommonCommandOptions defaultOptions = options;
        if(config != null){
            ArrayList<String> user = new ArrayList<String>();
            user.add((String) config.get("user"));
            defaultOptions = new CommonCommandOptions();
            defaultOptions.setCustomerKey((String) config.get("customerKey"));
            defaultOptions.setUser(user);
        }
        ExecuteScriptCommand.executeScript(script, opsGenieClient, defaultOptions, params);

    }

    
    public Map getAlert(Map params) throws Exception{
        populateCommonProps(params);
        GetAlertRequest request = new GetAlertRequest();
        request.setAlertId(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        GetAlertResponse resp = this.opsGenieClient.getAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put( OpsGenieClientConstants.API.ACTIONS, resp.getActions());
        mapResponse.put( OpsGenieClientConstants.API.MESSAGE, resp.getMessage());
        mapResponse.put( OpsGenieClientConstants.API.ALERT_ID, resp.getId());
        mapResponse.put( OpsGenieClientConstants.API.ALIAS, resp.getAlias());
        mapResponse.put( OpsGenieClientConstants.API.TAGS, resp.getTags());
        mapResponse.put( OpsGenieClientConstants.API.CREATED_AT, resp.getCreatedAt());
        mapResponse.put( OpsGenieClientConstants.API.DESCRIPTION, resp.getDescription());
        mapResponse.put( OpsGenieClientConstants.API.DETAILS, resp.getDetails());
        mapResponse.put( OpsGenieClientConstants.API.RECIPIENTS, resp.getRecipients());
        mapResponse.put( OpsGenieClientConstants.API.SOURCE, resp.getSource());
        mapResponse.put( OpsGenieClientConstants.API.ENTITY, resp.getEntity());
        mapResponse.put( OpsGenieClientConstants.API.STATUS, resp.getStatus());
        return mapResponse;
    }

    
    public Map heartbeat(Map params) throws Exception{
        populateCommonProps(params);
        HeartbeatRequest request = new HeartbeatRequest();
        request.setCustomerKey(OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        HeartbeatResponse resp = this.opsGenieClient.heartbeat(request);
        Map mapResponse = new HashMap();
        mapResponse.put("heartbeat", resp.getHeartbeat());
        return mapResponse;
    }

    private void populateCommonProps(Map params){
        populateCommonProps(options, params);
    }
    private void populateCommonProps(CommonCommandOptions options, Map params){
        String customerKey = OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY);
        if(customerKey == null){
            params.put(OpsGenieClientConstants.API.CUSTOMER_KEY, options.getCustomerKey());
        }
        String user = OpsGenieClientScriptingBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER);
        if(user == null){
            params.put(OpsGenieClientConstants.API.USER, options.getUser());
        }
    }
}
