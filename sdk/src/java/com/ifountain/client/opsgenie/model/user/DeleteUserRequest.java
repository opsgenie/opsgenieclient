package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete user api call.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
 */
public class DeleteUserRequest extends BaseRequest<DeleteUserResponse> {
    private String id;
    private String username;
    /**
     * Rest api uri of deleting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user";
    }

    /**
     * Id of user to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of user to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Username of user to be deleted.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user to be deleted.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        if(getId() != null){
            json.put(ClientConstants.API.ID, getId());
        }
        if(getUsername() != null){
            json.put(ClientConstants.API.USERNAME, getUsername());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteUserResponse createResponse() {
        return new DeleteUserResponse();
    }
}
