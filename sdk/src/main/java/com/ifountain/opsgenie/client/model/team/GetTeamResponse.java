package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get team request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:00 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(GetTeamRequest)
 */
public class GetTeamResponse extends BaseResponse{
    private Team team;

    /**
     * Details of team
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets details of team
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        team = new Team();
        team.fromMap(data);
    }
}
