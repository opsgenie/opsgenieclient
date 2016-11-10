package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseUserRequest;
import com.ifountain.opsgenie.client.model.beans.Condition;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ActionType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.NotifyBefore;
import com.ifountain.opsgenie.client.model.beans.Restriction;

import java.util.List;

/**
 * Container for the parameters to make an update notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRule(UpdateNotificationRuleRequest)
 */
public class UpdateNotificationRuleRequest extends BaseUserRequest<UpdateNotificationRuleResponse> {
    private String id;
    private String name;
    private ActionType actionType;
    private ConditionMatchType conditionMatchType;
    private List<Condition> conditions;
    private List<NotifyBefore> notifyBefore;
    private List<Restriction> restrictions;
    private List<String> schedules;

    /**
     * Rest api uri of adding notificationRule operation.
     */
    public String getEndPoint() {
        return "/v1.1/json/user/notificationRule";
    }

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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateNotificationRuleResponse createResponse() {
        return new UpdateNotificationRuleResponse();
    }

    /**
     * name of notificationRule
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of notificationRule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ActionType of notificationRule
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets ActionType of notificationRule
     */
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

}
