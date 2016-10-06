package com.ifountain.opsgenie.client.model.team.routing_rule;

import com.ifountain.opsgenie.client.model.beans.Condition;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.Restriction;
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule;
import com.ifountain.opsgenie.client.util.RestrictionsSeriliazer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Container for the parameters to make an add teamRoutingRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeamRoutingRule(AddTeamRoutingRuleRequest)
 */
public class AddTeamRoutingRuleRequest extends BaseTeamRoutingRuleRequest<AddTeamRoutingRuleResponse> {
    private TeamRoutingRule.TeamRoutingRuleNotify notify;
    private String name;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private Integer applyOrder;
    @JsonSerialize(using = RestrictionsSeriliazer.class)
    private List<Restriction> restrictions;

    @Override
    public String getEndPoint() {
        return "/v1/json/team/routingRule";
    }

    @Override
    public AddTeamRoutingRuleResponse createResponse() {
        return new AddTeamRoutingRuleResponse();
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

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

}
