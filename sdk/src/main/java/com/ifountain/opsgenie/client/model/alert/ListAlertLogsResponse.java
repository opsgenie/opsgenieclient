package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.AlertLog;

import java.util.List;

/**
 * Represents the OpsGenie service response for list alert logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertLogs(ListAlertLogsRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.ListAlertLogsResponse}
 */
@Deprecated
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
