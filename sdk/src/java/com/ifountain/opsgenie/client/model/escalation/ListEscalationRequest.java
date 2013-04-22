package com.ifountain.opsgenie.client.model.escalation;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse;

import java.util.Map;

/**
 * Container for the parameters to make a list escalations api call.
 *
 * @see com.ifountain.opsgenie.client.IEscalationOpsGenieClient#listEscalations(ListEscalationRequest)
 */
public class ListEscalationRequest extends BaseRequest<ListEscalationResponse> {
    /**
     * Rest api uri of listing escalations operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/escalation";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListEscalationResponse createResponse() {
        return new ListEscalationResponse();
    }
}
