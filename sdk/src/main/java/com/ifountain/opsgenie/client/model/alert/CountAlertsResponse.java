package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an count alerts request.
 *
 * @author Veli Burak Celen
 * @version 5/11/15 1:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#countAlerts(com.ifountain.opsgenie.client.model.alert.CountAlertsRequest)
 */
public class CountAlertsResponse extends BaseResponse {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        count = (Integer) data.get(OpsGenieClientConstants.API.COUNT);
    }
}
