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
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddGroupResponse) httpClient.doPostRequest(addGroupRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(com.ifountain.opsgenie.client.model.group.UpdateGroupRequest)
     */
    @Override
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateGroupResponse) httpClient.doPostRequest(updateGroupRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroup(com.ifountain.opsgenie.client.model.group.DeleteGroupRequest)
     */
    @Override
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteGroupResponse) httpClient.doDeleteRequest(deleteGroupRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#getGroup(com.ifountain.opsgenie.client.model.group.GetGroupRequest)
     */
    @Override
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetGroupResponse) httpClient.doGetRequest(getGroupRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(com.ifountain.opsgenie.client.model.group.ListGroupRequest)
     */
    @Override
    public ListGroupResponse listGroups(ListGroupRequest listGroupRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListGroupResponse) httpClient.doGetRequest(listGroupRequest);
    }
}
