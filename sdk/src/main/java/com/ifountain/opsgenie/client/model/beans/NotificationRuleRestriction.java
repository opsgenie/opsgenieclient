package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
/**
 * NotificationRuleRestriction bean
 */
public class NotificationRuleRestriction implements IBean{

    
    private Integer endHour;
    private Integer endMinute;
    private Integer startHour;
    private Integer startMinute;
    private DayOfWeek startDay;
    private DayOfWeek endDay;
    
    
	/**
     * endHour of NotificationRuleRestriction
     */
    public Integer getEndHour() {
		return endHour;
	}
	/**
     * Sets endHour of NotificationRuleRestriction
     */
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
	/**
     * endMinute of NotificationRuleRestriction
     */
	public Integer getEndMinute() {
		return endMinute;
	}
	/**
     * Sets endMinute of NotificationRuleRestriction
     */
	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}
	/**
     * startHour of NotificationRuleRestriction
     */
	public Integer getStartHour() {
		return startHour;
	}
	/**
     * Sets startHour of NotificationRuleRestriction
     */
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	/**
     * startMinute of NotificationRuleRestriction
     */
	public Integer getStartMinute() {
		return startMinute;
	}
	/**
     * Sets startMinute of NotificationRuleRestriction
     */
	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}
	/**
     * startDay of NotificationRuleRestriction
     */
	public DayOfWeek getStartDay() {
		return startDay;
	}
	/**
     * Sets startDay of NotificationRuleRestriction
     */
	public void setStartDay(DayOfWeek startDay) {
		this.startDay = startDay;
	}
	/**
     * endDay of NotificationRuleRestriction
     */
	public DayOfWeek getEndDay() {
		return endDay;
	}
	/**
     * Sets endDay of NotificationRuleRestriction
     */
	public void setEndDay(DayOfWeek endDay) {
		this.endDay = endDay;
	}
	
	@Override
	public Map<String, Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
		if(getEndHour() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_END_HOUR, getEndHour());
		if(getStartDay() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_START_DAY, getStartDay());
		if(getStartHour() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_START_HOUR, getStartHour());
		if(getEndDay() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_END_DAY, getEndDay().name());
		if(getStartMinute() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_START_MINUTE, getStartMinute());
		if(getEndMinute() != null)
			json.put(OpsGenieClientConstants.API.RESTRICTION_END_MINUTE, getEndMinute());
        return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_END_HOUR))
			this.endHour = (Integer) map.get(OpsGenieClientConstants.API.RESTRICTION_END_HOUR);
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_END_MINUTE))
			this.endMinute = (Integer) map.get(OpsGenieClientConstants.API.RESTRICTION_END_MINUTE);
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_END_DAY))
			this.endDay = DayOfWeek.valueOf((String) map.get(OpsGenieClientConstants.API.RESTRICTION_END_DAY));
			
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_START_HOUR))
			this.startHour = (Integer) map.get(OpsGenieClientConstants.API.RESTRICTION_START_HOUR);
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_START_MINUTE))
			this.startMinute = (Integer) map.get(OpsGenieClientConstants.API.RESTRICTION_START_MINUTE);
		if(map.containsKey(OpsGenieClientConstants.API.RESTRICTION_START_DAY))
			this.startDay = DayOfWeek.valueOf((String) map.get(OpsGenieClientConstants.API.RESTRICTION_START_DAY));
		
		
	}
   

}
