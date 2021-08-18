package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.User;
import com.ifountain.opsgenie.client.model.beans.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserRequest extends BaseRequest<UpdateUserResponse, UpdateUserRequest> {
    private String id;
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
        return role != null ? User.Role.fromName(role.getName()) : null;
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

    public String getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
    }

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateUserResponse createResponse() {
        return new UpdateUserResponse();
    }

    public UpdateUserRequest withId(String id) {
        this.id = id;
        return this;
    }

    public UpdateUserRequest withFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public UpdateUserRequest withSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
        return this;
    }

    public UpdateUserRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public UpdateUserRequest withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public UpdateUserRequest withRole(UserRole role) {
        this.role = role;
        return this;
    }
}
