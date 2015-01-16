package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IUserOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;

import java.io.IOException;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:11 AM
 */
public class InnerUserOpsGenieClientMock implements IUserOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private AddForwardingResponse addForwardingResponse;
    private UpdateForwardingResponse updateForwardingResponse;
    private DeleteForwardingResponse deleteForwardingResponse;
    private GetForwardingResponse getForwardingResponse;
    private ListForwardingsResponse listForwardingsResponse;
    private AddUserResponse addUserResponse;
    private UpdateUserResponse updateUserResponse;
    private DeleteUserResponse deleteUserResponse;
    private GetUserResponse getUserResponse;
    private ListUsersResponse listUsersResponse;
    public InnerUserOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(addForwardingRequest);
        return addForwardingResponse;
    }

    @Override
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(updateForwardingRequest);
        return updateForwardingResponse;
    }

    @Override
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listForwardingsRequest);
        return listForwardingsResponse;
    }

    @Override
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(deleteForwardingRequest);
        return deleteForwardingResponse;
    }

    @Override
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(getForwardingRequest);
        return getForwardingResponse;
    }

    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(addUserRequest);
        return addUserResponse;
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(updateUserRequest);
        return updateUserResponse;
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(deleteUserRequest);
        return deleteUserResponse;
    }

    @Override
    public GetUserResponse getUser(GetUserRequest getUserRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(getUserRequest);
        return getUserResponse;
    }

    @Override
    public ListUsersResponse listUsers(ListUsersRequest listUsersRequest) throws IOException, OpsGenieClientException {
        requestProcessor.processRequest(listUsersRequest);
        return listUsersResponse;
    }


    public void setAddForwardingResponse(AddForwardingResponse addForwardingResponse) {
        this.addForwardingResponse = addForwardingResponse;
    }

    public void setUpdateForwardingResponse(UpdateForwardingResponse updateForwardingResponse) {
        this.updateForwardingResponse = updateForwardingResponse;
    }

    public void setDeleteForwardingResponse(DeleteForwardingResponse deleteForwardingResponse) {
        this.deleteForwardingResponse = deleteForwardingResponse;
    }

    public void setGetForwardingResponse(GetForwardingResponse getForwardingResponse) {
        this.getForwardingResponse = getForwardingResponse;
    }

    public void setListForwardingsResponse(ListForwardingsResponse listForwardingsResponse) {
        this.listForwardingsResponse = listForwardingsResponse;
    }

    public void setAddUserResponse(AddUserResponse addUserResponse) {
        this.addUserResponse = addUserResponse;
    }

    public void setUpdateUserResponse(UpdateUserResponse updateUserResponse) {
        this.updateUserResponse = updateUserResponse;
    }

    public void setDeleteUserResponse(DeleteUserResponse deleteUserResponse) {
        this.deleteUserResponse = deleteUserResponse;
    }

    public void setGetUserResponse(GetUserResponse getUserResponse) {
        this.getUserResponse = getUserResponse;
    }

    public void setListUsersResponse(ListUsersResponse listUsersResponse) {
        this.listUsersResponse = listUsersResponse;
    }
}
