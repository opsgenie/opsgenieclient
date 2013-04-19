package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list escalations api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#listEscalations(ListEscalationRequest)
 */
public class ListEscalationRequest extends BaseRequest {
    /**
     * Rest api uri of listing escalations operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }
}
