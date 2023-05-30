package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamLogsData extends Bean{
    private String offset;
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

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
