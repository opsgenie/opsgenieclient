package com.ifountain.opsgenie.client.script.util;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 6/18/12
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptProxy {
    IOpsGenieClient opsGenieClient;
    final String customerKey;
    public ScriptProxy(IOpsGenieClient opsGenieClient, String customerKey){
        this.opsGenieClient = opsGenieClient;
        this.customerKey = customerKey;
    }
    
    public Map createAlert(Map params) throws Exception {
        populateCommonProps(params);
        CreateAlertRequest request = new CreateAlertRequest();
        request.setActions(ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.ACTIONS));
        request.setTags(ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.TAGS));
        request.setRecipients(ScriptBridgeUtils.getAsList(params, OpsGenieClientConstants.API.RECIPIENTS));
        request.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        request.setMessage(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setEntity(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        CreateAlertResponse resp = this.opsGenieClient.createAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ALERT_ID, resp.getAlertId());
        return mapResponse;
    }

    public Map closeAlert(Map params) throws Exception{
        populateCommonProps(params);
        CloseAlertRequest request = new CloseAlertRequest();
        request.setAlertId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        CloseAlertResponse resp = this.opsGenieClient.closeAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }

    public Map attach(Map params) throws Exception{
        populateCommonProps(params);
        AttachResponse resp;
        if(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT) != null){
            FileAttachRequest fileAttachRequest = new FileAttachRequest();
            String attachmentFilePath = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT);
            fileAttachRequest.setFile(new File(attachmentFilePath));
            populateAttachmentRequestCommonProps(fileAttachRequest, params);
            resp = this.opsGenieClient.attach(fileAttachRequest);
        }
        else{
            InputStreamAttachRequest inputStreamAttachRequest = new InputStreamAttachRequest();
            InputStream inputStream = (InputStream) params.get(OpsgenieClientApplicationConstants.ScriptProxy.INPUT_STREAM);
            String fileName = ScriptBridgeUtils.getAsString(params, OpsgenieClientApplicationConstants.ScriptProxy.FILE_NAME);
            inputStreamAttachRequest.setInputStream(inputStream);
            inputStreamAttachRequest.setFileName(fileName);
            populateAttachmentRequestCommonProps(inputStreamAttachRequest, params);
            resp = this.opsGenieClient.attach(inputStreamAttachRequest);
        }
        Map mapResponse = new HashMap();
        mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }
    private void populateAttachmentRequestCommonProps(AttachRequest request, Map params){
        request.setAlertId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
    }

    
    public Map addNote(Map params) throws Exception{
        populateCommonProps(params);
        AddNoteRequest request = new AddNoteRequest();
        request.setAlertId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddNoteResponse resp = this.opsGenieClient.addNote(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, resp.isSuccess());
        return mapResponse;
    }

    public Map getAlert(Map params) throws Exception{
        populateCommonProps(params);
        GetAlertRequest request = new GetAlertRequest();
        request.setAlertId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        GetAlertResponse resp = this.opsGenieClient.getAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put( OpsGenieClientConstants.API.ACTIONS, resp.getAlert().getActions());
        mapResponse.put( OpsGenieClientConstants.API.MESSAGE, resp.getAlert().getMessage());
        mapResponse.put( OpsGenieClientConstants.API.ALERT_ID, resp.getAlert().getId());
        mapResponse.put( OpsGenieClientConstants.API.ALIAS, resp.getAlert().getAlias());
        mapResponse.put( OpsGenieClientConstants.API.TAGS, resp.getAlert().getTags());
        mapResponse.put( OpsGenieClientConstants.API.CREATED_AT, resp.getAlert().getCreatedAt());
        mapResponse.put( OpsGenieClientConstants.API.DESCRIPTION, resp.getAlert().getDescription());
        mapResponse.put( OpsGenieClientConstants.API.DETAILS, resp.getAlert().getDetails());
        mapResponse.put( OpsGenieClientConstants.API.RECIPIENTS, resp.getAlert().getRecipients());
        mapResponse.put( OpsGenieClientConstants.API.SOURCE, resp.getAlert().getSource());
        mapResponse.put( OpsGenieClientConstants.API.ENTITY, resp.getAlert().getEntity());
        mapResponse.put( OpsGenieClientConstants.API.STATUS, resp.getAlert().getStatus());
        mapResponse.put( OpsGenieClientConstants.API.IS_SEEN, resp.getAlert().isSeen());
        mapResponse.put( OpsGenieClientConstants.API.TINY_ID, resp.getAlert().getTinyId());
        mapResponse.put( OpsGenieClientConstants.API.ACKNOWLEDGED, resp.getAlert().isAcknowledged());
        mapResponse.put( OpsGenieClientConstants.API.OWNER, resp.getAlert().getOwner());
        return mapResponse;
    }

    
    public Map heartbeat(Map params) throws Exception{
        populateCommonProps(params);
        HeartbeatRequest request = new HeartbeatRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        HeartbeatResponse resp = this.opsGenieClient.heartbeat(request);
        Map mapResponse = new HashMap();
        mapResponse.put("heartbeat", resp.getHeartbeat());
        return mapResponse;
    }

    protected void populateCommonProps(Map params){
        String cKey = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY);
        if(cKey == null){
            params.put(OpsGenieClientConstants.API.CUSTOMER_KEY, customerKey);
        }
    }
}
