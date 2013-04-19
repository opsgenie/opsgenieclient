package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for add escalation request.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#addEscalation(AddEscalationRequest)
 */
public class AddEscalationResponse extends BaseResponse{
    private String id;
    /**
     * Id of the added escalation
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added escalation
     */
    public void setId(String id) {
        this.id = id;
    }
}
