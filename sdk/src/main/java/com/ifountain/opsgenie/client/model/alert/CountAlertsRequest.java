package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a count alerts api call.
 *
 * @author Veli Burak Celen
 * @version 5/11/15 1:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#countAlerts(com.ifountain.opsgenie.client.model.alert.CountAlertsRequest)
 */
public class CountAlertsRequest extends AlertsRequest<CountAlertsResponse> {

    @Override
    public String getEndPoint() {
        return "/v1/json/alert/count";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public CountAlertsResponse createResponse() {
        return new CountAlertsResponse();
    }
}
