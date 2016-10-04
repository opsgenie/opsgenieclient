package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Escalation;

import java.util.List;

/**
 * Represents OpsGenie service response for list escalations request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#listEscalations(ListEscalationsRequest)
 */
public class ListEscalationsResponse extends BaseResponse {
    private List<Escalation> escalations;

    /**
     * List of escalations
     *
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public List<Escalation> getEscalations() {
        return escalations;
    }

    /**
     * Sets list of escalations
     *
     * @see com.ifountain.opsgenie.client.model.beans.Escalation
     */
    public void setEscalations(List<Escalation> escalations) {
        this.escalations = escalations;
    }

}
