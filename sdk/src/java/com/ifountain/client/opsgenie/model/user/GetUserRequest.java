package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get user api call.
 *
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserRequest extends BaseGetRequest<GetUserResponse> {
    private String username;
    /**
     * Rest api uri of getting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public void _serialize(Map json) {
        if(getUsername() != null){
            json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
        }
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetUserResponse createResponse() {
        return new GetUserResponse();
    }
}
