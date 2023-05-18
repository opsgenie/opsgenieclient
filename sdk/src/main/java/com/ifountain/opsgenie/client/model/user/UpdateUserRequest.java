package com.ifountain.opsgenie.client.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Details;
import com.ifountain.opsgenie.client.model.beans.User;
import com.ifountain.opsgenie.client.model.beans.UserAddress;
import com.ifountain.opsgenie.client.model.beans.UserRole;


import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserRequest extends BaseRequest<UpdateUserResponse, UpdateUserRequest> {
    private String identifier;
    private String username;
    private String fullName;
    private UserRole role;
    private String skypeUsername;
    private UserAddress userAddress;
    private List<String> tags;
    private Details details;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Locale locale;

    /**
     * Rest api uri of addding user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/"+identifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
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
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ID, OpsGenieClientConstants.API.USERNAME);
    }

    /**
     * Id of user to be updated.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets id of user to be updated.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateUserResponse createResponse() {
        return new UpdateUserResponse();
    }

    public UpdateUserRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public UpdateUserRequest withFullName(String fullName) {
        this.fullName = fullName;
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

    public UpdateUserRequest withUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public UpdateUserRequest withDetails(Details details) {
        this.details = details;
        return this;
    }

    public UpdateUserRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public UpdateUserRequest withUsername(String username) {
        this.username = username;
        return this;
    }
}
