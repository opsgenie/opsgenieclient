package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make a get user api call.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
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
    public void _serialize(Map<String,Object>  json) {
        if(getUsername() != null){
            json.put(ClientConstants.API.USERNAME, getUsername());
        }
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetUserResponse createResponse() {
        return new GetUserResponse();
    }

    @Override
    public List<String> getMandatoryProperties() {
        List<String> mandatoryProperyList = super.getMandatoryProperties();
        mandatoryProperyList.add(ClientConstants.API.USERNAME);
        return mandatoryProperyList;
    }
}
