package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
/**
 * Account bean
 */
public class Account implements IBean{
	
    private Integer userCount;
    private String name;
    private Boolean isYearly;
    private String planName;
    private Integer maxUserCount;
    
    
	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}
   
	@Override
	public void fromMap(Map map) throws ParseException {
		if(map.get(OpsGenieClientConstants.API.USER_COUNT) != null)
			setUserCount((Integer) map.get(OpsGenieClientConstants.API.USER_COUNT));
        setName((String) map.get(OpsGenieClientConstants.API.NAME));
        if(map.get(OpsGenieClientConstants.API.PLAN) != null){
        	Map planMap = (Map) map.get(OpsGenieClientConstants.API.PLAN);
            setIsYearly((Boolean) planMap.get(OpsGenieClientConstants.API.IS_YEARLY));
            setPlanName((String) planMap.get(OpsGenieClientConstants.API.NAME));
    		if(planMap.get(OpsGenieClientConstants.API.MAX_USER_COUNT) != null)
    			setMaxUserCount((Integer) map.get(OpsGenieClientConstants.API.USER_COUNT));
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
