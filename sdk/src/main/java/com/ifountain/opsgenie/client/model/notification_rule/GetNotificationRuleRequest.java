package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;

/**
 * Container for the parameters to make a get notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#getNotificationRule(GetNotificationRuleRequest)
 */
public class GetNotificationRuleRequest extends BaseUserComponentRequest<GetNotificationRuleResponse> {
    private String id;

    /**
     * Rest api uri of getting notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        super.validate();
    }

    @Override
    public GetNotificationRuleResponse createResponse() {
        return new GetNotificationRuleResponse();
    }

    /**
     * Id of notificationRule to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of notificationRule to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

}
