package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an update user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserRequest extends AddUserRequest {
    private String id;
    /**
     * Rest api uri of updating user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    /**
     * Id of user to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }
}
