package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for update user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserResponse extends BaseResponse{
    private String id;

    /**
     * Id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of the user
     */
    public void setId(String id) {
        this.id = id;
    }
}
