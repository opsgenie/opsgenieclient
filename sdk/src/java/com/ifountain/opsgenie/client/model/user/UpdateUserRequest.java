package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;

/**
 * Container for the parameters to make an update user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateUser(UpdateUserRequest)
 */
public class UpdateUserRequest extends AddUserRequest {
    private String id;
    /**
     * Rest api uri of updating user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.ID, getId());
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
    public UpdateUserResponse createResponse() {
        return new UpdateUserResponse();
    }
}
