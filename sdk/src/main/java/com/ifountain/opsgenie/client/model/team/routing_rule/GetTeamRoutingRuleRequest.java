package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to get TeamRoutingRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeamRoutingRule(GetTeamRoutingRuleRequest)
 */
public class GetTeamRoutingRuleRequest extends BaseTeamRoutingRuleRequest<GetTeamRoutingRuleResponse, GetTeamRoutingRuleRequest> {
    private String id;

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
    }

    /**
     * Rest api uri of getting teamRoutingRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1.1/json/team/routingRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetTeamRoutingRuleResponse createResponse() {
        return new GetTeamRoutingRuleResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetTeamRoutingRuleRequest withId(String id) {
        this.id = id;
        return this;
    }

}
