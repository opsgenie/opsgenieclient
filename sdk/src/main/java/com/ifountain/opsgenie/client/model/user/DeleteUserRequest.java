package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
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
    public DeleteUserResponse createResponse() {
        return new DeleteUserResponse();
    }
}
