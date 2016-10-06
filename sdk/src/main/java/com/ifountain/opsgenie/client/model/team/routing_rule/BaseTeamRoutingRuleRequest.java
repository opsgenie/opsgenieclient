package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Abstract Container for the team routing rule requests.
 *
 * @author Mehmet Mustafa Demir
 */
public abstract class BaseTeamRoutingRuleRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String teamId;
    private String teamName;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
