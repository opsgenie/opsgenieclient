package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Team;

/**
 * Represents OpsGenie service response for get team request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(GetTeamRequest)
 */
public class GetTeamResponse extends BaseResponse {
    @JsonProperty("data")
    private Team team;

    /**
     * Details of team
     *
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets details of team
     *
     * @see com.ifountain.opsgenie.client.model.beans.Team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

}
