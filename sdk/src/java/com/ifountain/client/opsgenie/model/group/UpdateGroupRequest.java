package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make an update group api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#updateGroup(com.ifountain.client.opsgenie.model.group.UpdateGroupRequest)
 */
public class UpdateGroupRequest extends AddGroupRequest {
    private String id;
    /**
     * Rest api uri of updating group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Id of group to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        json.put(ClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public UpdateGroupResponse createResponse() {
        return new UpdateGroupResponse();
    }
}
