package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;

import java.util.List;

/**
 * Contact bean
 */
public class TeamRoutingRule extends BeanWithId {
    private String name;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private Integer applyOrder;
    private Boolean isDefault;
    private TeamRoutingRuleNotify notify;
    private List<Restriction> restrictions;

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestriction(List<Restriction> restrictions) {
        this.restrictions = restrictions;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public TeamRoutingRuleNotify getNotify() {
        return notify;
    }

    public void setNotify(TeamRoutingRuleNotify notify) {
        this.notify = notify;
    }

    public TeamRoutingRule withName(String name) {
        this.name = name;
        return this;
    }

    public TeamRoutingRule withConditionMatchType(ConditionMatchType conditionMatchType) {
        this.conditionMatchType = conditionMatchType;
        return this;
    }

    public TeamRoutingRule withConditions(List<Condition> conditions) {
        this.conditions = conditions;
        return this;
    }

    public TeamRoutingRule withApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public TeamRoutingRule withIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public TeamRoutingRule withNotify(TeamRoutingRuleNotify notify) {
        this.notify = notify;
        return this;
    }

    public TeamRoutingRule withRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public static class TeamRoutingRuleNotify extends Bean {
        private String name;
        private String id;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
