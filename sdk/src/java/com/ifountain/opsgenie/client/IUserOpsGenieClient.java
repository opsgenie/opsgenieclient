package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for user related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface IUserOpsGenieClient {
    /**
     * Adds a user at OpsGenie.
     *
     * @param addUserRequest Object to construct request parameters.
     * @return <code>AddUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.AddUserRequest
     * @see com.ifountain.opsgenie.client.model.user.AddUserResponse
     */
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates user at OpsGenie.
     *
     * @param updateUserRequest Object to construct request parameters.
     * @return <code>UpdateUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.UpdateUserRequest
     * @see com.ifountain.opsgenie.client.model.user.UpdateUserResponse
     */
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a user at OpsGenie.
     *
     * @param deleteUserRequest Object to construct request parameters.
     * @return <code>DeleteUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.DeleteUserRequest
     * @see com.ifountain.opsgenie.client.model.user.DeleteUserResponse
     */
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Get user details
     *
     * @param getUserRequest Object to construct request parameters.
     * @return <code>GetUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.GetUserRequest
     * @see com.ifountain.opsgenie.client.model.user.GetUserResponse
     */
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List users of customer
     *
     * @param listUsersRequest Object to construct request parameters.
     * @return <code>ListUsersResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.ListUsersRequest
     * @see com.ifountain.opsgenie.client.model.user.ListUsersResponse
     */
    public ListUsersResponse listUsers(ListUsersRequest listUsersRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Adds user notification forwarding setting. All of notifications will be sent to forwarded user during configured
     * time settings.
     *
     * @param addForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.AddForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.AddForwardingResponse
     */
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException, ParseException;


    /**
     * Updates user notification forwarding settings.
     *
     * @param updateForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingResponse
     */
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes the specified forwarding.
     *
     * @param deleteForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingResponse
     */
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Gets forwarding details.
     *
     * @param getForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.GetForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.GetForwardingResponse
     */
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Lists forwarding settings for the specified user.
     *
     * @param listForwardingsRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.ListForwardingsResponse
     */
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException, ParseException;
}
