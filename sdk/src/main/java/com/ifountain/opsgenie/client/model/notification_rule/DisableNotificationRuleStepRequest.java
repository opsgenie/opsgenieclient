package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;

/**
 * Container for the parameters to make an disable notificationRuleStep api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#disableNotificationRuleStep(DisableNotificationRuleStepRequest)
 */
public class DisableNotificationRuleStepRequest extends BaseUserComponentRequest<DisableNotificationRuleStepResponse> {
    private String ruleId;
    private String id;

    /**
     * Rest api uri of enable/disable notificationRuleStep operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/step/disable";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DisableNotificationRuleStepResponse createResponse() {
        return new DisableNotificationRuleStepResponse();
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

}
