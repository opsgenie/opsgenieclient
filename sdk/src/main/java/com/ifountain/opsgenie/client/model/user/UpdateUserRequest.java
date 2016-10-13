package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.User;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update user api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserRequest extends BaseRequest<UpdateUserResponse> {
    private String id;
    private String fullname;
    private String skypeUsername;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Locale locale;
    private User.Role role;

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
     * Role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public User.Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     *
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public void setRole(User.Role role) {
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
}
