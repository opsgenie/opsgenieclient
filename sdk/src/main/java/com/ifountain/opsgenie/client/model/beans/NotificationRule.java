package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NotificationRule bean
 */
public class NotificationRule extends Bean {

    private String id;
    private String name;
    private Integer applyOrder;
    private ActionType actionType;
    private Boolean enabled;
    private ConditionMatchType conditionMatchType;
    private Integer loopAfter;
    private List<NotificationRuleStep> steps;
    private List<Condition> conditions;
    private List<String> schedules;
    private Object restrictions;
    private List<NotifyBefore> notifyBefore;


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


    public List<NotifyBefore> getNotifyBefore() {
        return notifyBefore;
    }

    public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
        this.notifyBefore = notifyBefore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static enum ActionType {
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
            if (value == null)
                return null;
            for (ActionType actionType : ActionType.values()) {
                if (actionType.value().toLowerCase().equals(value.toLowerCase()))
                    return actionType;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

    public static enum ConditionMatchType {
        MATCH_ALL(OpsGenieClientConstants.API.CONDITION_MATCH_ALL),
        MATCH_ALL_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ALL_CONDITIONS),
        MATCH_ANY_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ANY_CONDITIONS);
        private String value;

        private ConditionMatchType(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ConditionMatchType fromValue(String value) {
            if (value == null)
                return null;
            for (ConditionMatchType conditionMatchType : ConditionMatchType.values()) {
                if (conditionMatchType.value().toLowerCase().equals(value.toLowerCase()))
                    return conditionMatchType;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

    public static enum NotifyBefore {
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
            if (value == null)
                return null;
            for (NotifyBefore notifyBefore : NotifyBefore.values()) {
                if (notifyBefore.value().toLowerCase().equals(value.toLowerCase()))
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
