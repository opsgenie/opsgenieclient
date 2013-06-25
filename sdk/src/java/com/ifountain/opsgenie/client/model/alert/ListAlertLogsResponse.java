package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Alert;
import com.ifountain.opsgenie.client.model.beans.AlertLog;
import com.ifountain.opsgenie.client.model.beans.Heartbeat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for list alert logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 */
public class ListAlertLogsResponse extends BaseResponse {
    private List<AlertLog> alertLogs;

    /**
     * Returns alert log objects
     * @see AlertLog
     */
    public List<AlertLog> getAlertLogs() {
        return alertLogs;
    }

    /**
     * Sets alert log objects
     * @see AlertLog
     */
    public void setAlertLogs(List<AlertLog> alertLogs) {
        this.alertLogs = alertLogs;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> logsData = (List<Map>) data.get(OpsGenieClientConstants.API.LOGS);
        alertLogs = new ArrayList<AlertLog>();
        for(Map logData:logsData){
            AlertLog log = new AlertLog();
            log.fromMap(logData);
            alertLogs.add(log);
        }
    }
}