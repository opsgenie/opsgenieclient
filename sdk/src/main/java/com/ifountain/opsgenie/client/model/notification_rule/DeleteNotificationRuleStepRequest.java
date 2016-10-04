package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;

/**
 * Container for the parameters to make a delete notificationRuleStep api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#deleteNotificationRuleStep(DeleteNotificationRuleStepRequest)
 */
public class DeleteNotificationRuleStepRequest extends BaseUserComponentRequest<DeleteNotificationRuleStepResponse> {
    private String ruleId;
    private String id;

    /**
     * Rest api uri of deleting notificationRuleStep operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/step";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteNotificationRuleStepResponse createResponse() {
        return new DeleteNotificationRuleStepResponse();
    }

    /**
     * Id of notificationRuleStep to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of notificationRuleStep to be deleted.
     */
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
