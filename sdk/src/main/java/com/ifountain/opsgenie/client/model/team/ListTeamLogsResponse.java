package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.TeamLog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for list team logs request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 1:50 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(ListTeamLogsRequest)
 */
public class ListTeamLogsResponse extends BaseResponse {
    private String lastKey;
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

    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        lastKey = (String) data.get(OpsGenieClientConstants.API.LAST_KEY);
        List<Map> logsData = (List<Map>) data.get(OpsGenieClientConstants.API.LOGS);
        teamLogs = new ArrayList<TeamLog>();
        for (Map logData : logsData) {
            TeamLog log = new TeamLog();
            log.fromMap(logData);
            teamLogs.add(log);
        }
    }
}
