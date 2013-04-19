package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Escalation;
import com.ifountain.opsgenie.client.model.beans.Group;

/**
 * Represents OpsGenie service response for get escalation request.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest)
 */
public class GetEscalationResponse extends BaseResponse{
    private Escalation escalation;

    /**
     * Details of escalation
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public Escalation getEscalation() {
        return escalation;
    }

    /**
     * Sets details of escalation
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public void setEscalation(Escalation escalation) {
        this.escalation = escalation;
    }
}
