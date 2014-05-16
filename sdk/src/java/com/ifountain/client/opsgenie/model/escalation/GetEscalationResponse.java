package com.ifountain.client.opsgenie.model.escalation;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Escalation;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get escalation request.
 *
 * @author Mustafa Sener
 * @version 19.04.2013 11:39
 * @see com.ifountain.client.opsgenie.IEscalationOpsGenieClient#getEscalation(GetEscalationRequest)
 */
public class GetEscalationResponse extends BaseResponse{
    private Escalation escalation;

    /**
     * Details of escalation
     * @see com.ifountain.client.opsgenie.model.beans.Escalation
     */
    public Escalation getEscalation() {
        return escalation;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        escalation = new Escalation();
        escalation.fromMap(data);
    }

    /**
     * Sets details of escalation
     * @see com.ifountain.client.opsgenie.model.beans.Escalation
     */
    public void setEscalation(Escalation escalation) {
        this.escalation = escalation;
    }
}
