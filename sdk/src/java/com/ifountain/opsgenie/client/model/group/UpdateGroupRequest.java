package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse;

import java.util.Map;

/**
 * Container for the parameters to make an update group api call.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(com.ifountain.opsgenie.client.model.group.UpdateGroupRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.ID, getId());
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public UpdateGroupResponse createResponse() {
        return new UpdateGroupResponse();
    }
}
