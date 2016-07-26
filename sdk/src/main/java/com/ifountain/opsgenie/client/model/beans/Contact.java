package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
/**
 * Contact bean
 */
public class Contact implements IBean{

    public static enum Method{
        email,
        sms,
        voice
    }

    private String id;
    private String userId;
    private String username;
    private Method method;
    private String to;
    private Boolean enabled;
    private String disabledReason;
    
    
    /**
     * userId of contact
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of contact
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * username of contact
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets username of contact
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * method of contact
     */
	public Method getMethod() {
		return method;
	}
    /**
     * Sets method of contact
     */
	public void setMethod(Method method) {
		this.method = method;
	}
    /**
     * to of contact
     */
	public String getTo() {
		return to;
	}
    /**
     * Sets to of contact
     */
	public void setTo(String to) {
		this.to = to;
	}
    /**
     * Id of contact
     */
	public String getId() {
		return id;
	}
    /**
     * Sets Id of contact
     */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Map<String, Object> toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.USERNAME, username);
        json.put(OpsGenieClientConstants.API.USER_ID, userId);
        json.put(OpsGenieClientConstants.API.METHOD, getMethod() != null ? getMethod().name():null);
        json.put(OpsGenieClientConstants.API.ENABLED, getEnabled());
    	json.put(OpsGenieClientConstants.API.TO, to);
    	json.put(OpsGenieClientConstants.API.DISABLED_REASON, disabledReason);
        return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
        setId((String) map.get(OpsGenieClientConstants.API.ID));
        setUsername((String) map.get(OpsGenieClientConstants.API.USERNAME));
        setUserId((String) map.get(OpsGenieClientConstants.API.USER_ID));
        setTo((String) map.get(OpsGenieClientConstants.API.TO));
        setEnabled((Boolean) map.get(OpsGenieClientConstants.API.ENABLED));
        setDisabledReason((String) map.get(OpsGenieClientConstants.API.DISABLED_REASON));
        if(map.containsKey(OpsGenieClientConstants.API.METHOD)){
        	setMethod(Contact.Method.valueOf(((String) map.get(OpsGenieClientConstants.API.METHOD)).toLowerCase()));
        }
	}
    /**
     * enabled of contact
     */
	public Boolean getEnabled() {
		return enabled;
	}
    /**
     * Sets enabled of contact
     */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
    /**
     * disabledReason of contact
     */
	public String getDisabledReason() {
		return disabledReason;
	}
    /**
     * Sets disabledReason of contact
     */
	public void setDisabledReason(String disabledReason) {
		this.disabledReason = disabledReason;
	}


}
