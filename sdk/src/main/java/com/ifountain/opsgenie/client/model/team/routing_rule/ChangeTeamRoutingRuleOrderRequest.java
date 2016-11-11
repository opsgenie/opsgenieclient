package com.ifountain.opsgenie.client.model.team.routing_rule;

/**
 * Container for the parameters to change order of TeamRoutingRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#changeTeamRoutingRuleOrder(ChangeTeamRoutingRuleOrderRequest)
 */
public class ChangeTeamRoutingRuleOrderRequest extends BaseTeamRoutingRuleRequest<ChangeTeamRoutingRuleOrderResponse, ChangeTeamRoutingRuleOrderRequest> {
    private String id;
    private Integer applyOrder;

    /**
     * Rest api uri of change teamRoutingRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team/routingRule/changeOrder";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ChangeTeamRoutingRuleOrderResponse createResponse() {
        return new ChangeTeamRoutingRuleOrderResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }

    public ChangeTeamRoutingRuleOrderRequest withId(String id) {
        this.id = id;
        return this;
    }

    public ChangeTeamRoutingRuleOrderRequest withApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }
}
