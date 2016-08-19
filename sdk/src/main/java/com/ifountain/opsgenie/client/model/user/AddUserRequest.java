package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.User;
import java.util.Locale;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make an add user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserRequest extends BaseRequest<AddUserResponse> {
    private String username;
    private String fullname;
    @JsonIgnore
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
     * TimeZoneId of user
     */
	@JsonProperty("timezone")
    public String getTimeZoneID() {
    	if(timeZone != null)
    		return timeZone.getID();
    	return null;
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
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
	@JsonProperty("role")
    public String getRoleName() {
    	if(role != null)
    		return role.name();
    	return null;
    }
    
    /**
     * Role of user
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public User.Role getRole() {
        return role;
    }

    /**
     * Sets role of user
     * @see com.ifountain.opsgenie.client.model.beans.User.Role
     */
    public void setRole(User.Role role) {
        this.role = role;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddUserResponse createResponse() {
        return new AddUserResponse();
    }
}
