package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import javax.xml.bind.ValidationException;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseGetRequest;
/**
 * Container for the parameters to make a get notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#getNotificationRule(GetNotificationRuleRequest)
 */
public class GetNotificationRuleRequest extends BaseGetRequest<GetNotificationRuleResponse> {
	private String username;
	private String userId;
	private String id;
    /**
     * Rest api uri of getting notificationRule operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
	}
	
    @Override
    public boolean isValid() throws ValidationException {
    	if(this.id == null)
    		throw new ValidationException("ValidationException[[ID] field should be provided.]");
    	else if(this.username == null && this.userId == null)
    		throw new ValidationException("ValidationException[One of [userId] or [username] fields should be provided.]");
    	return super.isValid();
    }
    
	@Override
    public void _serialize(Map json) {
		if(getUsername() != null)
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if(getUserId() != null)
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		if(getId() != null)
			json.put(OpsGenieClientConstants.API.ID, getId());
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
