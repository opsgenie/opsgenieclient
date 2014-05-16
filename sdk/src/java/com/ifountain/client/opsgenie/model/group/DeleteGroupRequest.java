package com.ifountain.client.opsgenie.model.group;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete group api call.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 09:38
 * @see com.ifountain.client.opsgenie.IGroupOpsGenieClient#deleteGroup(com.ifountain.client.opsgenie.model.group.DeleteGroupRequest)
 */
public class DeleteGroupRequest extends BaseRequest<DeleteGroupResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of deleting group operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/group";
    }

    /**
     * Id of group to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of group to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of group to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of group to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(getId() != null){
            json.put(ClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(ClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public DeleteGroupResponse createResponse() {
        return new DeleteGroupResponse();
    }
}
