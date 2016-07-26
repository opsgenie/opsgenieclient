package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

/**
 * NotificationRule bean
 */
public class NotificationRule implements IBean {

	public static enum ActionType {
		newAlert, acknowledgedAlert, closedAlert, scheduleStart, renotifiedAlert, assignedAlert, addNote, scheduleEnd, incomingCallRouting;
		public static String getActionTypeEnumName(ActionType actionType) {
			if (actionType == null)
				return null;
			if (actionType.equals(ActionType.scheduleStart))
				return OpsGenieClientConstants.API.SCHEDULE_START;
			else if (actionType.equals(ActionType.scheduleEnd))
				return OpsGenieClientConstants.API.SCHEDULE_END;
			else if (actionType.equals(ActionType.acknowledgedAlert))
				return OpsGenieClientConstants.API.ACKNOWLEDGED_ALERT;
			else if (actionType.equals(ActionType.renotifiedAlert))
				return OpsGenieClientConstants.API.RENOTIFIED_ALERT;
			else if (actionType.equals(ActionType.assignedAlert))
				return OpsGenieClientConstants.API.ASSIGNED_ALERT;
			else if (actionType.equals(ActionType.incomingCallRouting))
				return OpsGenieClientConstants.API.INCOMING_CALL_ROUTING;
			else if (actionType.equals(ActionType.newAlert))
				return OpsGenieClientConstants.API.NEW_ALERT;
			else if (actionType.equals(ActionType.closedAlert))
				return OpsGenieClientConstants.API.CLOSED_ALERT;
			else if (actionType.equals(ActionType.addNote))
				return OpsGenieClientConstants.API.ADD_NOTE;
			System.out.println("Error at getActionTypeEnumName= " + actionType);
			return null;

		}

		public static ActionType getActionType(String actionType) {
			if (actionType == null)
				return null;
			if (actionType.equals(OpsGenieClientConstants.API.SCHEDULE_START))
				return ActionType.scheduleStart;
			else if (actionType.equals(OpsGenieClientConstants.API.SCHEDULE_END))
				return ActionType.scheduleEnd;
			else if (actionType.equals(OpsGenieClientConstants.API.ACKNOWLEDGED_ALERT))
				return ActionType.acknowledgedAlert;
			else if (actionType.equals(OpsGenieClientConstants.API.RENOTIFIED_ALERT))
				return ActionType.renotifiedAlert;
			else if (actionType.equals(OpsGenieClientConstants.API.ASSIGNED_ALERT))
				return ActionType.assignedAlert;
			else if (actionType.equals(OpsGenieClientConstants.API.INCOMING_CALL_ROUTING))
				return ActionType.incomingCallRouting;
			else if (actionType.equals(OpsGenieClientConstants.API.NEW_ALERT))
				return ActionType.newAlert;
			else if (actionType.equals(OpsGenieClientConstants.API.CLOSED_ALERT))
				return ActionType.closedAlert;
			else if (actionType.equals(OpsGenieClientConstants.API.ADD_NOTE))
				return ActionType.addNote;
			System.out.println("Error at getActionType = " + actionType);
			return null;
		}
	}

	public static enum ConditionMatchType {
		matchAll, matchAllConditions, matchAnyConditions;
		public static String getConditionMatchTypeEnumName(ConditionMatchType conditionMatchType) {
			if (conditionMatchType == null)
				return null;
			if (conditionMatchType.equals(ConditionMatchType.matchAll))
				return OpsGenieClientConstants.API.CONDITION_MATCH_ALL;
			else if (conditionMatchType.equals(ConditionMatchType.matchAllConditions))
				return OpsGenieClientConstants.API.CONDITION_MATCH_ALL_CONDITIONS;
			else if (conditionMatchType.equals(ConditionMatchType.matchAnyConditions))
				return OpsGenieClientConstants.API.CONDITION_MATCH_ANY_CONDITIONS;
			System.out.println("Error at getConditionMatchTypeEnumName = " + conditionMatchType);
			return null;
		}

