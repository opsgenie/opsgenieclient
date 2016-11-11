package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * Container for the parameters to make a delete notificationRule api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#deleteNotificationRule(DeleteNotificationRuleRequest)
 */
public class DeleteNotificationRuleRequest extends BaseUserRequest<DeleteNotificationRuleResponse, DeleteNotificationRuleStepRequest> {
    private String id;

    /**
     * Rest api uri of deleting notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteNotificationRuleResponse createResponse() {
        return new DeleteNotificationRuleResponse();
    }

    /**
     * Id of notificationRule to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of notificationRule to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    public DeleteNotificationRuleRequest withId(String id) {
        this.id = id;
        return this;
    }

}
