package com.ifountain.opsgenie.client.model.team.routing_rule;

/**
 * Container for the parameters to make a list TeamRoutingRules api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamRoutingRules(ListTeamRoutingRulesRequest)
 */
public class ListTeamRoutingRulesRequest extends BaseTeamRoutingRuleRequest<ListTeamRoutingRulesResponse, ListTeamRoutingRulesRequest> {

    /**
     * Rest api uri of listing teamRoutingRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/team/routingRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListTeamRoutingRulesResponse createResponse() {
        return new ListTeamRoutingRulesResponse();
    }

}