		public static ConditionMatchType getConditionMatchType(String conditionMatchTypeStr) {
			if (conditionMatchTypeStr == null)
				return null;
			if (conditionMatchTypeStr.equals(OpsGenieClientConstants.API.CONDITION_MATCH_ALL))
				return ConditionMatchType.matchAll;
			else if (conditionMatchTypeStr.equals(OpsGenieClientConstants.API.CONDITION_MATCH_ALL_CONDITIONS))
				return ConditionMatchType.matchAllConditions;
			else if (conditionMatchTypeStr.equals(OpsGenieClientConstants.API.CONDITION_MATCH_ANY_CONDITIONS))
				return ConditionMatchType.matchAnyConditions;
			System.out.println("Error at getConditionMatchType = " + conditionMatchTypeStr);
			return null;
		}
	}

	public static enum NotifyBefore {
		justBefore, oneHour, fifteenMinutes, oneDay;
		public static String getNotifyBeforeEnumName(NotifyBefore notifyBefore) {
			if (notifyBefore == null)
				return null;
			if (notifyBefore.equals(NotifyBefore.oneHour))
				return OpsGenieClientConstants.API.NOTIFY_ONE_HOUR;
			else if (notifyBefore.equals(NotifyBefore.oneDay))
				return OpsGenieClientConstants.API.NOTIFY_ONE_DAY;
			else if (notifyBefore.equals(NotifyBefore.fifteenMinutes))
				return OpsGenieClientConstants.API.NOTIFY_FIFTEEN_MINUTE;
			else if (notifyBefore.equals(NotifyBefore.justBefore))
				return OpsGenieClientConstants.API.NOTIFY_JUST_BEFORE;
			System.out.println("Error at getNotifyBeforeEnumName = " + notifyBefore);
			return null;
		}

		public static NotifyBefore getNotifyBefore(String notifyName) {
			if (notifyName == null)
				return null;
			if (notifyName.equals(OpsGenieClientConstants.API.NOTIFY_ONE_HOUR))
				return NotifyBefore.oneHour;
			else if (notifyName.equals(OpsGenieClientConstants.API.NOTIFY_ONE_DAY))
				return NotifyBefore.oneDay;
			else if (notifyName.equals(OpsGenieClientConstants.API.NOTIFY_FIFTEEN_MINUTE))
				return NotifyBefore.fifteenMinutes;
			else if (notifyName.equals(OpsGenieClientConstants.API.NOTIFY_JUST_BEFORE))
				return NotifyBefore.justBefore;
			System.out.println("Error at getNotifyBefore = " + notifyName);
			return null;
		}
	}

	private String id;
	private String userId;
	private String username;
	private String name;
	private Integer applyOrder;
	private ActionType actionType;
	private Boolean enabled;
	private ConditionMatchType conditionMatchType;
	private Integer loopAfter;
	private List<NotificationRuleStep> steps;
	private List<NotificationRuleConditions> conditions;
	private List<String> schedules;
	private List<NotificationRuleRestriction> restirictions;
	private List<NotifyBefore> notifyBefore;

