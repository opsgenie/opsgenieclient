package com.ifountain.opsgenie.client.model.escalation;

/**
 * Container for the parameters to make an update escalation api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#updateEscalation(UpdateEscalationRequest)
 */
public class UpdateEscalationRequest extends AddEscalationRequest {
    private String id;
    /**
     * Rest api uri of updating escalation operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    /**
     * Id of escalation to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of escalation to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }
}
