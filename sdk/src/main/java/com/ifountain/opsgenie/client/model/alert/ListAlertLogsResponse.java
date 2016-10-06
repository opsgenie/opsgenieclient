package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertLog;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Represents the OpsGenie service response for list alert logs request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 */
public class ListAlertLogsResponse extends BaseResponse {
    private String lastKey;
    @JsonProperty("logs")
    private List<AlertLog> alertLogs;

    /**
     * Returns alert log objects
     *
     * @see AlertLog
     */
    public List<AlertLog> getAlertLogs() {
        return alertLogs;
    }

    /**
     * Sets alert log objects
     *
     * @see AlertLog
     */
    public void setAlertLogs(List<AlertLog> alertLogs) {
        this.alertLogs = alertLogs;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }
}
