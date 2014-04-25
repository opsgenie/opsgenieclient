package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.user.*;
import com.ifountain.client.opsgenie.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner User Client
 */
public class InnerUserOpsGenieClient implements IUserOpsGenieClient {
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerUserOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IUserOpsGenieClient#addForwarding(com.ifountain.client.opsgenie.model.user.forward.AddForwardingRequest)
     */
    @Override
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, ClientException, ParseException {
        return (AddForwardingResponse) httpClient.doPostRequest(addForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#updateForwarding(com.ifountain.client.opsgenie.model.user.forward.UpdateForwardingRequest)
     */
    @Override
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, ClientException, ParseException {
        return (UpdateForwardingResponse) httpClient.doPostRequest(updateForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#deleteForwarding(com.ifountain.client.opsgenie.model.user.forward.DeleteForwardingRequest)
     */
    @Override
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, ClientException, ParseException {
        return (DeleteForwardingResponse) httpClient.doDeleteRequest(deleteForwardingRequest);

    }

    /**
     * @see IUserOpsGenieClient#getForwarding(com.ifountain.client.opsgenie.model.user.forward.GetForwardingRequest)
     */
    @Override
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, ClientException, ParseException {
        return (GetForwardingResponse) httpClient.doGetRequest(getForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#listForwardings(com.ifountain.client.opsgenie.model.user.forward.ListForwardingsRequest)
     */
    @Override
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, ClientException, ParseException {
        return (ListForwardingsResponse) httpClient.doGetRequest(listForwardingsRequest);
    }

    /**
     * @see IUserOpsGenieClient#addUser(com.ifountain.client.opsgenie.model.user.AddUserRequest)
     */
    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, ClientException, ParseException {
        return (AddUserResponse) httpClient.doPostRequest(addUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#updateUser(com.ifountain.client.opsgenie.model.user.UpdateUserRequest)
     */
    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, ClientException, ParseException {
        return (UpdateUserResponse) httpClient.doPostRequest(updateUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#deleteUser(com.ifountain.client.opsgenie.model.user.DeleteUserRequest)
     */
    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, ClientException, ParseException {
        return (DeleteUserResponse) httpClient.doDeleteRequest(deleteUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#getUser(com.ifountain.client.opsgenie.model.user.GetUserRequest)
     */
    @Override
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, ClientException, ParseException {
        return (GetUserResponse) httpClient.doGetRequest(getUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#listUsers(com.ifountain.client.opsgenie.model.user.ListUsersRequest)
     */
    @Override
    public ListUsersResponse listUsers(ListUsersRequest listUsersRequest) throws IOException, ClientException, ParseException {
        return (ListUsersResponse) httpClient.doGetRequest(listUsersRequest);
    }
}
