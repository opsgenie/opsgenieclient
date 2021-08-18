package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Represents OpsGenie service response for get TeamRoutingRules request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeamRoutingRule(GetTeamRoutingRuleRequest)
 */
public class GetTeamRoutingRuleResponse extends BaseResponse {
    @JsonUnwrapped
    private TeamRoutingRule rule;

    /**
     * Details of TeamRoutingRule
     *
     * @see TeamRoutingRule
     */
    public TeamRoutingRule getRule() {
        return rule;
    }

    /**
     * Sets details of TeamRoutingRule
     *
     * @see TeamRoutingRule
     */
    public void setRule(TeamRoutingRule rule) {
        this.rule = rule;
    }

}
