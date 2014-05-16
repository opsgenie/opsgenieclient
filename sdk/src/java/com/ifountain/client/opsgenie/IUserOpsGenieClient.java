package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.user.*;
import com.ifountain.client.opsgenie.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for user related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IUserOpsGenieClient {
    /**
     * Adds a user at OpsGenie.
     *
     * @param addUserRequest Object to construct request parameters.
     * @return <code>AddUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.AddUserRequest
     * @see com.ifountain.client.opsgenie.model.user.AddUserResponse
     */
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, ClientException, ParseException;

    /**
     * Updates user at OpsGenie.
     *
     * @param updateUserRequest Object to construct request parameters.
     * @return <code>UpdateUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.UpdateUserRequest
     * @see com.ifountain.client.opsgenie.model.user.UpdateUserResponse
     */
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, ClientException, ParseException;

    /**
     * Deletes a user at OpsGenie.
     *
     * @param deleteUserRequest Object to construct request parameters.
     * @return <code>DeleteUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.DeleteUserRequest
     * @see com.ifountain.client.opsgenie.model.user.DeleteUserResponse
     */
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, ClientException, ParseException;

    /**
     * Get user details
     *
     * @param getUserRequest Object to construct request parameters.
     * @return <code>GetUserResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.GetUserRequest
     * @see com.ifountain.client.opsgenie.model.user.GetUserResponse
     */
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, ClientException, ParseException;

    /**
     * List users of customer
     *
     * @param listUsersRequest Object to construct request parameters.
     * @return <code>ListUsersResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.ListUsersRequest
     * @see com.ifountain.client.opsgenie.model.user.ListUsersResponse
     */
    public ListUsersResponse listUsers(ListUsersRequest listUsersRequest) throws IOException, ClientException, ParseException;

    /**
     * Adds user notification forwarding setting. All of notifications will be sent to forwarded user during configured
     * time settings.
     *
     * @param addForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.forward.AddForwardingRequest
     * @see com.ifountain.client.opsgenie.model.user.forward.AddForwardingResponse
     */
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, ClientException, ParseException;

    /**
     * Updates user notification forwarding setting.
     * time settings.
     *
     * @param updateForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.forward.UpdateForwardingRequest
     * @see com.ifountain.client.opsgenie.model.user.forward.UpdateForwardingResponse
     */
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, ClientException, ParseException;

    /**
     * Deletes user notification forwarding setting.
     * @param deleteForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.forward.DeleteForwardingRequest
     * @see com.ifountain.client.opsgenie.model.user.forward.DeleteForwardingResponse
     */
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, ClientException, ParseException;

    /**
     * Returns user notification forwarding settings with details
     * @param getForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.forward.GetForwardingRequest
     * @see com.ifountain.client.opsgenie.model.user.forward.GetForwardingResponse
     */
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, ClientException, ParseException;

    /**
     * Lists all notification forwarding settings belongs to a spesific user or all users of the customer.
     * @param listForwardingsRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.user.forward.ListForwardingsRequest
     * @see com.ifountain.client.opsgenie.model.user.forward.ListForwardingsResponse
     */
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, ClientException, ParseException;
}