	/**
	 * userId of notificationRule
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets userId of notificationRule
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * username of notificationRule
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username of notificationRule
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Id of notificationRule
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets Id of notificationRule
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> json = new HashMap<String, Object>();
		if(getActionType() != null)
			json.put(OpsGenieClientConstants.API.ACTION_TYPE, ActionType.getActionTypeEnumName(getActionType()));
		json.put(OpsGenieClientConstants.API.APPLY_ORDER, getApplyOrder());
		if(getSchedules() != null)
			json.put(OpsGenieClientConstants.API.SCHEDULES, getSchedules());
		json.put(OpsGenieClientConstants.API.NAME, getName());
		if(getNotifyBefore() != null){
			List<String> notifyStringList = new ArrayList<String>();
			for (NotifyBefore notifyBefore : getNotifyBefore()) 
				notifyStringList.add(NotifyBefore.getNotifyBeforeEnumName(notifyBefore));
			json.put(OpsGenieClientConstants.API.NOTIFY_BEFORE, notifyStringList);
		}
		if(getRestirictions() != null){
			List<Map> restirictionList = new ArrayList<Map>();
			for (NotificationRuleRestriction rest : getRestirictions()) 
				restirictionList.add(rest.toMap());
			json.put(OpsGenieClientConstants.API.RESTRICTIONS, restirictionList);
		}
		json.put(OpsGenieClientConstants.API.ID, getId());
		if(getConditions() != null){
			List<Map> conditionMapList = new ArrayList<Map>();
			for (NotificationRuleConditions cond : getConditions()) 
				conditionMapList.add(cond.toMap());
			json.put(OpsGenieClientConstants.API.CONDITIONS, conditionMapList);
		}
		if(getSteps() != null){
			List<Map> stepMapList = new ArrayList<Map>();
			for (NotificationRuleStep step : getSteps()) 
				stepMapList.add(step.toMap());
			json.put(OpsGenieClientConstants.API.STEPS, stepMapList);
		}
		json.put(OpsGenieClientConstants.API.ENABLED, getEnabled());
		if(getConditionMatchType() != null)
			json.put(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE, ConditionMatchType.getConditionMatchTypeEnumName(getConditionMatchType()));
		if(getLoopAfter() != null)
			json.put(OpsGenieClientConstants.API.LOOP_AFTER, getLoopAfter());
		return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		// Action Type
		if (map.containsKey(OpsGenieClientConstants.API.ACTION_TYPE))
			this.actionType = ActionType.getActionType((String) map.get(OpsGenieClientConstants.API.ACTION_TYPE));

		// variables
		if (map.containsKey(OpsGenieClientConstants.API.APPLY_ORDER))
			this.applyOrder = (Integer) map.get(OpsGenieClientConstants.API.APPLY_ORDER);
		if (map.containsKey(OpsGenieClientConstants.API.NAME))
			this.name = (String) map.get(OpsGenieClientConstants.API.NAME);
		if (map.containsKey(OpsGenieClientConstants.API.ID))
			this.id = (String) map.get(OpsGenieClientConstants.API.ID);
		if (map.containsKey(OpsGenieClientConstants.API.USERNAME))
			this.username = (String) map.get(OpsGenieClientConstants.API.USERNAME);
		if (map.containsKey(OpsGenieClientConstants.API.USER_ID))
			this.userId = (String) map.get(OpsGenieClientConstants.API.USER_ID);
		if (map.containsKey(OpsGenieClientConstants.API.ENABLED))
			this.enabled = (Boolean) map.get(OpsGenieClientConstants.API.ENABLED);
		if (map.containsKey(OpsGenieClientConstants.API.LOOP_AFTER))
			this.loopAfter = (Integer) map.get(OpsGenieClientConstants.API.LOOP_AFTER);

		// condition list
		if (map.containsKey(OpsGenieClientConstants.API.CONDITIONS)) {
			List<NotificationRuleConditions> conditions = new ArrayList<NotificationRuleConditions>();
			List<Map> conditionList = (List<Map>) map.get(OpsGenieClientConstants.API.CONDITIONS);
			for (Map conditionMap : conditionList) {
				NotificationRuleConditions condition = new NotificationRuleConditions();
				condition.fromMap(conditionMap);
				conditions.add(condition);
			}
			setConditions(conditions);
		}
		// step List
		if (map.containsKey(OpsGenieClientConstants.API.STEPS)) {
			List<NotificationRuleStep> steps = new ArrayList<NotificationRuleStep>();
			List<Map> stepList = (List<Map>) map.get(OpsGenieClientConstants.API.STEPS);
			for (Map stepMap : stepList) {
				NotificationRuleStep step = new NotificationRuleStep();
				step.fromMap(stepMap);
				steps.add(step);
			}
			setSteps(steps);
		}

		// condition type
		if (map.containsKey(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE))
			this.conditionMatchType = ConditionMatchType
					.getConditionMatchType((String) map.get(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE));

		// scheduleList
		if (map.containsKey(OpsGenieClientConstants.API.SCHEDULES)) 
			this.schedules = (List<String>) map.get(OpsGenieClientConstants.API.SCHEDULES);
		
		// restirictionList
		if (map.containsKey(OpsGenieClientConstants.API.RESTRICTIONS)) {
			this.restirictions = new ArrayList<NotificationRuleRestriction>();
			if (map.get(OpsGenieClientConstants.API.RESTRICTIONS) instanceof Map) {
				NotificationRuleRestriction newRestiriction = new NotificationRuleRestriction();
				newRestiriction.fromMap((Map) map.get(OpsGenieClientConstants.API.RESTRICTIONS));
				this.restirictions.add(newRestiriction);
			} else if (map.get(OpsGenieClientConstants.API.RESTRICTIONS) instanceof List) {
				List<Map> restirictionList = (List<Map>) map.get(OpsGenieClientConstants.API.RESTRICTIONS);
				for (Map restirictionMap : restirictionList) {
					NotificationRuleRestriction newRestiriction = new NotificationRuleRestriction();
					newRestiriction.fromMap(restirictionMap);
					this.restirictions.add(newRestiriction);
				}
			}
		}
		// notifyBeforeList
		if (map.containsKey(OpsGenieClientConstants.API.NOTIFY_BEFORE)) {
			List<NotifyBefore> notifyBeforeValues = new ArrayList<NotifyBefore>();
			List<String> notifyBeforeList = (List<String>) map.get(OpsGenieClientConstants.API.NOTIFY_BEFORE);
			for (String notifyName : notifyBeforeList) 
				notifyBeforeValues.add(NotifyBefore.getNotifyBefore(notifyName));
			setNotifyBefore(notifyBeforeValues);
		}

	}

	/**
	 * enabled of notificationRule
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Sets enabled of notificationRule
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * name of notificationRule
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of notificationRule
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * applyOrder of notificationRule
	 */
	public Integer getApplyOrder() {
		return applyOrder;
	}

