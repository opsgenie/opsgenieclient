package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.util.List;

/**
 * Represents OpsGenie service response for list teams request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeams(ListTeamsRequest)
 */
public class ListTeamsResponse extends BaseResponse {
    @JsonProperty("data")
    private List<Team> teams;

    /**
     * List of teams
     *
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Sets list of teams
     *
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
