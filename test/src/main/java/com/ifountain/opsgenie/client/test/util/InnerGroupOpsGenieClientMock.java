package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IGroupOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.group.*;

import java.io.IOException;
import java.text.ParseException;

public class InnerGroupOpsGenieClientMock implements IGroupOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private AddGroupResponse addGroupResponse;
    private UpdateGroupResponse updateGroupResponse;
    private DeleteGroupResponse deleteGroupResponse;
    private GetGroupResponse getGroupResponse;
    private ListGroupsResponse listGroupsResponse;
    private AddGroupMemberResponse addGroupMemberResponse;
    private DeleteGroupMemberResponse deleteGroupMemberResponse;
    public InnerGroupOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(addGroupRequest);
        return addGroupResponse;
    }

    @Override
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(updateGroupRequest);
        return updateGroupResponse;
    }

    @Override
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listGroupsRequest);
        return listGroupsResponse;
    }

    @Override
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(deleteGroupRequest);
        return deleteGroupResponse;
    }

    @Override
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(getGroupRequest);
        return getGroupResponse;
    }

    @Override
    public AddGroupMemberResponse addGroupMember(AddGroupMemberRequest addGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(addGroupMemberRequest);
        return addGroupMemberResponse;
    }

    @Override
    public DeleteGroupMemberResponse deleteGroupMember(DeleteGroupMemberRequest deleteGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(deleteGroupMemberRequest);
        return deleteGroupMemberResponse;
    }

    public void setAddGroupResponse(AddGroupResponse addGroupResponse) {
        this.addGroupResponse = addGroupResponse;
    }

    public void setUpdateGroupResponse(UpdateGroupResponse updateGroupResponse) {
        this.updateGroupResponse = updateGroupResponse;
    }

    public void setDeleteGroupResponse(DeleteGroupResponse deleteGroupResponse) {
        this.deleteGroupResponse = deleteGroupResponse;
    }

    public void setGetGroupResponse(GetGroupResponse getGroupResponse) {
        this.getGroupResponse = getGroupResponse;
    }

    public void setListGroupsResponse(ListGroupsResponse listGroupsResponse) {
        this.listGroupsResponse = listGroupsResponse;
    }

    public AddGroupMemberResponse getAddGroupMemberResponse() {
        return addGroupMemberResponse;
    }

    public void setAddGroupMemberResponse(AddGroupMemberResponse addGroupMemberResponse) {
        this.addGroupMemberResponse = addGroupMemberResponse;
    }

    public DeleteGroupMemberResponse getDeleteGroupMemberResponse() {
        return deleteGroupMemberResponse;
    }

    public void setDeleteGroupMemberResponse(DeleteGroupMemberResponse deleteGroupMemberResponse) {
        this.deleteGroupMemberResponse = deleteGroupMemberResponse;
    }
}
