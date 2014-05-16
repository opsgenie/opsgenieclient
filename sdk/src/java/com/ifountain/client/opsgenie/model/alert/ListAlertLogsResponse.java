package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.AlertLog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for list alert logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 */
public class ListAlertLogsResponse extends BaseResponse {
    private String lastKey;
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
     * Returns the key which will be used in pagination.
     */
    public String getLastKey() {
        return lastKey;
    }

    /**
     * Sets the key which will be used in pagination.
     */
    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

    /**
     * @see com.ifountain.client.model.BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        lastKey = (String) data.get(ClientConstants.API.LAST_KEY);
        List<Map<String, Object>> logsData = (List<Map<String, Object>>) data.get(ClientConstants.API.LOGS);
        alertLogs = new ArrayList<AlertLog>();
        for(Map<String, Object> logData:logsData){
            AlertLog log = new AlertLog();
            log.fromMap(logData);
            alertLogs.add(log);
        }
    }
}
