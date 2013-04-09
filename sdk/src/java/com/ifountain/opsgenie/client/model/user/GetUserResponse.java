package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.User;

/**
 * Represents OpsGenie service response for get user request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserResponse extends BaseResponse{
    private User user;

    /**
     * Details of user
     * @see User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets details of user
     * @see User
     */
    public void setUser(User user) {
        this.user = user;
    }
}
