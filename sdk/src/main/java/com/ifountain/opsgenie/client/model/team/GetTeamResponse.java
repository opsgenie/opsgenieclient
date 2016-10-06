package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Team;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * Represents OpsGenie service response for get team request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(GetTeamRequest)
 */
public class GetTeamResponse extends BaseResponse {
    @JsonUnwrapped
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
