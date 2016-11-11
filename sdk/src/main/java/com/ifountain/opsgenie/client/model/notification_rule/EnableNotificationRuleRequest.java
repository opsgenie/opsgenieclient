package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make an enable notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#enableNotificationRule(com.ifountain.opsgenie.client.model.notification_rule.EnableNotificationRuleRequest)
 */
public class EnableNotificationRuleRequest extends BaseUserRequest<EnableNotificationRuleResponse, EnableNotificationRuleRequest> {
    private String id;

    /**
     * Rest api uri of enable notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/enable";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public EnableNotificationRuleResponse createResponse() {
        return new EnableNotificationRuleResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnableNotificationRuleRequest withId(String id) {
        this.id = id;
        return this;
    }

}
