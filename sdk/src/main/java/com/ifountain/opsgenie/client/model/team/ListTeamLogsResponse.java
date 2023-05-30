package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.TeamLogsData;

import java.util.List;

/**
 * Represents the OpsGenie service response for list team logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsResponse extends BaseResponse {
    @JsonProperty("data")
    private TeamLogsData teamLogsData;

    public TeamLogsData getTeamLogsData() {
        return teamLogsData;
    }

    public void setTeamLogsData(TeamLogsData teamLogsData) {
        this.teamLogsData = teamLogsData;
    }
}
