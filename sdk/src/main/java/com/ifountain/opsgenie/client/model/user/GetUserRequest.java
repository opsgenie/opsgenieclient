package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserRequest extends BaseRequest<GetUserResponse> {
    private String username;
    private String id;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetUserResponse createResponse() {
        return new GetUserResponse();
    }
}
