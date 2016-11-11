package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make an enable notificationRuleStep api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#enableNotificationRuleStep(EnableNotificationRuleStepRequest)
 */
public class EnableNotificationRuleStepRequest extends BaseUserRequest<EnableNotificationRuleStepResponse, EnableNotificationRuleStepRequest> {
    private String ruleId;
    private String id;

    /**
     * Rest api uri of enable/disable notificationRuleStep operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/step/enable";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public EnableNotificationRuleStepResponse createResponse() {
        return new EnableNotificationRuleStepResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public EnableNotificationRuleStepRequest withRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public EnableNotificationRuleStepRequest withId(String id) {
        this.id = id;
        return this;
    }

}
