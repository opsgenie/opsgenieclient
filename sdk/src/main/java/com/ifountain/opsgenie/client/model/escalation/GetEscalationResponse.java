package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Escalation;

import java.text.ParseException;
import java.util.Map;

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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        escalation = new Escalation();
        escalation.fromMap(data);
    }

    /**
     * Sets details of escalation
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public void setEscalation(Escalation escalation) {
        this.escalation = escalation;
    }
}
