package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.User;

import java.util.List;

/**
 * Represents OpsGenie service response for get user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserResponse extends BaseResponse {
    @JsonProperty("data")
    private User user;

    private List<String> expandable;

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

    public List<String> getExpandable() {
        return expandable;
    }

    public void setExpandable(List<String> expandable) {
        this.expandable = expandable;
    }
}
