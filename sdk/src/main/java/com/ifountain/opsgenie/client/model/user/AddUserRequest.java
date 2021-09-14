package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.User;
import com.ifountain.opsgenie.client.model.beans.UserRole;


import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserRequest extends BaseRequest<AddUserResponse, AddUserRequest> {
    private String username;
    private String fullname;
    private String skypeUsername;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Locale locale;
    private UserRole role;

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
     * Locale of user
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets locale of user
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @deprecated Use getUserRole
     */
    @JsonIgnore
    @Deprecated
    public User.Role getRole() {

        if (role == null) {
            return null;
        }
        return User.Role.fromName(role.getName());
    }

    /**
     * @throws UnsupportedOperationException when role is custom. Please use setUserRole() for
     *                                       custom roles.
     * @deprecated Use setUserRole
     */
    @JsonIgnore
    @Deprecated
    public void setRole(User.Role role) {
        if (role != null) {
            if (role == User.Role.admin) {
                this.role = UserRole.ADMIN;
            } else if (role == User.Role.owner) {
                this.role = UserRole.OWNER;
            } else if (role == User.Role.user) {
                this.role = UserRole.USER;
            } else {
                throw new UnsupportedOperationException("custom role does not supported by Role enum. Use setUserRole() for custom roles.");
            }
        } else {
            this.role = null;
        }
    }

    /**
     * Role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.UserRole
     */
    @JsonProperty("role")
    public UserRole getUserRole() {
        return role;
    }

    /**
     * Sets role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.UserRole
     */
    public void setUserRole(UserRole role) {
        this.role = role;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddUserResponse createResponse() {
        return new AddUserResponse();
    }

    public String getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
    }


    public AddUserRequest withUsername(String username) {
        this.username = username;
        return this;
    }

    public AddUserRequest withFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public AddUserRequest withSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
        return this;
    }

    public AddUserRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public AddUserRequest withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public AddUserRequest withRole(UserRole role) {
        this.role = role;
        return this;
    }
}
