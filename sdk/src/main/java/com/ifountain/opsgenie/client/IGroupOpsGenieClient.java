package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.group.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for group related operations
 *
 * groups are deprecated
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
@Deprecated
public interface IGroupOpsGenieClient {
    /**
     * Adds a group at OpsGenie.
     *
     * @param addGroupRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.AddGroupRequest
     * @see com.ifountain.opsgenie.client.model.group.AddGroupResponse
     */
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Adds a group member at OpsGenie.
     *
     * @param addGroupMemberRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.AddGroupMemberRequest
     * @see com.ifountain.opsgenie.client.model.group.AddGroupMemberResponse
     */
    public AddGroupMemberResponse addGroupMember(AddGroupMemberRequest addGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Removes a group member at OpsGenie.
     *
     * @param deleteGroupMemberRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.DeleteGroupMemberRequest
     * @see com.ifountain.opsgenie.client.model.group.DeleteGroupMemberResponse
     */
    public DeleteGroupMemberResponse deleteGroupMember(DeleteGroupMemberRequest deleteGroupMemberRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates group at OpsGenie.
     *
     * @param updateGroupRequest Object to construct request parameters.
     * @return <code>UpdateGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.UpdateGroupRequest
     * @see com.ifountain.opsgenie.client.model.group.UpdateGroupResponse
     */
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a group at OpsGenie.
     *
     * @param deleteGroupRequest Object to construct request parameters.
     * @return <code>DeleteGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.DeleteGroupRequest
     * @see com.ifountain.opsgenie.client.model.group.DeleteGroupResponse
     */
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Get group details
     *
     * @param getGroupRequest Object to construct request parameters.
     * @return <code>GetGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.GetGroupRequest
     * @see com.ifountain.opsgenie.client.model.group.GetGroupResponse
     */
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List groups of customer
     *
     * @param listGroupsRequest Object to construct request parameters.
     * @return <code>ListGroupsResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.group.ListGroupsRequest
     * @see com.ifountain.opsgenie.client.model.group.ListGroupsResponse
     */
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws IOException, OpsGenieClientException, ParseException;
}
