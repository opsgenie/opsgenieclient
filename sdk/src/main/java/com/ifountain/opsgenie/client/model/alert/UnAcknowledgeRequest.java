package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an acknowledge alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#unAcknowledge(UnAcknowledgeRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.UnAcknowledgeAlertRequest}
 */
@Deprecated
public class UnAcknowledgeRequest extends BaseAlertRequestWithParameters<UnAcknowledgeResponse, UnAcknowledgeRequest> {

    /**
     * Rest api uri of unAcknowledge alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/unacknowledge";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UnAcknowledgeResponse createResponse() {
        return new UnAcknowledgeResponse();
    }
}
