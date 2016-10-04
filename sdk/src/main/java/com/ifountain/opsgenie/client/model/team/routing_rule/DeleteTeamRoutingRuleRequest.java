package com.ifountain.opsgenie.client.model.team.routing_rule;

/**
 * Container for the parameters to delete TeamRoutingRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeamRoutingRule(DeleteTeamRoutingRuleRequest)
 */
public class DeleteTeamRoutingRuleRequest extends BaseTeamRoutingRuleRequest<DeleteTeamRoutingRuleResponse> {
    private String id;

    /**
     * Rest api uri of delete teamRoutingRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team/routingRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteTeamRoutingRuleResponse createResponse() {
        return new DeleteTeamRoutingRuleResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
