package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleStep;
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
	public static AddNotificationRuleStepRequest fromNotificationRuleStep(NotificationRuleStep step,String ruleID){
		if(step == null)
			return null;
		AddNotificationRuleStepRequest request = new AddNotificationRuleStepRequest();
		request.setMethod(step.getMethod());
		request.setSendAfter(step.getSendAfter());
		request.setTo(step.getTo());
		request.setRuleID(ruleID);
		return request;
	}
	
	/**
	 * Rest api uri of adding notificationRuleStep operation.
	 */
	public String getEndPoint() {
		return "/v1/json/user/notificationRule/Step";
	}
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
	 */
	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = super.serialize();
		if (getUsername() != null) 
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null) 
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		if (getRuleId() != null) 
			json.put(OpsGenieClientConstants.API.RULE_ID, getRuleId());
		if (getMethod() != null) 
			json.put(OpsGenieClientConstants.API.METHOD, getMethod().value());
		if (getTo() != null) 
			json.put(OpsGenieClientConstants.API.TO, getTo());
		if(getSendAfter() != null)
			json.put(OpsGenieClientConstants.API.SEND_AFTER, getSendAfter());
		return json;
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
	public void setRuleID(String ruleId) {
		this.ruleId = ruleId;
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
