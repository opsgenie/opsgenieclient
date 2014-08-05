package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.group.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Group Client
 */
public class InnerGroupOpsGenieClient implements IGroupOpsGenieClient{
    private JsonOpsgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerGroupOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
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
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#listGroups(com.ifountain.opsgenie.client.model.group.ListGroupsRequest)
     */
    @Override
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListGroupsResponse) httpClient.doGetRequest(listGroupsRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#addGroupMember(com.ifountain.opsgenie.client.model.group.AddGroupMemberRequest)
     */
    @Override
    public AddGroupMemberResponse addGroupMember(AddGroupMemberRequest addGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddGroupMemberResponse) httpClient.doPostRequest(addGroupMemberRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroupMember(com.ifountain.opsgenie.client.model.group.DeleteGroupMemberRequest)
     */
    @Override
    public DeleteGroupMemberResponse deleteGroupMember(DeleteGroupMemberRequest deleteGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteGroupMemberResponse) httpClient.doDeleteRequest(deleteGroupMemberRequest);
    }
}
