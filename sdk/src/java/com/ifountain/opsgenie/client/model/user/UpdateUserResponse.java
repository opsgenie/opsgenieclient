package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for update user request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserResponse extends BaseResponse{
    private String id;

    /**
     * Id of the created forwarding
     */
    public String getId() {
        return id;
    }

    /**
     * Id of the created forwarding
     */
    public void setId(String id) {
        this.id = id;
    }
}
