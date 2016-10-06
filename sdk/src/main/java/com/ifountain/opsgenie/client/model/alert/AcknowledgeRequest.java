package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make an acknowledge alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#acknowledge(AcknowledgeRequest)
 */
public class AcknowledgeRequest extends AddNoteRequest {

    /**
     * Rest api uri of acknowledge alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/acknowledge";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AcknowledgeResponse createResponse() {
        return new AcknowledgeResponse();
    }
}
