package com.ifountain.opsgenie.client.model.notification_rule;


import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make an add notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleStepOpsGenieClient#addNotificationRuleStep(AddNotificationRuleStepRequest)
 */
public class AddNotificationRuleStepRequest extends BaseRequest<AddNotificationRuleStepResponse> {
    private String username;
    private String userId;
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
     * UserName of notificationRuleStep
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets userName of notificationRuleStep
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * userID of notificationRuleStep
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets userID of notificationRuleStep
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    @JsonProperty("method")
    public String getMethodValue() {
        if (method != null)
            return method.value();
        return null;
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
