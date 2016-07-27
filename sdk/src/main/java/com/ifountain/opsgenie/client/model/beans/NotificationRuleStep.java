package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Contact bean
 */
public class NotificationRuleStep implements IBean {
	private Method method;
	private String to;
	private String id;
	private Integer sendAfter;
	private Boolean enabled;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> json = new HashMap<String, Object>();
		if(getMethod() != null)
			json.put(OpsGenieClientConstants.API.METHOD, getMethod().value());
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
		if (map.containsKey(OpsGenieClientConstants.API.METHOD)){
			String methodName = (String) map.get(OpsGenieClientConstants.API.METHOD);
			for (Method method : Method.values())
				if (method.value().equals(methodName)) {
					setMethod(method);
					break;
				}
		}
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Integer getSendAfter() {
		return sendAfter;
	}

	public void setSendAfter(Integer sendAfter) {
		this.sendAfter = sendAfter;
	}

}
