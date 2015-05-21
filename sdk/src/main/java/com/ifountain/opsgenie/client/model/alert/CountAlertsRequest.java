package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.List;
import java.util.Map;

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
