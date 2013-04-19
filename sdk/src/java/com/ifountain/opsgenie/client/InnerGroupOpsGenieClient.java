package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.beans.Forwarding;
import com.ifountain.opsgenie.client.model.beans.Group;
import com.ifountain.opsgenie.client.model.group.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ifountain.opsgenie.client.JsonOpgenieHttpClient.OpsGenieJsonResponse;

/**
 * Inner Group Client
 */
public class InnerGroupOpsGenieClient implements IGroupOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerGroupOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroup(com.ifountain.opsgenie.client.model.group.AddGroupRequest)
     */
    @Override
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, addGroupRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.NAME, addGroupRequest.getName());
        json.put(OpsGenieClientConstants.API.USERS, addGroupRequest.getUsers());
        OpsGenieJsonResponse resp = httpClient.doPostRequest(addGroupRequest, json);
        AddGroupResponse response = new AddGroupResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(com.ifountain.opsgenie.client.model.group.UpdateGroupRequest)
     */
    @Override
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, updateGroupRequest.getCustomerKey());
        json.put(OpsGenieClientConstants.API.ID, updateGroupRequest.getId());
        if(updateGroupRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, updateGroupRequest.getName());
        }
        if(updateGroupRequest.getUsers() != null){
            json.put(OpsGenieClientConstants.API.USERS, updateGroupRequest.getUsers());
        }
        OpsGenieJsonResponse resp = httpClient.doPostRequest(updateGroupRequest, json);
        UpdateGroupResponse response = new UpdateGroupResponse();
        response.setId((String) resp.getJson().get("id"));
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroup(com.ifountain.opsgenie.client.model.group.DeleteGroupRequest)
     */
    @Override
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, OpsGenieClientException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, deleteGroupRequest.getCustomerKey());
        if(deleteGroupRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, deleteGroupRequest.getId());
        }
        if(deleteGroupRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, deleteGroupRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doDeleteRequest(deleteGroupRequest, json);
        DeleteGroupResponse response = new DeleteGroupResponse();
        response.setTook(((Number) resp.getJson().get("took")).longValue());
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.group.GetGroupRequest)
     */
    @Override
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, getGroupRequest.getCustomerKey());
        if(getGroupRequest.getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getGroupRequest.getId());
        }
        if(getGroupRequest.getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getGroupRequest.getName());
        }
        OpsGenieJsonResponse resp = httpClient.doGetRequest(getGroupRequest, json);
        GetGroupResponse response = new GetGroupResponse();
        response.setTook(((Number) resp.json().get("took")).longValue());
        Group group = createGroupFromParameters(resp.getJson());
        response.setGroup(group);
        return response;
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(com.ifountain.opsgenie.client.model.group.ListGroupRequest)
     */
    @Override
    public ListGroupResponse listGroups(ListGroupRequest listGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.CUSTOMER_KEY, listGroupRequest.getCustomerKey());
        OpsGenieJsonResponse resp = httpClient.doGetRequest(listGroupRequest, json);
        ListGroupResponse response = new ListGroupResponse();
        List<Map> groupsData = (List<Map>) resp.getJson().get(OpsGenieClientConstants.API.GROUPS);
        List<Group> groups = new ArrayList<Group>();
        for(Map groupData:groupsData){
            Group group = createGroupFromParameters(groupData);
            groups.add(group);
        }
        response.setGroups(groups);
        return response;
    }

    private Group createGroupFromParameters(Map resp) throws ParseException {
        Group group = new Group();
        group.setId((String) resp.get(OpsGenieClientConstants.API.ID));
        group.setName((String) resp.get(OpsGenieClientConstants.API.NAME));
        group.setUsers((List<String>) resp.get(OpsGenieClientConstants.API.USERS));
        return group;
    }
}
