package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contact bean
 */
public class TeamRoutingRule extends Bean {
    private String name;
    private String id;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private Integer applyOrder;
    private Object restrictions;
    private Boolean isDefault;
    private TeamRoutingRuleNotify notify;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeamRoutingRuleNotify getNotify() {
        return notify;
    }

    public void setNotify(TeamRoutingRuleNotify notify) {
        this.notify = notify;
    }

    public Object getRestirictionsMap() {
        if (getRestrictions() != null) {
            if (getRestrictions().size() == 1 && (getRestrictions().get(0).getEndDay() == null
                    && getRestrictions().get(0).getStartDay() == null)) {
                return getRestrictions().get(0);
            }
            return getRestrictions();
        }
        return null;
    }

    public List<Restriction> getRestrictions() {
        if (this.restrictions == null)
            return null;
        return (List<Restriction>) this.restrictions;
    }

    public void setRestrictions(Object restrictions) throws ParseException {
        List<Restriction> restrictionList = new ArrayList<Restriction>();
        if (restrictions instanceof List) {
            List<Map> list = (List) restrictions;
            for (Object object : list) {
                Restriction newRestiriction = new Restriction();
                newRestiriction.fromMap((Map) object);
                restrictionList.add(newRestiriction);
            }
        } else {
            Restriction newRestiriction = new Restriction();
            newRestiriction.fromMap((Map) restrictions);
            restrictionList.add(newRestiriction);
        }
        this.restrictions = restrictionList;
    }

    public void setRestrictionList(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

}
