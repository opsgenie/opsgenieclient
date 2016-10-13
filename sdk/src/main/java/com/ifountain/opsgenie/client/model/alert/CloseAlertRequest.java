package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a close alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#closeAlert(CloseAlertRequest)
 */
public class CloseAlertRequest extends AddNoteRequest {

    /**
     * Rest api uri of close alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/close";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public CloseAlertResponse createResponse() {
        return new CloseAlertResponse();
    }
}
