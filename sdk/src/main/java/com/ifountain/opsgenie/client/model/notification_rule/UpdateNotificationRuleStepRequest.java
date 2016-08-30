package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an update notificationRuleStep api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRuleStep(UpdateNotificationRuleStepRequest)
 */
public class UpdateNotificationRuleStepRequest extends AddNotificationRuleStepRequest {
    private String id;

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


}
