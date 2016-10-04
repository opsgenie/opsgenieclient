package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;
import com.ifountain.opsgenie.client.model.beans.Condition;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ActionType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.NotifyBefore;
import com.ifountain.opsgenie.client.model.beans.Restriction;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Container for the parameters to make an add notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRule(AddNotificationRuleRequest)
 */
public class AddNotificationRuleRequest extends BaseUserComponentRequest<AddNotificationRuleResponse> {
    private String name;
    private ActionType actionType;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private List<NotifyBefore> notifyBefore;
    private Integer applyOrder;
    private List<Restriction> restrictions;
    private List<String> schedules;

    /**
     * Rest api uri of adding notificationRule operation.
     */
    public String getEndPoint() {
        return "/v1/json/user/notificationRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddNotificationRuleResponse createResponse() {
        return new AddNotificationRuleResponse();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType action) {
        this.actionType = action;
    }

    public ConditionMatchType getConditionMatchType() {
        return conditionMatchType;
    }

    public void setConditionMatchType(ConditionMatchType conditionType) {
        this.conditionMatchType = conditionType;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("restrictions")
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
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }

    public List<NotifyBefore> getNotifyBefore() {
        return notifyBefore;
    }

    public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
        this.notifyBefore = notifyBefore;
    }

}
