package com.ifountain.opsgenie.client.model.group;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an update group api call.
 * groups are deprecated
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(com.ifountain.opsgenie.client.model.group.UpdateGroupRequest)
 */
@Deprecated
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
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateGroupResponse createResponse() {
        return new UpdateGroupResponse();
    }
}
