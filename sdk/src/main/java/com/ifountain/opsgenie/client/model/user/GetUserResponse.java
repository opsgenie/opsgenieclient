package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.User;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * Represents OpsGenie service response for get user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserResponse extends BaseResponse {
    @JsonUnwrapped
    private User user;

    /**
     * Details of user
     *
     * @see User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets details of user
     *
     * @see User
     */
    public void setUser(User user) {
        this.user = user;
    }

}
