package com.ifountain.opsgenie.client.model.escalation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Escalation;

/**
 * Represents OpsGenie service response for get escalation request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest)
 */
public class GetEscalationResponse extends BaseResponse {
    @JsonUnwrapped
    private Escalation escalation;

    /**
     * Details of escalation
     *
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public Escalation getEscalation() {
        return escalation;
    }

    /**
     * Sets details of escalation
     *
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public void setEscalation(Escalation escalation) {
        this.escalation = escalation;
    }
}
