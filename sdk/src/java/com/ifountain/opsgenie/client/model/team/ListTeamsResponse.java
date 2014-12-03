package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list teams request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:00 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeams(ListTeamsRequest)
 */
public class ListTeamsResponse extends BaseResponse {
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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> teamsData = (List<Map>) data.get(OpsGenieClientConstants.API.GROUPS);
        teams = new ArrayList<Team>();
        for (Map teamData : teamsData) {
            Team team = new Team();
            team.fromMap(teamData);
            teams.add(team);
        }
    }
}
