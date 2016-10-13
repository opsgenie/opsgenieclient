package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.TeamLog;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Represents the OpsGenie service response for list team logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsResponse extends BaseResponse {
    private String lastKey;
    @JsonProperty("logs")
    private List<TeamLog> teamLogs;

    /**
     * Returns team log objects
     *
     * @see TeamLog
     */
    public List<TeamLog> getTeamLogs() {
        return teamLogs;
    }

    /**
     * Sets team log objects
     *
     * @see TeamLog
     */
    public void setTeamLogs(List<TeamLog> teamLogs) {
        this.teamLogs = teamLogs;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }

}
