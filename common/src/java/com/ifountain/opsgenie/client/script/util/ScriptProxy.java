package com.ifountain.opsgenie.client.script.util;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.beans.*;
import com.ifountain.opsgenie.client.model.customer.*;
import com.ifountain.opsgenie.client.model.escalation.*;
import com.ifountain.opsgenie.client.model.group.*;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest;
import com.ifountain.opsgenie.client.model.schedule.*;
import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptProxy {
    IOpsGenieClient opsGenieClient;
    final String apiKey;
    public ScriptProxy(IOpsGenieClient opsGenieClient, String apiKey){
        this.opsGenieClient = opsGenieClient;
        this.apiKey = apiKey;
    }

    public Map acknowledge(Map params) throws Exception{
        AcknowledgeRequest request = new AcknowledgeRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        return successToMap(this.opsGenieClient.alert().acknowledge(request));
    }
    public Map renotify(Map params) throws Exception{
        RenotifyRequest request = new RenotifyRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        List<String> recipientList = ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.RECIPIENTS);
        if(recipientList != null){
            List<RenotifyRecipient> recipients = new ArrayList<RenotifyRecipient>();
            for(String recipientName:recipientList){
                RenotifyRecipient recipient = new RenotifyRecipient();
                recipient.setRecipient(recipientName);
                recipients.add(recipient);
            }
            request.setRecipients(recipients);
        }
        return successToMap(this.opsGenieClient.alert().renotify(request));
    }

    public Map addNote(Map params) throws Exception{
        AddNoteRequest request = new AddNoteRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        return successToMap(this.opsGenieClient.alert().addNote(request));
    }

    public Map addRecipient(Map params) throws Exception{
        AddRecipientRequest request = new AddRecipientRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setRecipient(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.RECIPIENT));
        
        return successToMap(this.opsGenieClient.alert().addRecipient(request));
    }

    public Map assign(Map params) throws Exception{
        AssignRequest request = new AssignRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setOwner(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OWNER));
        
        return successToMap(this.opsGenieClient.alert().assign(request));
    }

    public Map attach(Map params) throws Exception{
        AttachResponse resp;
        if(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT) != null){
            FileAttachRequest fileAttachRequest = new FileAttachRequest();
            String attachmentFilePath = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ATTACHMENT);
            fileAttachRequest.setFile(new File(attachmentFilePath));
            populateAttachmentRequestCommonProps(fileAttachRequest, params);
            resp = this.opsGenieClient.alert().attach(fileAttachRequest);
        }
        else{
            InputStreamAttachRequest inputStreamAttachRequest = new InputStreamAttachRequest();
            InputStream inputStream = (InputStream) params.get(OpsgenieClientApplicationConstants.ScriptProxy.INPUT_STREAM);
            String fileName = ScriptBridgeUtils.getAsString(params, OpsgenieClientApplicationConstants.ScriptProxy.FILE_NAME);
            inputStreamAttachRequest.setInputStream(inputStream);
            inputStreamAttachRequest.setFileName(fileName);
            populateAttachmentRequestCommonProps(inputStreamAttachRequest, params);
            resp = this.opsGenieClient.alert().attach(inputStreamAttachRequest);
        }
        return successToMap(resp);
    }

    public Map closeAlert(Map params) throws Exception{
        CloseAlertRequest request = new CloseAlertRequest();
        populateAlertRequestWithSource(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        return successToMap(this.opsGenieClient.alert().closeAlert(request));
    }

    public Map createAlert(Map params) throws Exception {
        CreateAlertRequest request = new CreateAlertRequest();
        populateCommonProps(request, params);
        request.setActions(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.ACTIONS));
        request.setTags(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.TAGS));
        request.setRecipients(ScriptBridgeUtils.getAsStringList(params, OpsGenieClientConstants.API.RECIPIENTS));
        request.setDetails(ScriptBridgeUtils.getAsMap(params, OpsGenieClientConstants.API.DETAILS));
        request.setMessage(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.MESSAGE));
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setEntity(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ENTITY));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        CreateAlertResponse resp = this.opsGenieClient.alert().createAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ALERT_ID, resp.getAlertId());
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getAlertId());
        return mapResponse;
    }
    public Map deleteAlert(Map params) throws Exception {
        DeleteAlertRequest request = new DeleteAlertRequest();
        populateAlertRequestWithSource(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        return successToMap(this.opsGenieClient.alert().deleteAlert(request));
    }

    public Map executeAlertAction(Map params) throws Exception {
        ExecuteAlertActionRequest request = new ExecuteAlertActionRequest();
        populateAlertRequestWithSource(request, params);
        request.setAction(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ACTION));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        
        ExecuteAlertActionResponse response = this.opsGenieClient.alert().executeAlertAction(request);
        Map respMap = successToMap(response);
        respMap.put(OpsGenieClientConstants.API.RESULT, response.getResult());
        return respMap;
    }

    public Map getAlert(Map params) throws Exception{
        GetAlertRequest request = new GetAlertRequest();
        populateCommonProps(request, params);
        populateAlertRequestWithId(request, params);
        
        Map resp = this.opsGenieClient.alert().getAlert(request).getAlert().toMap();
        //for backward compatability
        resp.put( OpsGenieClientConstants.API.ALERT_ID, resp.get(OpsGenieClientConstants.API.ID));
        return resp;
    }
    public Map listAlertLogs(Map params) throws Exception{
        ListAlertLogsRequest request = new ListAlertLogsRequest();
        populateAlertRequestWithId(request, params);
        
        if(params.containsKey(OpsGenieClientConstants.API.LAST_KEY)){
            request.setLastKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.LAST_KEY));
        }
        if(params.containsKey(OpsGenieClientConstants.API.LIMIT)){
            request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        }
        if(params.containsKey(OpsGenieClientConstants.API.ORDER)){
            request.setSortOrder(ListAlertLogsRequest.SortOrder.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER)));
        }
        ListAlertLogsResponse listAlertLogsResponse = this.opsGenieClient.alert().listAlertLogs(request);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(OpsGenieClientConstants.API.LAST_KEY, listAlertLogsResponse.getLastKey());
        res.put(OpsGenieClientConstants.API.LOGS, beansToMap(listAlertLogsResponse.getAlertLogs()));
        return res;
    }
    public Map listAlertRecipients(Map params) throws Exception{
        ListAlertRecipientsRequest request = new ListAlertRecipientsRequest();
        populateAlertRequestWithId(request, params);
        
        ListAlertRecipientsResponse resp = this.opsGenieClient.alert().listAlertRecipients(request);
        Map res = new HashMap();
        res.put(OpsGenieClientConstants.API.USERS, beansToMap(resp.getUsers()));
        Map groups = new HashMap();
        for(Map.Entry<String, List<AlertRecipient>> entry:resp.getGroups().entrySet()){
            List<Map> groupRecipients = beansToMap(entry.getValue());
            groups.put(entry.getKey(), groupRecipients);
        }
        res.put(OpsGenieClientConstants.API.GROUPS, groups);
        return res;
    }

    public List<Map> listAlerts(Map params) throws Exception{
        ListAlertsRequest request = new ListAlertsRequest();
        populateCommonProps(request, params);
        request.setCreatedAfter(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.CREATED_AFTER));
        request.setCreatedBefore(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.CREATED_BEFORE));
        request.setUpdatedAfter(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.UPDATED_AFTER));
        request.setUpdatedBefore(ScriptBridgeUtils.getAsLong(params, OpsGenieClientConstants.API.UPDATED_BEFORE));
        request.setLimit(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.LIMIT));
        if(params.containsKey(OpsGenieClientConstants.API.SORT_BY)){
            request.setSortBy(ListAlertsRequest.SortBy.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SORT_BY)));
        }
        if(params.containsKey(OpsGenieClientConstants.API.ORDER)){
            request.setSortOrder(ListAlertsRequest.SortOrder.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ORDER)));
        }
        if(params.containsKey(OpsGenieClientConstants.API.STATUS)){
            request.setStatus(ListAlertsRequest.Status.valueOf(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.STATUS)));
        }
        
        return beansToMap(this.opsGenieClient.alert().listAlerts(request).getAlerts());
    }

    public Map takeOwnership(Map params) throws Exception{
        TakeOwnershipRequest request = new TakeOwnershipRequest();
        populateAlertRequestWithSource(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        
        return successToMap(this.opsGenieClient.alert().takeOwnership(request));
    }

    public Map heartbeat(Map params) throws Exception{
        HeartbeatRequest request = new HeartbeatRequest();
        populateCommonProps(request, params);
        
        request.setName(getHeartbeatName(params));
        HeartbeatResponse resp = this.opsGenieClient.heartbeat(request);
        Map mapResponse = new HashMap();
        mapResponse.put("heartbeat", resp.getHeartbeat());
        return mapResponse;
    }

    public Map deleteHeartbeat(Map params) throws Exception{
        DeleteHeartbeatRequest request = new DeleteHeartbeatRequest();
        populateCommonProps(request, params);
        
        request.setName(getHeartbeatName(params));
        return successToMap(this.opsGenieClient.deleteHeartbeat(request));
    }
    public Map enableHeartbeat(Map params) throws Exception{
        EnableHeartbeatRequest request = new EnableHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnable(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLE));
        EnableHeartbeatResponse response = this.opsGenieClient.enableHeartbeat(request);
        return successToMap(response);
    }
    public Map addHeartbeat(Map params) throws Exception{
        AddHeartbeatRequest request = new AddHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        request.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        String intervalUnitStr = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT);
        if(intervalUnitStr != null){
            request.setIntervalUnit(Heartbeat.IntervalUnit.valueOf(intervalUnitStr));
        }
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        return successToMap(this.opsGenieClient.addHeartbeat(request));
    }
    public Map updateHeartbeat(Map params) throws Exception{
        UpdateHeartbeatRequest request = new UpdateHeartbeatRequest();
        populateCommonProps(request, params);

        request.setName(getHeartbeatName(params));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        request.setInterval(ScriptBridgeUtils.getAsInt(params, OpsGenieClientConstants.API.INTERVAL));
        String intervalUnitStr = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INTERVAL_UNIT);
        if(intervalUnitStr != null){
            request.setIntervalUnit(Heartbeat.IntervalUnit.valueOf(intervalUnitStr));
        }
        request.setDescription(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.DESCRIPTION));
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        return successToMap(this.opsGenieClient.addHeartbeat(request));
    }
    public Map getHeartbeat(Map params) throws Exception{
        GetHeartbeatRequest request = new GetHeartbeatRequest();
        populateCommonProps(request, params);
        
        request.setName(getHeartbeatName(params));
        return this.opsGenieClient.getHeartbeat(request).getHeartbeat().toMap();
    }

    private String getHeartbeatName(Map params){
        if(params.containsKey(OpsGenieClientConstants.API.SOURCE)){
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE);
        }
        else{
            return ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME);
        }
    }

    public List<Map> listHeartbeats(Map params) throws Exception{
        ListHeartbeatsRequest request = new ListHeartbeatsRequest();
        populateCommonProps(request, params);
        
        return  beansToMap(this.opsGenieClient.listHeartbeats(request).getHeartbeats());
    }

    @Deprecated
    public Map enableAutomation(Map params) throws Exception {
        return enableAlertPolicy(params);
    }

    public Map enableAlertPolicy(Map params) throws Exception {
        EnableAlertPolicyRequest request = new EnableAlertPolicyRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        Boolean enabled = ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED);
        request.setEnabled(enabled);

        return successToMap(this.opsGenieClient.alertPolicy().enableAlertPolicy(request));
    }

    public Map enableIntegration(Map params) throws Exception {
        EnableIntegrationRequest request = new EnableIntegrationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setEnabled(ScriptBridgeUtils.getAsBoolean(params, OpsGenieClientConstants.API.ENABLED));
        return successToMap(this.opsGenieClient.integration().enableIntegration(request));
    }

    public Map sendToIntegration(String endPoint, Map contentParams, Map httpParams) throws Exception {
            SendToIntegrationRequest request = new SendToIntegrationRequest();
            request.setEndPoint(endPoint);
            request.setContentParameters(contentParams);
            request.setHttpParameters(httpParams);
            return successToMap(this.opsGenieClient.integration().sendToIntegration(request));
        }

    public Map addEscalation(Map params) throws Exception {
        AddEscalationRequest request = new AddEscalationRequest();
        populateCommonProps(request, params);
        Escalation escalation = new Escalation();
        escalation.fromMap(params);
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());

        AddEscalationResponse resp = this.opsGenieClient.escalation().addEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteEscalation(Map params) throws Exception {
        DeleteEscalationRequest request = new DeleteEscalationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return successToMap(this.opsGenieClient.escalation().deleteEscalation(request));
    }

    public Map getEscalation(Map params) throws Exception {
        GetEscalationRequest request = new GetEscalationRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));

        return this.opsGenieClient.escalation().getEscalation(request).getEscalation().toMap();
    }

    public List<Map> listEscalations(Map params) throws Exception {
        ListEscalationsRequest request = new ListEscalationsRequest();
        populateCommonProps(request, params);

        return beansToMap(this.opsGenieClient.escalation().listEscalations(request).getEscalations());
    }

    public Map updateEscalation(Map params) throws Exception {
        UpdateEscalationRequest request = new UpdateEscalationRequest();
        populateCommonProps(request, params);
        Escalation escalation = new Escalation();
        escalation.fromMap(params);
        request.setId(escalation.getId());
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());
        
        UpdateEscalationResponse resp = this.opsGenieClient.escalation().updateEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroup(Map params) throws Exception {
        AddGroupRequest request = new AddGroupRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        group.fromMap(params);
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        
        AddGroupResponse resp = this.opsGenieClient.group().addGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroupMember(Map params) throws Exception {
        AddGroupMemberRequest request = new AddGroupMemberRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        
        AddGroupMemberResponse resp = this.opsGenieClient.group().addGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }
    public Map deleteGroupMember(Map params) throws Exception {
        DeleteGroupMemberRequest request = new DeleteGroupMemberRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        
        AddGroupMemberResponse resp = this.opsGenieClient.group().deleteGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteGroup(Map params) throws Exception {
        DeleteGroupRequest request = new DeleteGroupRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        
        return successToMap(this.opsGenieClient.group().deleteGroup(request));
    }

    public Map getGroup(Map params) throws Exception {
        GetGroupRequest request = new GetGroupRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        
        return this.opsGenieClient.group().getGroup(request).getGroup().toMap();
    }

    public List<Map> listGroups(Map params) throws Exception {
        ListGroupsRequest request = new ListGroupsRequest();
        populateCommonProps(request, params);
        
        return beansToMap(this.opsGenieClient.group().listGroups(request).getGroups());
    }

    public Map updateGroup(Map params) throws Exception {
        UpdateGroupRequest request = new UpdateGroupRequest();
        populateCommonProps(request, params);
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        
        UpdateGroupResponse resp = this.opsGenieClient.group().updateGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addUser(Map params) throws Exception {
        AddUserRequest request = new AddUserRequest();
        populateCommonProps(request, params);
        User user = new User();
        user.fromMap(params);
        request.setUsername(user.getUsername());
        request.setFullname(user.getFullname());
        request.setRole(user.getRole());
        request.setTimeZone(user.getTimeZone());
        request.setLocale(user.getLocale());
        
        AddUserResponse resp = this.opsGenieClient.user().addUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteUser(Map params) throws Exception {
        DeleteUserRequest request = new DeleteUserRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));
        
        return successToMap(this.opsGenieClient.user().deleteUser(request));
    }

    public Map getUser(Map params) throws Exception {
        GetUserRequest request = new GetUserRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));
        
        return this.opsGenieClient.user().getUser(request).getUser().toMap();
    }

    public List<Map> listUsers(Map params) throws Exception {
        ListUsersRequest request = new ListUsersRequest();
        populateCommonProps(request, params);
        
        return beansToMap(this.opsGenieClient.user().listUsers(request).getUsers());
    }

    public Map updateUser(Map params) throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();
        populateCommonProps(request, params);
        User user = new User();
        user.fromMap(params);
        request.setId(user.getId());
        request.setUsername(user.getUsername());
        request.setFullname(user.getFullname());
        request.setRole(user.getRole());
        request.setTimeZone(user.getTimeZone());
        request.setLocale(user.getLocale());
        
        UpdateUserResponse resp = this.opsGenieClient.user().updateUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }


    public Map addForwarding(Map params) throws Exception {
        AddForwardingRequest request = new AddForwardingRequest();
        populateCommonProps(request, params);
        Forwarding forwarding = new Forwarding();
        forwarding.fromMap(params);
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());
        
        AddForwardingResponse resp = this.opsGenieClient.user().addForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteForwarding(Map params) throws Exception {
        DeleteForwardingRequest request = new DeleteForwardingRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        
        return successToMap(this.opsGenieClient.user().deleteForwarding(request));
    }

    public Map getForwarding(Map params) throws Exception {
        GetForwardingRequest request = new GetForwardingRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        
        return this.opsGenieClient.user().getForwarding(request).getForwarding().toMap();
    }

    public List<Map> listForwardings(Map params) throws Exception {
        ListForwardingsRequest request = new ListForwardingsRequest();
        populateCommonProps(request, params);
        
        return beansToMap(this.opsGenieClient.user().listForwardings(request).getForwardings());
    }

    public Map updateForwarding(Map params) throws Exception {
        UpdateForwardingRequest request = new UpdateForwardingRequest();
        populateCommonProps(request, params);
        Forwarding forwarding = new Forwarding();
        forwarding.fromMap(params);
        request.setId(forwarding.getId());
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());
        
        UpdateForwardingResponse resp = this.opsGenieClient.user().updateForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addSchedule(Map params) throws Exception {
        correctRestrictionAndParticipantParams(params);
        AddScheduleRequest request = new AddScheduleRequest();
        populateCommonProps(request, params);
        Schedule schedule = new Schedule();
        schedule.fromMap(params);
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRules(schedule.getRules());
        request.setTimeZone(schedule.getTimeZone());
        
        AddScheduleResponse resp = this.opsGenieClient.schedule().addSchedule(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    private void correctRestrictionAndParticipantParams(Map params) {
        if(params.containsKey(OpsGenieClientConstants.API.RULES)){
            List<Map> rules = (List<Map>) params.get(OpsGenieClientConstants.API.RULES);
            for(Map ruleMap: rules){
                if(ruleMap.containsKey(OpsGenieClientConstants.API.RESTRICTIONS)){
                    List<Map> restrictions = (List<Map>) ruleMap.get(OpsGenieClientConstants.API.RESTRICTIONS);
                    for(Map restriction:restrictions){
                        int startHour = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.START_HOUR);
                        int startMinute = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.START_MINUTE);
                        int endHour = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.END_HOUR);
                        int endMinute = ScriptBridgeUtils.getAsInt(restriction, OpsgenieClientApplicationConstants.ScriptProxy.END_MINUTE);
                        restriction.put(OpsGenieClientConstants.API.START_TIME, ""+startHour +":"+startMinute);
                        restriction.put(OpsGenieClientConstants.API.END_TIME, ""+endHour+":"+endMinute);
                    }
                }
                if(ruleMap.containsKey(OpsGenieClientConstants.API.PARTICIPANTS)){
                    List<String> participants = (List<String>) ruleMap.get(OpsGenieClientConstants.API.PARTICIPANTS);
                    List<Map> participantMaps = new ArrayList<Map>();
                    for(String participant:participants){
                        Map participantMap = new HashMap();
                        participantMap.put(OpsGenieClientConstants.API.PARTICIPANT, participant);
                        participantMaps.add(participantMap);
                    }
                    ruleMap.put(OpsGenieClientConstants.API.PARTICIPANTS, participantMaps);
                }
            }

        }
    }

    public Map deleteSchedule(Map params) throws Exception {
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        
        return successToMap(this.opsGenieClient.schedule().deleteSchedule(request));
    }

    public Map getSchedule(Map params) throws Exception {
        GetScheduleRequest request = new GetScheduleRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        
        return this.opsGenieClient.schedule().getSchedule(request).getSchedule().toMap();
    }

    public Map whoIsOnCall(Map params) throws Exception {
        WhoIsOnCallRequest request = new WhoIsOnCallRequest();
        populateCommonProps(request, params);
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        
        request.setTimeZone(ScriptBridgeUtils.getAsTimeZone(params, OpsGenieClientConstants.API.TIMEZONE));
        request.setTime(ScriptBridgeUtils.getAsDate(params, OpsGenieClientConstants.API.TIME));
        return this.opsGenieClient.schedule().whoIsOnCall(request).getWhoIsOnCall().toMap();
    }
    public List<Map> listWhoIsOnCall(Map params) throws Exception {
        ListWhoIsOnCallRequest request = new ListWhoIsOnCallRequest();
        populateCommonProps(request, params);
        
        return beansToMap(this.opsGenieClient.schedule().listWhoIsOnCall(request).getWhoIsOnCallList());
    }

    public List<Map> listSchedules(Map params) throws Exception {
        ListSchedulesRequest request = new ListSchedulesRequest();
        populateCommonProps(request, params);
        
        return beansToMap(this.opsGenieClient.schedule().listSchedules(request).getSchedules());
    }

    public Map updateSchedule(Map params) throws Exception {
        correctRestrictionAndParticipantParams(params);
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        populateCommonProps(request, params);
        Schedule schedule = new Schedule();
        schedule.fromMap(params);
        request.setId(schedule.getId());
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRules(schedule.getRules());
        request.setTimeZone(schedule.getTimeZone());
        
        UpdateScheduleResponse resp = this.opsGenieClient.schedule().updateSchedule(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    private void populateAttachmentRequestCommonProps(AttachRequest request, Map params){
        populateAlertRequestWithId(request, params);
        populateCommonProps(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
    }

    protected void populateCommonProps(BaseRequest request, Map params){
        String apiKeyFromParam;
        if(params.containsKey(OpsGenieClientConstants.API.CUSTOMER_KEY)){
            apiKeyFromParam = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY);
        }
        else{
            apiKeyFromParam = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.API_KEY);
        }
        if(apiKeyFromParam == null){
            apiKeyFromParam = apiKey;
        }
        request.setApiKey(apiKeyFromParam);
    }

    protected Map successToMap(BaseResponse response){
        Map mapResponse = new HashMap();
        mapResponse.put(OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS, response.isSuccess());
        return mapResponse;
    }

    protected List<Map> beansToMap(List<? extends IBean> beans){
        List<Map> beanMaps = new ArrayList<Map>();
        for(IBean bean:beans){
            beanMaps.add(bean.toMap());
        }
        return beanMaps;
    }

    private void populateAlertRequestWithSource(BaseAlertRequestWithSource request, Map params){
        populateAlertRequestWithId(request, params);
        if(params.containsKey(OpsGenieClientConstants.API.SOURCE)){
            request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        }
    }
    private void populateAlertRequestWithId(BaseAlertRequestWithId request, Map params){
        populateCommonProps(request, params);
        if(params.containsKey(OpsGenieClientConstants.API.ALERT_ID)){
            request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALERT_ID));
        }
        if(params.containsKey(OpsGenieClientConstants.API.TINY_ID)){
            request.setTinyId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.TINY_ID));
        }
        if(params.containsKey(OpsGenieClientConstants.API.ID)){
            request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        }
        if(params.containsKey(OpsGenieClientConstants.API.ALIAS)){
            request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        }

    }
}
