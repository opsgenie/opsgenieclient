package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.NotificationRule;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleConditions;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleRestriction;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.NotifyBefore;
/**
 * Container for the parameters to make an update notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#updateNotificationRule(UpdateNotificationRuleRequest)
 */
public class UpdateNotificationRuleRequest extends BaseRequest<UpdateNotificationRuleResponse> {
	private String username;
	private String userId;
	private String id;
	
	private String name;
	private ConditionMatchType conditionMatchType;
	private List<NotificationRuleConditions> conditions;
	private List<NotifyBefore> notifyBefore;
	private List<NotificationRuleRestriction> restirictions;
	private List<String> schedules;
	
	public static UpdateNotificationRuleRequest fromNotification(NotificationRule rule){
		if(rule == null)
			return null;
		UpdateNotificationRuleRequest request = new UpdateNotificationRuleRequest();
		request.setId(rule.getId());
		request.setName(rule.getName());
		request.setConditionMatchType(rule.getConditionMatchType());
		request.setConditions(rule.getConditions());
		request.setNotifyBefore(rule.getNotifyBefore());
		request.setRestirictions(rule.getRestirictions());
		request.setSchedules(rule.getSchedules());
		return request;
	}
	
	/**
	 * Rest api uri of updating notificationRule operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
	}
	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
	 */
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = new HashMap();
		if (getApiKey() != null) 
			json.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
		if (getUsername() != null) 
			json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
		if (getUserId() != null) 
			json.put(OpsGenieClientConstants.API.USER_ID, getUserId());
		if (getId() != null) 
			json.put(OpsGenieClientConstants.API.ID, getId());
		if (getName() != null) 
			json.put(OpsGenieClientConstants.API.NAME, getName());
		if(getConditionMatchType() != null)
			json.put(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE, getConditionMatchType().value());
		if(getConditions() != null){
			List<Map> conditionMapList = new ArrayList<Map>();
			for (NotificationRuleConditions cond : getConditions()) 
				conditionMapList.add(cond.toMap());
			json.put(OpsGenieClientConstants.API.CONDITIONS, conditionMapList);
		}
		if(getNotifyBefore() != null){
			List<String> notifyStringList = new ArrayList<String>();
			for (NotifyBefore notifyBefore : getNotifyBefore()) 
				notifyStringList.add(notifyBefore.value());
			json.put(OpsGenieClientConstants.API.NOTIFY_BEFORE, notifyStringList);
		}
		if(getRestirictions() != null){
			if(getRestirictions().size() == 1 && getRestirictions().get(0).getEndDay() == null){
				json.put(OpsGenieClientConstants.API.RESTRICTIONS, getRestirictions().get(0));
			}
			else{
				List<Map> restirictionList = new ArrayList<Map>();
				for (NotificationRuleRestriction rest : getRestirictions()) 
					restirictionList.add(rest.toMap());
				json.put(OpsGenieClientConstants.API.RESTRICTIONS, restirictionList);
			}
		}
		if(getSchedules() != null)
			json.put(OpsGenieClientConstants.API.SCHEDULES, getSchedules());
		return json;
	}

	@Override
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	public UpdateNotificationRuleResponse createResponse() {
		return new UpdateNotificationRuleResponse();
	}
	/**
	 * Id of notificationRule to be updated
	 */
	public String getId() {
		return id;
	}
	/**
	 * Sets Id of notificationRule to be updated
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * UserName of notificationRule to be updated
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets UserName of notificationRule to be updated
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * userId of notificationRule to be updated
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * Sets userId of notificationRule to be updated
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ConditionMatchType getConditionMatchType() {
		return conditionMatchType;
	}
	public void setConditionMatchType(ConditionMatchType conditionMatchType) {
		this.conditionMatchType = conditionMatchType;
	}
	public List<NotificationRuleConditions> getConditions() {
		return conditions;
	}
	public void setConditions(List<NotificationRuleConditions> conditions) {
		this.conditions = conditions;
	}
	public List<NotifyBefore> getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	public List<NotificationRuleRestriction> getRestirictions() {
		return restirictions;
	}
	public void setRestirictions(List<NotificationRuleRestriction> restirictions) {
		this.restirictions = restirictions;
	}
	public List<String> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}
	
	

}
