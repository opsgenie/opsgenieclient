package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner User Client
 */
public class InnerUserOpsGenieClient implements IUserOpsGenieClient {
    private JsonOpsgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerUserOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see IUserOpsGenieClient#addForwarding(com.ifountain.opsgenie.client.model.user.forward.AddForwardingRequest)
     */
    @Override
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddForwardingResponse) httpClient.doPostRequestV2(addForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
     */
    @Override
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateForwardingResponse) httpClient.doPutRequestV2(updateForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#deleteForwarding(com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingRequest)
     */
    @Override
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteForwardingResponse) httpClient.doDeleteRequestV2(deleteForwardingRequest);

    }

    /**
     * @see IUserOpsGenieClient#getForwarding(com.ifountain.opsgenie.client.model.user.forward.GetForwardingRequest)
     */
    @Override
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetForwardingResponse) httpClient.doGetRequestV2(getForwardingRequest);
    }

    /**
     * @see IUserOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
     */
    @Override
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListForwardingsResponse) httpClient.doGetRequestV2(listForwardingsRequest);
    }

    /**
     * @see IUserOpsGenieClient#addUser(com.ifountain.opsgenie.client.model.user.AddUserRequest)
     */
    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddUserResponse) httpClient.doPostRequestV2(addUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#updateUser(com.ifountain.opsgenie.client.model.user.UpdateUserRequest)
     */
    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateUserResponse) httpClient.doPatchRequestV2(updateUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#deleteUser(com.ifountain.opsgenie.client.model.user.DeleteUserRequest)
     */
    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteUserResponse) httpClient.doDeleteRequestV2(deleteUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#getUser(com.ifountain.opsgenie.client.model.user.GetUserRequest)
     */
    @Override
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetUserResponse) httpClient.doGetRequestV2(getUserRequest);
    }

    /**
     * @see IUserOpsGenieClient#listUsers(com.ifountain.opsgenie.client.model.user.ListUsersRequest)
     */
    @Override
    public ListUsersResponse listUsers(ListUsersRequest listUsersRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListUsersResponse) httpClient.doGetRequestV2(listUsersRequest);
    }
}
