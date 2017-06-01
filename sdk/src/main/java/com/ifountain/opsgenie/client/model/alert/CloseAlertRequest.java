package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a close alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#closeAlert(CloseAlertRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.CloseAlertRequest}
 */
@Deprecated
public class CloseAlertRequest extends BaseAlertRequestWithParameters<CloseAlertResponse, CloseAlertRequest> {

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
