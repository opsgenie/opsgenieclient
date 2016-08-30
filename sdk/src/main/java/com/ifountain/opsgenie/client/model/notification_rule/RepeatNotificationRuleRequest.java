package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to repeat notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#repeatNotificationRule(RepeatNotificationRuleRequest)
 */
public class RepeatNotificationRuleRequest extends BaseRequest<RepeatNotificationRuleResponse> {
    private String username;
    private String userId;
    private String id;
    private Boolean enabled;
    private Integer loopAfter;

    /**
     * Rest api uri of deleting notificationRule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/notificationRule/repeat";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public RepeatNotificationRuleResponse createResponse() {
        return new RepeatNotificationRuleResponse();
    }

    /**
     * username of notificationRule to be deleted.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of notificationRule to be deleted.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * userId of notificationRule to be deleted.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets userId of notificationRule to be deleted.
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    public Integer getLoopAfter() {
        return loopAfter;
    }

    public void setLoopAfter(Integer loopAfter) {
        this.loopAfter = loopAfter;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
