package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Alert;
import com.ifountain.client.opsgenie.model.beans.ListAlert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for a list alerts request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlerts(com.ifountain.client.opsgenie.model.alert.ListAlertsRequest)
 */
public class ListAlertsResponse extends BaseResponse {
    private List<ListAlert> alerts;

    /**
     * Returns list alert objects
     */
    public List<ListAlert> getAlerts() {
        return alerts;
    }

    /**
     * Sets list alert objects
     */
   public void setAlerts(List<ListAlert> alerts) {
        this.alerts = alerts;
    }

    /**
     * @see com.ifountain.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String, Object>> alertMaps = (List<Map<String, Object>>) data.get(ClientConstants.API.ALERTS);
        if(alertMaps != null){
            alerts = new ArrayList<ListAlert>();
            for(Map<String, Object> alertmap:alertMaps){
                ListAlert alert = new ListAlert();
                alert.fromMap(alertmap);
                alerts.add(alert);
            }
        }

    }
}
