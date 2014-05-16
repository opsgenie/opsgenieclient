package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an update user api call.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#updateUser(UpdateUserRequest)
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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String, Object> serialize() throws ClientValidationException {
        Map<String, Object> json = super.serialize();
        json.put(ClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public UpdateUserResponse createResponse() {
        return new UpdateUserResponse();
    }
}
