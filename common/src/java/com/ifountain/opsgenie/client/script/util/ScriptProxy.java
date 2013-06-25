package com.ifountain.opsgenie.client.script.util;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.beans.*;
import com.ifountain.opsgenie.client.model.customer.*;
import com.ifountain.opsgenie.client.model.escalation.*;
import com.ifountain.opsgenie.client.model.group.*;
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
    final String customerKey;
    public ScriptProxy(IOpsGenieClient opsGenieClient, String customerKey){
        this.opsGenieClient = opsGenieClient;
        this.customerKey = customerKey;
    }

    public Map acknowledge(Map params) throws Exception{
        populateCommonProps(params);
        AcknowledgeRequest request = new AcknowledgeRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().acknowledge(request));
    }
    
    public Map addNote(Map params) throws Exception{
        populateCommonProps(params);
        AddNoteRequest request = new AddNoteRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().addNote(request));
    }

    public Map addRecipient(Map params) throws Exception{
        populateCommonProps(params);
        AddRecipientRequest request = new AddRecipientRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setRecipient(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.RECIPIENT));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().addRecipient(request));
    }

    public Map assign(Map params) throws Exception{
        populateCommonProps(params);
        AssignRequest request = new AssignRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setOwner(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.OWNER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().assign(request));
    }

    public Map attach(Map params) throws Exception{
        populateCommonProps(params);
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
        populateCommonProps(params);
        CloseAlertRequest request = new CloseAlertRequest();
        populateAlertRequestWithId(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().closeAlert(request));
    }

    public Map createAlert(Map params) throws Exception {
        populateCommonProps(params);
        CreateAlertRequest request = new CreateAlertRequest();
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
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        CreateAlertResponse resp = this.opsGenieClient.alert().createAlert(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ALERT_ID, resp.getAlertId());
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getAlertId());
        return mapResponse;
    }
    public Map deleteAlert(Map params) throws Exception {
        populateCommonProps(params);
        DeleteAlertRequest request = new DeleteAlertRequest();
        populateAlertRequestWithId(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().deleteAlert(request));
    }

    public Map executeAlertAction(Map params) throws Exception {
        populateCommonProps(params);
        ExecuteAlertActionRequest request = new ExecuteAlertActionRequest();
        populateAlertRequestWithId(request, params);
        request.setAction(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ACTION));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        ExecuteAlertActionResponse response = this.opsGenieClient.alert().executeAlertAction(request);
        Map respMap = successToMap(response);
        respMap.put(OpsGenieClientConstants.API.RESULT, response.getResult());
        return respMap;
    }

    public Map getAlert(Map params) throws Exception{
        populateCommonProps(params);
        GetAlertRequest request = new GetAlertRequest();
        populateAlertRequestWithId(request, params);
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        Map resp = this.opsGenieClient.alert().getAlert(request).getAlert().toMap();
        //for backward compatability
        resp.put( OpsGenieClientConstants.API.ALERT_ID, resp.get(OpsGenieClientConstants.API.ID));
        return resp;
    }
    public List<Map> listAlertLogs(Map params) throws Exception{
        populateCommonProps(params);
        ListAlertLogsRequest request = new ListAlertLogsRequest();
        populateAlertRequestWithId(request, params);
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.alert().listAlertLogs(request).getAlertLogs());
    }
    public Map listAlertRecipients(Map params) throws Exception{
        populateCommonProps(params);
        ListAlertRecipientsRequest request = new ListAlertRecipientsRequest();
        populateAlertRequestWithId(request, params);
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
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
        populateCommonProps(params);
        ListAlertsRequest request = new ListAlertsRequest();
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
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.alert().listAlerts(request).getAlerts());
    }

    public Map takeOwnership(Map params) throws Exception{
        populateCommonProps(params);
        TakeOwnershipRequest request = new TakeOwnershipRequest();
        populateAlertRequestWithId(request, params);
        request.setNote(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NOTE));
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.alert().takeOwnership(request));
    }

    public Map heartbeat(Map params) throws Exception{
        populateCommonProps(params);
        HeartbeatRequest request = new HeartbeatRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        HeartbeatResponse resp = this.opsGenieClient.heartbeat(request);
        Map mapResponse = new HashMap();
        mapResponse.put("heartbeat", resp.getHeartbeat());
        return mapResponse;
    }

    public Map deleteHeartbeat(Map params) throws Exception{
        populateCommonProps(params);
        DeleteHeartbeatRequest request = new DeleteHeartbeatRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        return successToMap(this.opsGenieClient.deleteHeartbeat(request));
    }
    public Map getHeartbeat(Map params) throws Exception{
        populateCommonProps(params);
        GetHeartbeatRequest request = new GetHeartbeatRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        request.setSource(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.SOURCE));
        return this.opsGenieClient.getHeartbeat(request).getHeartbeat().toMap();
    }
    public Map getHeartbeatConfig(Map params) throws Exception{
        populateCommonProps(params);
        GetHeartbeatConfigRequest request = new GetHeartbeatConfigRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.getHeartbeatConfig(request).getHeartbeatConfig().toMap();
    }
    public Map setHeartbeatConfig(Map params) throws Exception{
        populateCommonProps(params);
        SetHeartbeatConfigRequest request = new SetHeartbeatConfigRequest();
        HeartbeatConfig conf = new HeartbeatConfig();
        conf.fromMap(params);
        request.setMessage(conf.getMessage());
        request.setRecipients(conf.getRecipients());
        request.setEnabled(conf.isEnabled());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.setHeartbeatConfig(request));
    }
    public List<Map> listHeartbeats(Map params) throws Exception{
        populateCommonProps(params);
        ListHeartbeatsRequest request = new ListHeartbeatsRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return  beansToMap(this.opsGenieClient.listHeartbeats(request).getHeartbeats());
    }


    public Map addEscalation(Map params) throws Exception {
        populateCommonProps(params);
        AddEscalationRequest request = new AddEscalationRequest();
        Escalation escalation = new Escalation();
        escalation.fromMap(params);
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddEscalationResponse resp = this.opsGenieClient.escalation().addEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteEscalation(Map params) throws Exception {
        populateCommonProps(params);
        DeleteEscalationRequest request = new DeleteEscalationRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.escalation().deleteEscalation(request));
    }

    public Map getEscalation(Map params) throws Exception {
        populateCommonProps(params);
        GetEscalationRequest request = new GetEscalationRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.escalation().getEscalation(request).getEscalation().toMap();
    }

    public List<Map> listEscalations(Map params) throws Exception {
        populateCommonProps(params);
        ListEscalationsRequest request = new ListEscalationsRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.escalation().listEscalations(request).getEscalations());
    }

    public Map updateEscalation(Map params) throws Exception {
        populateCommonProps(params);
        UpdateEscalationRequest request = new UpdateEscalationRequest();
        Escalation escalation = new Escalation();
        escalation.fromMap(params);
        request.setId(escalation.getId());
        request.setName(escalation.getName());
        request.setRules(escalation.getRules());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        UpdateEscalationResponse resp = this.opsGenieClient.escalation().updateEscalation(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroup(Map params) throws Exception {
        populateCommonProps(params);
        AddGroupRequest request = new AddGroupRequest();
        Group group = new Group();
        group.fromMap(params);
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddGroupResponse resp = this.opsGenieClient.group().addGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addGroupMember(Map params) throws Exception {
        populateCommonProps(params);
        AddGroupMemberRequest request = new AddGroupMemberRequest();
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddGroupMemberResponse resp = this.opsGenieClient.group().addGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }
    public Map deleteGroupMember(Map params) throws Exception {
        populateCommonProps(params);
        DeleteGroupMemberRequest request = new DeleteGroupMemberRequest();
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddGroupMemberResponse resp = this.opsGenieClient.group().deleteGroupMember(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteGroup(Map params) throws Exception {
        populateCommonProps(params);
        DeleteGroupRequest request = new DeleteGroupRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.group().deleteGroup(request));
    }

    public Map getGroup(Map params) throws Exception {
        populateCommonProps(params);
        GetGroupRequest request = new GetGroupRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.group().getGroup(request).getGroup().toMap();
    }

    public List<Map> listGroups(Map params) throws Exception {
        populateCommonProps(params);
        ListGroupsRequest request = new ListGroupsRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.group().listGroups(request).getGroups());
    }

    public Map updateGroup(Map params) throws Exception {
        populateCommonProps(params);
        UpdateGroupRequest request = new UpdateGroupRequest();
        Group group = new Group();
        group.fromMap(params);
        request.setId(group.getId());
        request.setName(group.getName());
        request.setUsers(group.getUsers());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        UpdateGroupResponse resp = this.opsGenieClient.group().updateGroup(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addUser(Map params) throws Exception {
        populateCommonProps(params);
        AddUserRequest request = new AddUserRequest();
        User user = new User();
        user.fromMap(params);
        request.setUsername(user.getUsername());
        request.setFullname(user.getFullname());
        request.setRole(user.getRole());
        request.setTimeZone(user.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddUserResponse resp = this.opsGenieClient.user().addUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteUser(Map params) throws Exception {
        populateCommonProps(params);
        DeleteUserRequest request = new DeleteUserRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.user().deleteUser(request));
    }

    public Map getUser(Map params) throws Exception {
        populateCommonProps(params);
        GetUserRequest request = new GetUserRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setUsername(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USERNAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.user().getUser(request).getUser().toMap();
    }

    public List<Map> listUsers(Map params) throws Exception {
        populateCommonProps(params);
        ListUsersRequest request = new ListUsersRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.user().listUsers(request).getUsers());
    }

    public Map updateUser(Map params) throws Exception {
        populateCommonProps(params);
        UpdateUserRequest request = new UpdateUserRequest();
        User user = new User();
        user.fromMap(params);
        request.setId(user.getId());
        request.setUsername(user.getUsername());
        request.setFullname(user.getFullname());
        request.setRole(user.getRole());
        request.setTimeZone(user.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        UpdateUserResponse resp = this.opsGenieClient.user().updateUser(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }


    public Map addForwarding(Map params) throws Exception {
        populateCommonProps(params);
        AddForwardingRequest request = new AddForwardingRequest();
        Forwarding forwarding = new Forwarding();
        forwarding.fromMap(params);
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        AddForwardingResponse resp = this.opsGenieClient.user().addForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map deleteForwarding(Map params) throws Exception {
        populateCommonProps(params);
        DeleteForwardingRequest request = new DeleteForwardingRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.user().deleteForwarding(request));
    }

    public Map getForwarding(Map params) throws Exception {
        populateCommonProps(params);
        GetForwardingRequest request = new GetForwardingRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setAlias(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ALIAS));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.user().getForwarding(request).getForwarding().toMap();
    }

    public List<Map> listForwardings(Map params) throws Exception {
        populateCommonProps(params);
        ListForwardingsRequest request = new ListForwardingsRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.user().listForwardings(request).getForwardings());
    }

    public Map updateForwarding(Map params) throws Exception {
        populateCommonProps(params);
        UpdateForwardingRequest request = new UpdateForwardingRequest();
        Forwarding forwarding = new Forwarding();
        forwarding.fromMap(params);
        request.setId(forwarding.getId());
        request.setAlias(forwarding.getAlias());
        request.setStartDate(forwarding.getStartDate());
        request.setEndDate(forwarding.getEndDate());
        request.setFromUser(forwarding.getFromUser());
        request.setToUser(forwarding.getToUser());
        request.setTimeZone(forwarding.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        UpdateForwardingResponse resp = this.opsGenieClient.user().updateForwarding(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    public Map addSchedule(Map params) throws Exception {
        populateCommonProps(params);
        correctRestrictionAndParticipantParams(params);
        AddScheduleRequest request = new AddScheduleRequest();
        Schedule schedule = new Schedule();
        schedule.fromMap(params);
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRules(schedule.getRules());
        request.setTimeZone(schedule.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
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
        populateCommonProps(params);
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return successToMap(this.opsGenieClient.schedule().deleteSchedule(request));
    }

    public Map getSchedule(Map params) throws Exception {
        populateCommonProps(params);
        GetScheduleRequest request = new GetScheduleRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.schedule().getSchedule(request).getSchedule().toMap();
    }

    public Map whoIsOnCall(Map params) throws Exception {
        populateCommonProps(params);
        WhoIsOnCallRequest request = new WhoIsOnCallRequest();
        request.setId(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.ID));
        request.setName(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.NAME));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return this.opsGenieClient.schedule().whoIsOnCall(request).getWhoIsOnCall().toMap();
    }
    public List<Map> listWhoIsOnCall(Map params) throws Exception {
        populateCommonProps(params);
        ListWhoIsOnCallRequest request = new ListWhoIsOnCallRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.schedule().listWhoIsOnCall(request).getWhoIsOnCallList());
    }

    public List<Map> listSchedules(Map params) throws Exception {
        populateCommonProps(params);
        ListSchedulesRequest request = new ListSchedulesRequest();
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        return beansToMap(this.opsGenieClient.schedule().listSchedules(request).getSchedules());
    }

    public Map updateSchedule(Map params) throws Exception {
        populateCommonProps(params);
        correctRestrictionAndParticipantParams(params);
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        Schedule schedule = new Schedule();
        schedule.fromMap(params);
        request.setId(schedule.getId());
        request.setEnabled(schedule.isEnabled());
        request.setName(schedule.getName());
        request.setRules(schedule.getRules());
        request.setTimeZone(schedule.getTimeZone());
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
        UpdateScheduleResponse resp = this.opsGenieClient.schedule().updateSchedule(request);
        Map mapResponse = new HashMap();
        mapResponse.put(OpsGenieClientConstants.API.ID, resp.getId());
        return mapResponse;
    }

    private void populateAttachmentRequestCommonProps(AttachRequest request, Map params){
        populateAlertRequestWithId(request, params);
        request.setUser(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.USER));
        request.setIndexFile(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.INDEX_FILE));
        request.setCustomerKey(ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY));
    }

    protected void populateCommonProps(Map params){
        String cKey = ScriptBridgeUtils.getAsString(params, OpsGenieClientConstants.API.CUSTOMER_KEY);
        if(cKey == null){
            params.put(OpsGenieClientConstants.API.CUSTOMER_KEY, customerKey);
        }
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

    private void populateAlertRequestWithId(BaseAlertRequestWithId request, Map params){
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
