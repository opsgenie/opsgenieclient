package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for update group request.
 *
 * @see com.ifountain.opsgenie.client.IGroupOpsGenieClient#updateGroup(UpdateEscalationRequest)
 */
public class UpdateGroupResponse extends BaseResponse{
    private String id;

    /**
     * Id of the group
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of the group
     */
    public void setId(String id) {
        this.id = id;
    }
}
