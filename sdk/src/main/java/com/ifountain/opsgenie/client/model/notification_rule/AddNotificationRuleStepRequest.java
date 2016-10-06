package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Container for the parameters to make an add notificationRuleStep api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRuleStep(AddNotificationRuleStepRequest)
 */
public class AddNotificationRuleStepRequest extends BaseUserRequest<AddNotificationRuleStepResponse> {
    private String ruleId;
    private Method method;
    private String to;
    private Integer sendAfter;

    /**
     * Rest api uri of adding notificationRuleStep operation.
     */
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/step";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddNotificationRuleStepResponse createResponse() {
        return new AddNotificationRuleStepResponse();
    }

    /**
     * To of notificationRuleStep
     */
    public String getTo() {
        return to;
    }

    /**
     * Set To of notificationRuleStep
     */
    public void setTo(String to) {
        this.to = to;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Integer getSendAfter() {
        return sendAfter;
    }

    public void setSendAfter(Integer sendAfter) {
        this.sendAfter = sendAfter;
    }

}
