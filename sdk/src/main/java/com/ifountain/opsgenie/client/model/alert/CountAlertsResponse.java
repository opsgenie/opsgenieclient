package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents the OpsGenie service response for an count alerts request.
 *
 * @author Veli Burak Celen
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#countAlerts(com.ifountain.opsgenie.client.model.alert.CountAlertsRequest)
 * @deprecated As of release 2.8.0, will not be supported
 */
@Deprecated
public class CountAlertsResponse extends BaseResponse {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
