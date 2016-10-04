package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Contact bean
 */
public class NotificationRuleStep extends Bean {
    private Method method;
    private String to;
	private String id;
	private Integer sendAfter;
	private Boolean enabled;

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
