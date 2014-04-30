package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Alert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an get alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlerts(com.ifountain.client.opsgenie.model.alert.ListAlertsRequest)
 */
public class ListAlertsResponse extends BaseResponse {
    private List<Alert> alerts;

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }


    /**
     * @see com.ifountain.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> alertMaps = (List<Map>) data.get(ClientConstants.API.ALERTS);
        if(alertMaps != null){
            alerts = new ArrayList<Alert>();
            for(Map alertmap:alertMaps){
                Alert alert = new Alert();
                alert.fromMap(alertmap);
                alerts.add(alert);
            }
        }

    }
}
