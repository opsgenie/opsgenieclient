package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.JsonHttpClient;
import com.ifountain.client.opsgenie.model.group.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Group Client
 */
public class InnerGroupOpsGenieClient implements IGroupOpsGenieClient{
    private JsonHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerGroupOpsGenieClient(JsonHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IGroupOpsGenieClient#addGroup(com.ifountain.client.opsgenie.model.group.AddGroupRequest)
     */
    @Override
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, ClientException, ParseException {
        return (AddGroupResponse) httpClient.doPostRequest(addGroupRequest);
    }

    /**
     * @see IGroupOpsGenieClient#updateGroup(com.ifountain.client.opsgenie.model.group.UpdateGroupRequest)
     */
    @Override
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, ClientException, ParseException {
        return (UpdateGroupResponse) httpClient.doPostRequest(updateGroupRequest);
    }

    /**
     * @see IGroupOpsGenieClient#deleteGroup(com.ifountain.client.opsgenie.model.group.DeleteGroupRequest)
     */
    @Override
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, ClientException, ParseException {
        return (DeleteGroupResponse) httpClient.doDeleteRequest(deleteGroupRequest);
    }

    /**
     * @see IGroupOpsGenieClient#getGroup(com.ifountain.client.opsgenie.model.group.GetGroupRequest)
     */
    @Override
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, ClientException, ParseException {
        return (GetGroupResponse) httpClient.doGetRequest(getGroupRequest);
    }

    /**
     * @see IGroupOpsGenieClient#listGroups(com.ifountain.client.opsgenie.model.group.ListGroupsRequest)
     */
    @Override
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws IOException, ClientException, ParseException {
        return (ListGroupsResponse) httpClient.doGetRequest(listGroupsRequest);
    }

    /**
     * @see IGroupOpsGenieClient#addGroupMember(com.ifountain.client.opsgenie.model.group.AddGroupMemberRequest)
     */
    @Override
    public AddGroupMemberResponse addGroupMember(AddGroupMemberRequest addGroupMemberRequest) throws IOException, ClientException, ParseException {
        return (AddGroupMemberResponse) httpClient.doPostRequest(addGroupMemberRequest);
    }

    /**
     * @see IGroupOpsGenieClient#deleteGroupMember(com.ifountain.client.opsgenie.model.group.DeleteGroupMemberRequest)
     */
    @Override
    public DeleteGroupMemberResponse deleteGroupMember(DeleteGroupMemberRequest deleteGroupMemberRequest) throws IOException, ClientException, ParseException {
        return (DeleteGroupMemberResponse) httpClient.doDeleteRequest(deleteGroupMemberRequest);
    }
}