	/**
	 * Sets applyOrder of notificationRule
	 */
	public void setApplyOrder(Integer applyOrder) {
		this.applyOrder = applyOrder;
	}

	/**
	 * actionType of notificationRule
	 */
	public ActionType getActionType() {
		return actionType;
	}

	/**
	 * Sets actionType of notificationRule
	 */
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	/**
	 * conditionMatchType of notificationRule
	 */
	public ConditionMatchType getConditionMatchType() {
		return conditionMatchType;
	}

	/**
	 * Sets conditionMatchType of notificationRule
	 */
	public void setConditionMatchType(ConditionMatchType conditionMatchType) {
		this.conditionMatchType = conditionMatchType;
	}

	/**
	 * loopAfter of notificationRule
	 */
	public Integer getLoopAfter() {
		return loopAfter;
	}

	/**
	 * Sets loopAfter of notificationRule
	 */
	public void setLoopAfter(Integer loopAfter) {
		this.loopAfter = loopAfter;
	}

	/**
	 * stepList of notificationRule
	 */
	public List<NotificationRuleStep> getSteps() {
		return steps;
	}

	/**
	 * Sets stepList of notificationRule
	 */
	public void setSteps(List<NotificationRuleStep> steps) {
		this.steps = steps;
	}

	/**
	 * conditionList of notificationRule
	 */
	public List<NotificationRuleConditions> getConditions() {
		return conditions;
	}

	/**
	 * Sets conditionList of notificationRule
	 */
	public void setConditions(List<NotificationRuleConditions> conditions) {
		this.conditions = conditions;
	}

	/**
	 * scheduleList of notificationRule
	 */
	public List<String> getSchedules() {
		return schedules;
	}

	/**
	 * Sets scheduleList of notificationRule
	 */
	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	/**
	 * notificationList of notificationRule
	 */
	public List<NotificationRuleRestriction> getRestirictions() {
		return restirictions;
	}

	/**
	 * Sets notificationList of notificationRule
	 */
	public void setRestirictions(List<NotificationRuleRestriction> restirictions) {
		this.restirictions = restirictions;
	}

	/**
	 * notifyBefore of notificationRule
	 */
	public List<NotifyBefore> getNotifyBefore() {
		return notifyBefore;
	}

	/**
	 * Sets notifyBefore of notificationRule
	 */
	public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
		this.notifyBefore = notifyBefore;
	}

}
