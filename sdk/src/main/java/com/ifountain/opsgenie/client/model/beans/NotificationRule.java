package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.List;

/**
 * NotificationRule bean
 */
public class NotificationRule extends BeanWithId {

    private String name;
    private Integer applyOrder;
    private ActionType actionType;
    private Boolean enabled;
    private ConditionMatchType conditionMatchType;
    private Integer loopAfter;
    private List<NotificationRuleStep> steps;
    private List<Condition> conditions;
    private List<String> schedules;
    private List<NotifyBefore> notifyBefore;
    private List<Restriction> restrictions;

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestriction(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }


    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public ConditionMatchType getConditionMatchType() {
        return conditionMatchType;
    }

    public void setConditionMatchType(ConditionMatchType conditionMatchType) {
        this.conditionMatchType = conditionMatchType;
    }

    public Integer getLoopAfter() {
        return loopAfter;
    }

    public void setLoopAfter(Integer loopAfter) {
        this.loopAfter = loopAfter;
    }

    public List<NotificationRuleStep> getSteps() {
        return steps;
    }

    public void setSteps(List<NotificationRuleStep> steps) {
        this.steps = steps;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

    public List<NotifyBefore> getNotifyBefore() {
        return notifyBefore;
    }

    public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
        this.notifyBefore = notifyBefore;
    }

    public NotificationRule withName(String name) {
        this.name = name;
        return this;
    }

    public NotificationRule withApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public NotificationRule withActionType(ActionType actionType) {
        this.actionType = actionType;
        return this;
    }

    public NotificationRule withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public NotificationRule withConditionMatchType(ConditionMatchType conditionMatchType) {
        this.conditionMatchType = conditionMatchType;
        return this;
    }

    public NotificationRule withLoopAfter(Integer loopAfter) {
        this.loopAfter = loopAfter;
        return this;
    }

    public NotificationRule withSteps(List<NotificationRuleStep> steps) {
        this.steps = steps;
        return this;
    }

    public NotificationRule withConditions(List<Condition> conditions) {
        this.conditions = conditions;
        return this;
    }

    public NotificationRule withSchedules(List<String> schedules) {
        this.schedules = schedules;
        return this;
    }

    public NotificationRule withNotifyBefore(List<NotifyBefore> notifyBefore) {
        this.notifyBefore = notifyBefore;
        return this;
    }

    public NotificationRule withRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public enum ActionType {
        NEW_ALERT(OpsGenieClientConstants.API.NEW_ALERT),
        ACKNOWLEDGED_ALERT(OpsGenieClientConstants.API.ACKNOWLEDGED_ALERT),
        CLOSED_ALERT(OpsGenieClientConstants.API.CLOSED_ALERT),
        SCHEDULE_START(OpsGenieClientConstants.API.SCHEDULE_START),
        RENOTIFIED_ALERT(OpsGenieClientConstants.API.RENOTIFIED_ALERT),
        ASSIGNED_ALERT(OpsGenieClientConstants.API.ASSIGNED_ALERT),
        ADD_NOTE(OpsGenieClientConstants.API.ADD_NOTE),
        SCHEDULE_END(OpsGenieClientConstants.API.SCHEDULE_END),
        INCOMING_CALL_ROUTING(OpsGenieClientConstants.API.INCOMING_CALL_ROUTING);

        private String value;

        ActionType(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ActionType fromValue(String value) {
            for (ActionType actionType : ActionType.values()) {
                if (actionType.value().equalsIgnoreCase(value))
                    return actionType;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

    public enum ConditionMatchType {
        MATCH_ALL(OpsGenieClientConstants.API.CONDITION_MATCH_ALL),
        MATCH_ALL_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ALL_CONDITIONS),
        MATCH_ANY_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ANY_CONDITIONS);
        private String value;

        ConditionMatchType(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ConditionMatchType fromValue(String value) {
            for (ConditionMatchType conditionMatchType : ConditionMatchType.values()) {
                if (conditionMatchType.value().equalsIgnoreCase(value))
                    return conditionMatchType;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

    public enum NotifyBefore {
        JUST_BEFORE(OpsGenieClientConstants.API.NOTIFY_JUST_BEFORE),
        ONE_HOUR(OpsGenieClientConstants.API.NOTIFY_ONE_HOUR),
        FIFTEEN_MINUTES(OpsGenieClientConstants.API.NOTIFY_FIFTEEN_MINUTE),
        ONE_DAY(OpsGenieClientConstants.API.NOTIFY_ONE_DAY);

        private String value;

        NotifyBefore(String value) {
            this.value = value;
        }

        @JsonCreator
        public static NotifyBefore fromValue(String value) {
            for (NotifyBefore notifyBefore : NotifyBefore.values()) {
                if (notifyBefore.value().equalsIgnoreCase(value))
                    return notifyBefore;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

}
