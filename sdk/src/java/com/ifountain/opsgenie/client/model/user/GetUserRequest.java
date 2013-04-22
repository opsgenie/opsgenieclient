package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;

/**
 * Container for the parameters to make a get user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserRequest extends BaseRequest<GetUserResponse> {
    private String id;
    private String username;
    /**
     * Rest api uri of getting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    /**
     * Id of user to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Username of user to be queried.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user to be queried.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        if(getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getId());
        }
        if(getUsername() != null){
            json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetUserResponse createResponse() {
        return new GetUserResponse();
    }
}
