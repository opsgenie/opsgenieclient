package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.TimeZone;

/**
 * Container for the parameters to make an add user api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserRequest extends BaseRequest {
    static enum Role{
        Admin,
        User
    }
    private String username;
    private String fullname;
    private TimeZone timeZone;
    private Role role;


    /**
     * Rest api uri of addding user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    /**
     * Username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Fullname of user
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets fullname of user
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * TimeZone of user
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of user
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Role of user
     * @see Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     * @see Role
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
