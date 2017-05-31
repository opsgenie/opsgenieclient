package com.ifountain.opsgenie.client.model.alert;

/**
 * Container for the parameters to make a count alerts api call.
 *
 * @author Veli Burak Celen
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#countAlerts(com.ifountain.opsgenie.client.model.alert.CountAlertsRequest)
 * @deprecated As of release 2.8.0, will not be supported
 */
@Deprecated
public class CountAlertsRequest extends AlertsRequest<CountAlertsResponse, CountAlertsRequest> {

    @Override
    public String getEndPoint() {
        return "/v1/json/alert/count";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public CountAlertsResponse createResponse() {
        return new CountAlertsResponse();
    }
}
