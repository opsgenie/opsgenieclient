package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.group.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for group related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IGroupOpsGenieClient {
    /**
     * Adds a group at OpsGenie.
     *
     * @param addGroupRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.AddGroupRequest
     * @see com.ifountain.client.opsgenie.model.group.AddGroupResponse
     */
    public AddGroupResponse addGroup(AddGroupRequest addGroupRequest) throws IOException, ClientException, ParseException;

    /**
     * Adds a group member at OpsGenie.
     *
     * @param addGroupMemberRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.AddGroupMemberRequest
     * @see com.ifountain.client.opsgenie.model.group.AddGroupMemberResponse
     */
    public AddGroupMemberResponse addGroupMember(AddGroupMemberRequest addGroupMemberRequest) throws IOException, ClientException, ParseException;

    /**
     * Removes a group member at OpsGenie.
     *
     * @param deleteGroupMemberRequest Object to construct request parameters.
     * @return <code>AddGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.DeleteGroupMemberRequest
     * @see com.ifountain.client.opsgenie.model.group.DeleteGroupMemberResponse
     */
    public DeleteGroupMemberResponse deleteGroupMember(DeleteGroupMemberRequest deleteGroupMemberRequest) throws IOException, ClientException, ParseException;

    /**
     * Updates group at OpsGenie.
     *
     * @param updateGroupRequest Object to construct request parameters.
     * @return <code>UpdateGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.UpdateGroupRequest
     * @see com.ifountain.client.opsgenie.model.group.UpdateGroupResponse
     */
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws IOException, ClientException, ParseException;

    /**
     * Deletes a group at OpsGenie.
     *
     * @param deleteGroupRequest Object to construct request parameters.
     * @return <code>DeleteGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.DeleteGroupRequest
     * @see com.ifountain.client.opsgenie.model.group.DeleteGroupResponse
     */
    public DeleteGroupResponse deleteGroup(DeleteGroupRequest deleteGroupRequest) throws IOException, ClientException, ParseException;

    /**
     * Get group details
     *
     * @param getGroupRequest Object to construct request parameters.
     * @return <code>GetGroupResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.GetGroupRequest
     * @see com.ifountain.client.opsgenie.model.group.GetGroupResponse
     */
    public GetGroupResponse getGroup(GetGroupRequest getGroupRequest) throws IOException, ClientException, ParseException;

    /**
     * List groups of customer
     *
     * @param listGroupsRequest Object to construct request parameters.
     * @return <code>ListGroupsResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.group.ListGroupsRequest
     * @see com.ifountain.client.opsgenie.model.group.ListGroupsResponse
     */
    public ListGroupsResponse listGroups(ListGroupsRequest listGroupsRequest) throws IOException, ClientException, ParseException;
}
