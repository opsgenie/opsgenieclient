package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.beans.Condition;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.Restriction;
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule;

import java.util.List;

/**
 * Container for the parameters to make an update teamRoutingRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeamRoutingRule(UpdateTeamRoutingRuleRequest)
 */
public class UpdateTeamRoutingRuleRequest extends BaseTeamRoutingRuleRequest<UpdateTeamRoutingRuleResponse> {
    private String id;
    private TeamRoutingRule.TeamRoutingRuleNotify notify;
    private String name;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private List<Restriction> restrictions;

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

    @Override
    public String getEndPoint() {
        return "/v1.1/json/team/routingRule";
    }

    @Override
    public UpdateTeamRoutingRuleResponse createResponse() {
        return new UpdateTeamRoutingRuleResponse();
    }

    public TeamRoutingRule.TeamRoutingRuleNotify getNotify() {
        return notify;
    }

    public void setNotify(TeamRoutingRule.TeamRoutingRuleNotify notify) {
        this.notify = notify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConditionMatchType getConditionMatchType() {
        return conditionMatchType;
    }

    public void setConditionMatchType(ConditionMatchType conditionMatchType) {
        this.conditionMatchType = conditionMatchType;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
