package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a delete group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#deleteGroup(com.ifountain.opsgenie.client.model.group.DeleteGroupRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public DeleteGroupResponse createResponse() {
        return new DeleteGroupResponse();
    }
}
