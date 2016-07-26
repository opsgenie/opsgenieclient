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
		NEW_ALERT(OpsGenieClientConstants.API.NEW_ALERT),
		ACKNOWLEDGED_ALERT(OpsGenieClientConstants.API.ACKNOWLEDGED_ALERT),
		CLOSED_ALERT(OpsGenieClientConstants.API.CLOSED_ALERT),
		SCHEDULE_START(OpsGenieClientConstants.API.SCHEDULE_START),
		RENOTIFIED_ALERT(OpsGenieClientConstants.API.RENOTIFIED_ALERT),
		ASSIGNED_ALERT(OpsGenieClientConstants.API.ASSIGNED_ALERT),
		ADD_NOTE(OpsGenieClientConstants.API.ADD_NOTE),
		SCHEDULE_END(OpsGenieClientConstants.API.SCHEDULE_END),
		INCOMING_CALL_ROUTING(OpsGenieClientConstants.API.INCOMING_CALL_ROUTING);
		
		private String value;
		private ActionType(String value) {
			this.value = value;
		}
		public String value() {
			return value;
		}
	}

	public static enum ConditionMatchType {
		MATCH_ALL(OpsGenieClientConstants.API.CONDITION_MATCH_ALL), 
		MATCH_ALL_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ALL_CONDITIONS), 
		MATCH_ANY_CONDITIONS(OpsGenieClientConstants.API.CONDITION_MATCH_ANY_CONDITIONS);
		
		private String value;
		private ConditionMatchType(String value) {
			this.value = value;
		}
		public String value() {
			return value;
		}
	}

	public static enum NotifyBefore {
		JUST_BEFORE(OpsGenieClientConstants.API.NOTIFY_JUST_BEFORE), 
		ONE_HOUR(OpsGenieClientConstants.API.NOTIFY_ONE_HOUR),
		FIFTEEN_MINUTES(OpsGenieClientConstants.API.NOTIFY_FIFTEEN_MINUTE),
		ONE_DAY(OpsGenieClientConstants.API.NOTIFY_ONE_DAY);
		
		private String value;
		private NotifyBefore(String value) {
			this.value = value;
		}
		public String value() {
			return value;
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

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> json = new HashMap<String, Object>();
		if (getActionType() != null)
			json.put(OpsGenieClientConstants.API.ACTION_TYPE, getActionType().value);
		json.put(OpsGenieClientConstants.API.APPLY_ORDER, getApplyOrder());
		if (getSchedules() != null)
			json.put(OpsGenieClientConstants.API.SCHEDULES, getSchedules());
		json.put(OpsGenieClientConstants.API.NAME, getName());
		if (getNotifyBefore() != null) {
			List<String> notifyStringList = new ArrayList<String>();
			for (NotifyBefore notifyBefore : getNotifyBefore())
				notifyStringList.add(notifyBefore.value);
			json.put(OpsGenieClientConstants.API.NOTIFY_BEFORE, notifyStringList);
		}
		if (getRestirictions() != null) {
			List<Map> restirictionList = new ArrayList<Map>();
			for (NotificationRuleRestriction rest : getRestirictions())
				restirictionList.add(rest.toMap());
			json.put(OpsGenieClientConstants.API.RESTRICTIONS, restirictionList);
		}
		json.put(OpsGenieClientConstants.API.ID, getId());
		if (getConditions() != null) {
			List<Map> conditionMapList = new ArrayList<Map>();
			for (NotificationRuleConditions cond : getConditions())
				conditionMapList.add(cond.toMap());
			json.put(OpsGenieClientConstants.API.CONDITIONS, conditionMapList);
		}
		if (getSteps() != null) {
			List<Map> stepMapList = new ArrayList<Map>();
			for (NotificationRuleStep step : getSteps())
				stepMapList.add(step.toMap());
			json.put(OpsGenieClientConstants.API.STEPS, stepMapList);
		}
		json.put(OpsGenieClientConstants.API.ENABLED, getEnabled());
		if (getConditionMatchType() != null)
			json.put(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE, getConditionMatchType().value);
		if (getLoopAfter() != null)
			json.put(OpsGenieClientConstants.API.LOOP_AFTER, getLoopAfter());
		return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		// Action Type
		if (map.containsKey(OpsGenieClientConstants.API.ACTION_TYPE)){
			String actionTypeName = (String) map.get(OpsGenieClientConstants.API.ACTION_TYPE);
			for (ActionType actionType : ActionType.values()) 
				if(actionType.value.equals(actionTypeName)){
					this.actionType = actionType;
					break;
				}
		}

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
		if (map.containsKey(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE)) {
			String conditionMatchTypeName = (String) map.get(OpsGenieClientConstants.API.CONDITION_MATCH_TYPE);
			for (ConditionMatchType cndMatchType : ConditionMatchType.values())
				if (cndMatchType.value.equals(conditionMatchTypeName)) {
					this.conditionMatchType = cndMatchType;
					break;
				}
		}

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
			for (String notifyName : notifyBeforeList) {
				for (NotifyBefore nb : NotifyBefore.values())
					if (nb.value.equals(notifyName)) {
						notifyBeforeValues.add(nb);
						break;
					}
			}
			setNotifyBefore(notifyBeforeValues);
		}

	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getApplyOrder() {
		return applyOrder;
	}

	public void setApplyOrder(Integer applyOrder) {
		this.applyOrder = applyOrder;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public ConditionMatchType getConditionMatchType() {
		return conditionMatchType;
	}

	public void setConditionMatchType(ConditionMatchType conditionMatchType) {
		this.conditionMatchType = conditionMatchType;
	}

	public Integer getLoopAfter() {
		return loopAfter;
	}

	public void setLoopAfter(Integer loopAfter) {
		this.loopAfter = loopAfter;
	}

	public List<NotificationRuleStep> getSteps() {
		return steps;
	}

	public void setSteps(List<NotificationRuleStep> steps) {
		this.steps = steps;
	}

	public List<NotificationRuleConditions> getConditions() {
		return conditions;
	}

	public void setConditions(List<NotificationRuleConditions> conditions) {
		this.conditions = conditions;
	}

	public List<String> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	public List<NotificationRuleRestriction> getRestirictions() {
		return restirictions;
	}

	public void setRestirictions(List<NotificationRuleRestriction> restirictions) {
		this.restirictions = restirictions;
	}

	public List<NotifyBefore> getNotifyBefore() {
		return notifyBefore;
	}

	public void setNotifyBefore(List<NotifyBefore> notifyBefore) {
		this.notifyBefore = notifyBefore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
