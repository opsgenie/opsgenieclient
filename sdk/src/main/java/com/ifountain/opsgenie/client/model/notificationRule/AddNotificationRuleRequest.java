package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleConditions;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleRestriction;
import com.ifountain.opsgenie.client.model.beans.NotificationRule;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ActionType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.NotifyBefore;
/**
 * Container for the parameters to make an add notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRule(AddNotificationRuleRequest)
 */
public class AddNotificationRuleRequest extends BaseRequest<AddNotificationRuleResponse> {
	private String username;
	private String userId;
	private String name;
	private ActionType action;
	private ConditionMatchType conditionMatchType;
	private List<NotificationRuleConditions> conditions;
	private List<NotifyBefore> notifyBefore;
	private Integer applyOrder;
	private List<NotificationRuleRestriction> restirictions;
	private List<String> schedules;
	
	public static AddNotificationRuleRequest fromNotification(NotificationRule rule){
		if(rule == null)
			return null;
		AddNotificationRuleRequest request = new AddNotificationRuleRequest();
		request.setUsername(rule.getUsername());
		request.setUserId(rule.getUserId());
		request.setName(rule.getName());
		request.setAction(rule.getActionType());
		request.setConditionMatchType(rule.getConditionMatchType());
		request.setConditions(rule.getConditions());
		request.setNotifyBefore(rule.getNotifyBefore());
		request.setApplyOrder(rule.getApplyOrder());
		request.setRestirictions(rule.getRestirictions());
		request.setSchedules(rule.getSchedules());
		return request;
	}
	
	/**
	 * Rest api uri of adding notificationRule operation.
	 */
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
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
		if (getName() != null) 
			json.put(OpsGenieClientConstants.API.NAME, getName());
		if(getAction() != null)
			json.put(OpsGenieClientConstants.API.ACTION_TYPE, ActionType.getActionTypeEnumName(getAction()));
		if(getConditionMatchType() != null)
			json.put(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE, ConditionMatchType.getConditionMatchTypeEnumName(getConditionMatchType()));
		if(getConditions() != null){
			List<Map> conditionMapList = new ArrayList<Map>();
			for (NotificationRuleConditions cond : getConditions()) 
				conditionMapList.add(cond.toMap());
			json.put(OpsGenieClientConstants.API.CONDITIONS, conditionMapList);
		}
		if(getNotifyBefore() != null){
			List<String> notifyStringList = new ArrayList<String>();
			for (NotifyBefore notifyBefore : getNotifyBefore()) 
				notifyStringList.add(NotifyBefore.getNotifyBeforeEnumName(notifyBefore));
			json.put(OpsGenieClientConstants.API.NOTIFY_BEFORE, notifyStringList);
		}
		if(getApplyOrder() != null)
			json.put(OpsGenieClientConstants.API.APPLY_ORDER, getApplyOrder());
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
	
	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	@Override
	public AddNotificationRuleResponse createResponse() {
		return new AddNotificationRuleResponse();
	}
	/**
	 * UserName of notificationRule
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets userName of notificationRule
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * userID of notificationRule
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * Sets userID of notificationRule
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * name of notificationRule
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set name of notificationRule
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * ActionType of notificationRule
	 */
	public ActionType getAction() {
		return action;
	}
	/**
	 * Sets ActionType of notificationRule
	 */
	public void setAction(ActionType action) {
		this.action = action;
	}
	public ConditionMatchType getConditionMatchType() {
		return conditionMatchType;
	}
	public void setConditionMatchType(ConditionMatchType conditionType) {
		this.conditionMatchType = conditionType;
	}
	public List<NotificationRuleConditions> getConditions() {
		return conditions;
	}
	public void setConditions(List<NotificationRuleConditions> conditions) {
		this.conditions = conditions;
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
	public Integer getApplyOrder() {
		return applyOrder;
	}
	public void setApplyOrder(Integer applyOrder) {
		this.applyOrder = applyOrder;
	}
	public List<NotifyBefore> getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	
	
	

}
