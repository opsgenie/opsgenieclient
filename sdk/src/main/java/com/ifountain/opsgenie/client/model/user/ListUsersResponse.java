package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Paging;
import com.ifountain.opsgenie.client.model.beans.User;

import java.util.List;

/**
 * Represents OpsGenie service response for list user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listUsers(ListUsersRequest)
 */
public class ListUsersResponse extends BaseResponse {
    @JsonProperty("data")
    private List<User> users;
    private Integer totalCount;
    private Paging paging;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /**
     * List of users
     *
     * @see User
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets list of users
     *
     * @see User
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
