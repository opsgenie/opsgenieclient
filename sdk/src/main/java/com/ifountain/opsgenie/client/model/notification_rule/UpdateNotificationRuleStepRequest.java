package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseUserRequest;
import com.ifountain.opsgenie.client.model.beans.Contact;

/**
 * Container for the parameters to make an update notificationRuleStep api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRuleStep(UpdateNotificationRuleStepRequest)
 */
public class UpdateNotificationRuleStepRequest extends BaseUserRequest<UpdateNotificationRuleStepResponse, UpdateNotificationRuleStepRequest> {
    private String id;
    private String ruleId;
    private Contact.Method method;
    private String to;
    private Integer sendAfter;

    /**
     * Id of notificationRuleStep to be updated
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of notificationRuleStep to be updated
     */
    public void setId(String id) {
        this.id = id;
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

    public Contact.Method getMethod() {
        return method;
    }

    public void setMethod(Contact.Method method) {
        this.method = method;
    }

    public Integer getSendAfter() {
        return sendAfter;
    }

    public void setSendAfter(Integer sendAfter) {
        this.sendAfter = sendAfter;
    }

    public UpdateNotificationRuleStepRequest withRuleId(String ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public UpdateNotificationRuleStepRequest withMethod(Contact.Method method) {
        this.method = method;
        return this;
    }

    public UpdateNotificationRuleStepRequest withTo(String to) {
        this.to = to;
        return this;
    }

    public UpdateNotificationRuleStepRequest withSendAfter(Integer sendAfter) {
        this.sendAfter = sendAfter;
        return this;
    }

    public UpdateNotificationRuleStepRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Rest api uri of updating notificationRuleStep operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/step";
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
    public UpdateNotificationRuleStepResponse createResponse() {
        return new UpdateNotificationRuleStepResponse();
    }

}
