package com.ifountain.opsgenie.client.model.user;


import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
 */
public class DeleteUserRequest extends BaseRequest<DeleteUserResponse, DeleteUserRequest> {
    private String identifier;

    public String getIdentifier() { return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    /**
     * Rest api uri of deleting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + identifier;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteUserResponse createResponse() {
        return new DeleteUserResponse();
    }

    public DeleteUserRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
}
