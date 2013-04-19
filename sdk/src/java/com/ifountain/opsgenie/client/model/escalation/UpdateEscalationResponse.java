package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for update escalation request.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#updateEscalation(UpdateEscalationRequest) 
 */
public class UpdateEscalationResponse extends BaseResponse{
    private String id;

    /**
     * Id of the escalation
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of the escalation
     */
    public void setId(String id) {
        this.id = id;
    }
}
