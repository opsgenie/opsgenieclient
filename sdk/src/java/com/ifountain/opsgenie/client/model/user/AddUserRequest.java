package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.User;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addUser(AddUserRequest)
 */
public class AddUserRequest extends BaseRequest<AddUserResponse> {
    private String username;
    private String fullname;
    private TimeZone timeZone;
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        if(getUsername() != null){
            json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
        }
        if(getFullname() != null){
            json.put(OpsGenieClientConstants.API.FULLNAME, getFullname());
        }
        if(getRole() != null){
            json.put(OpsGenieClientConstants.API.ROLE, getRole().name());
        }
        if(getTimeZone() != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, getTimeZone().getID());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddUserResponse createResponse() {
        return new AddUserResponse();
    }
}
