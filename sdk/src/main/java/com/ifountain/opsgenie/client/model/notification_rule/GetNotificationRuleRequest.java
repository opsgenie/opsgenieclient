package com.ifountain.opsgenie.client.model.notification_rule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * Container for the parameters to make a get notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#getNotificationRule(GetNotificationRuleRequest)
 */
public class GetNotificationRuleRequest extends BaseRequest<GetNotificationRuleResponse> {
	private String username;
	private String userId;
	@JsonSerialize(include=Inclusion.ALWAYS)
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
		if(id == null)
			throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
		super.validate();
	}

	@Override
	public GetNotificationRuleResponse createResponse() {
		return new GetNotificationRuleResponse();
	}
    /**
     * Username of notificationRule to be queried.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets Username of notificationRule to be queried.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of notificationRule to be queried.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of notificationRule to be queried.
     */
	public void setUserId(String userId) {
		this.userId = userId;
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
