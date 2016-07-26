package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a list notificationRules api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#listNotificationRules(ListNotificationRulesRequest)
 */
public class ListNotificationRulesRequest extends BaseRequest<ListNotificationRulesResponse> {
	private String username;
	private String userId;
    /**
     * Rest api uri of listing notificationRule operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
	}
	
	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = super.serialize();
		if (getUsername() != null) 
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null) 
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		return json;
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public ListNotificationRulesResponse createResponse() {
		return new ListNotificationRulesResponse();
	}
    /**
     * Username of notificationRule to be listed.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets Username of notificationRule to be listed.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of notificationRule to be listed.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of notificationRule to be listed.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
