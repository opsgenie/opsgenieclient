package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;
/**
 * Contact bean
 */
public class NotificationRuleStep implements IBean{
    private Method method;
    private String to;
    private String id;
    private Integer sendAfter;
    private Boolean enabled;

    /**
     * to of NotificationRuleStep
     */
	public String getTo() {
		return to;
	}
    /**
     * Sets to of NotificationRuleStep
     */
	public void setTo(String to) {
		this.to = to;
	}
    /**
     * Id of NotificationRuleStep
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of NotificationRuleStep
     */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Map<String, Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.METHOD, getMethod() != null ? getMethod().name():null);
    	json.put(OpsGenieClientConstants.API.TO, getTo());
    	json.put(OpsGenieClientConstants.API.ID, getId());
    	json.put(OpsGenieClientConstants.API.SEND_AFTER, getSendAfter());
        json.put(OpsGenieClientConstants.API.ENABLED, getEnabled());
        return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
        setId((String) map.get(OpsGenieClientConstants.API.ID));
        setTo((String) map.get(OpsGenieClientConstants.API.TO));
        setEnabled((Boolean) map.get(OpsGenieClientConstants.API.ENABLED));
        setSendAfter(((Integer) map.get(OpsGenieClientConstants.API.SEND_AFTER)));
        if(map.containsKey(OpsGenieClientConstants.API.METHOD))
        	setMethod(Contact.Method.valueOf(((String) map.get(OpsGenieClientConstants.API.METHOD)).toLowerCase()));
	}
    /**
     * enabled of NotificationRuleStep
     */
	public Boolean getEnabled() {
		return enabled;
	}
    /**
     * Sets enabled of NotificationRuleStep
     */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
    /**
     * method of NotificationRuleStep
     */
	public Method getMethod() {
		return method;
	}
    /**
     * Sets method of NotificationRuleStep
     */
	public void setMethod(Method method) {
		this.method = method;
	}
    /**
     * sendAfter of NotificationRuleStep
     */
	public Integer getSendAfter() {
		return sendAfter;
	}
    /**
     * Sets sendAfter of NotificationRuleStep
     */
	public void setSendAfter(Integer sendAfter) {
		this.sendAfter = sendAfter;
	}
    


}
