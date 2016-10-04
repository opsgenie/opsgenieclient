package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule;

import java.util.List;

/**
 * Represents OpsGenie service response for list TeamRoutingRules request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamRoutingRules(ListTeamRoutingRulesRequest)
 */
public class ListTeamRoutingRulesResponse extends BaseResponse {
    private List<TeamRoutingRule> rules;

    /**
     * List of TeamRoutingRules
     *
     * @see TeamRoutingRule
     */
    public List<TeamRoutingRule> getRules() {
        return rules;
    }

    /**
     * Sets list of TeamRoutingRules
     *
     * @see TeamRoutingRule
     */
    public void setRules(List<TeamRoutingRule> rules) {
        this.rules = rules;
    }
}
