package com.ifountain.opsgenie.client.model.notificationRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ActionType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType;
import com.ifountain.opsgenie.client.model.beans.NotificationRule.NotifyBefore;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleConditions;
import com.ifountain.opsgenie.client.model.beans.NotificationRuleRestriction;
/**
 * Container for the parameters to make an add notificationRule api call.
 *
 * @see com.ifountain.opsgenie.client.INotificationRuleOpsGenieClient#addNotificationRule(AddNotificationRuleRequest)
 */
public class AddNotificationRuleRequest extends BaseRequest<AddNotificationRuleResponse> {
	private String username;
	private String userId;
	private String name;
	private ActionType actionType;
	private ConditionMatchType conditionMatchType;
	private List<NotificationRuleConditions> conditions;
	private List<NotifyBefore> notifyBefore;
	private Integer applyOrder;
	private List<NotificationRuleRestriction> restrictions;
	private List<String> schedules;
	
	/**
	 * Rest api uri of adding notificationRule operation.
	 */
	public String getEndPoint() {
		return "/v1/json/user/notificationRule";
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
	 * String ActionType of notificationRule
	 */
	@JsonProperty("actionType")
	public String getActionTypeValue() {
		if(actionType != null)
			return actionType.value();
		return null;
	}
	
	/**
	 * ActionType of notificationRule
	 */
	public ActionType getActionType() {
		return actionType;
	}
	/**
	 * Sets ActionType of notificationRule
	 */
	public void setActionType(ActionType action) {
		this.actionType = action;
	}
	/**
	 * String conditionMatchType of notificationRule
	 */
	@JsonProperty("conditionMatchType")
	public String getConditionMatchTypeString() {
		if(conditionMatchType != null)
			return conditionMatchType.value();
		return null;
	}
	public ConditionMatchType getConditionMatchType() {
		return conditionMatchType;
	}
	public void setConditionMatchType(ConditionMatchType conditionType) {
		this.conditionMatchType = conditionType;
	}
	@JsonProperty("conditions")
	public List<Map> getConditionsMap() {
		if(getConditions() != null){
			List<Map> conditionMapList = new ArrayList<Map>();
			for (NotificationRuleConditions cond : getConditions()) 
				conditionMapList.add(cond.toMap());
			return conditionMapList;
		}
		return null;
	}
	public List<NotificationRuleConditions> getConditions() {
		return conditions;
	}
	public void setConditions(List<NotificationRuleConditions> conditions) {
		this.conditions = conditions;
	}

	@JsonProperty("restrictions")
	public Object getRestirictionsMap() {
		if(getRestrictions() != null){
			if(getRestrictions().size() == 1 
					&& (getRestrictions().get(0).getEndDay() == null && getRestrictions().get(0).getStartDay() == null)){
				return getRestrictions().get(0).toMap();
			}
			List<Map> restrictionList = new ArrayList<Map>();
			for (NotificationRuleRestriction rest : getRestrictions()) 
				restrictionList.add(rest.toMap());
			return restrictionList;
		}
		return null;
	}
	public List<NotificationRuleRestriction> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(List<NotificationRuleRestriction> restrictions) {
		this.restrictions = restrictions;
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
	@JsonProperty("notifyBefore")
	public List<String> getNotifyBeforeString() {
		if(getNotifyBefore() != null){
			List<String> notifyStringList = new ArrayList<String>();
			for (NotifyBefore notifyBefore : getNotifyBefore()) 
				notifyStringList.add(notifyBefore.value());
			return notifyStringList;
		}
		return null;
	}
	public List<NotifyBefore> getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
		this.notifyBefore = notifyBefore;
	}

}
