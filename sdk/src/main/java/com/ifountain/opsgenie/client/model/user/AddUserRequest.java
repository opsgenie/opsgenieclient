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
import org.apache.commons.lang3.StringUtils;


import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserRequest extends BaseRequest<AddUserResponse, AddUserRequest> {
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

    private Boolean invitationDisabled = false;


    /**
     * Rest api uri of addding user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isEmpty(username))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USERNAME);
        if(StringUtils.isEmpty(fullName))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.FULLNAME);
        if(role == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ROLE);
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

    public Boolean getInvitationDisabled() {
        return invitationDisabled;
    }

    public void setInvitationDisabled(Boolean invitationDisabled) {
        this.invitationDisabled = invitationDisabled;
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
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets fullname of user
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public AddUserRequest withUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public AddUserRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public AddUserRequest withDetails(Details details) {
        this.details = details;
        return this;
    }

    public AddUserRequest withInvitationDisabled(Boolean invitationDisabled) {
        this.invitationDisabled = invitationDisabled;
        return this;
    }

    public AddUserRequest withFullName(String fullName) {
        this.fullName = fullName;
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
