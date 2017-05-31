package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to escalate the alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#escalateToNext(EscalateToNextRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.EscalateAlertToNextRequest}
 */
@Deprecated
public class EscalateToNextRequest extends BaseAlertRequestWithParameters<EscalateToNextResponse, EscalateToNextRequest> {
    private String escalationName;
    private String escalationId;

    public String getEscalationName() {
        return escalationName;
    }

    public void setEscalationName(String escalationName) {
        this.escalationName = escalationName;
    }

    public EscalateToNextRequest withEscalationName(String escalationName) {
        this.escalationName = escalationName;
        return this;
    }

    public String getEscalationId() {
        return escalationId;
    }

    public void setEscalationId(String escalationId) {
        this.escalationId = escalationId;
    }

    public EscalateToNextRequest withEscalationId(String escalationId) {
        this.escalationId = escalationId;
        return this;
    }

    /**
     * Rest api uri of escalateToNext alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/escalateToNext";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public EscalateToNextResponse createResponse() {
        return new EscalateToNextResponse();
    }
}
