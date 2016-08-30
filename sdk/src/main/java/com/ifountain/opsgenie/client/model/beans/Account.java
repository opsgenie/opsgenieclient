package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Use Jackson
 * Account bean
 */
public class Account implements IBean {

	private Integer userCount;
	private String name;
	private Boolean isYearly;
	private String planName;
	private Integer maxUserCount;

	@Override
	public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        if(getUserCount() != null)
        	json.put(OpsGenieClientConstants.API.USER_COUNT, getUserCount());
        if(getName() != null)
        	json.put(OpsGenieClientConstants.API.NAME, getName());
        Map planmap = new HashMap<String, String>();
        if(getIsYearly() != null)
        	planmap.put(OpsGenieClientConstants.API.IS_YEARLY, getIsYearly());
        if(getPlanName() != null)
        	planmap.put(OpsGenieClientConstants.API.NAME, getPlanName());
        if(getMaxUserCount() != null)
        	planmap.put(OpsGenieClientConstants.API.MAX_USER_COUNT, getMaxUserCount());
    	json.put(OpsGenieClientConstants.API.PLAN, planmap);
        return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		if (map.get(OpsGenieClientConstants.API.USER_COUNT) != null)
			setUserCount((Integer) map.get(OpsGenieClientConstants.API.USER_COUNT));
		setName((String) map.get(OpsGenieClientConstants.API.NAME));
		if (map.get(OpsGenieClientConstants.API.PLAN) != null) {
			Map planMap = (Map) map.get(OpsGenieClientConstants.API.PLAN);
			setIsYearly((Boolean) planMap.get(OpsGenieClientConstants.API.IS_YEARLY));
			setPlanName((String) planMap.get(OpsGenieClientConstants.API.NAME));
			if (planMap.get(OpsGenieClientConstants.API.MAX_USER_COUNT) != null)
				setMaxUserCount((Integer) planMap.get(OpsGenieClientConstants.API.MAX_USER_COUNT));
		}
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsYearly() {
		return isYearly;
	}

	public void setIsYearly(Boolean isYearly) {
		this.isYearly = isYearly;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getMaxUserCount() {
		return maxUserCount;
	}

	public void setMaxUserCount(Integer maxUserCount) {
		this.maxUserCount = maxUserCount;
	}
}
