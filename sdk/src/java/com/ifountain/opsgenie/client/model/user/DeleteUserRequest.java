package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
 */
public class DeleteUserRequest extends BaseRequest {
    private String id;
    private String username;
    /**
     * Rest api uri of deleting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    /**
     * Id of user to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Username of user to be deleted.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user to be deleted.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
