package com.ifountain.opsgenie.client.model.beans;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

/**
 * Contact bean
 */
public class Contact implements IBean {

	public static enum Method {
		EMAIL(OpsGenieClientConstants.API.EMAIL), 
		SMS(OpsGenieClientConstants.API.SMS),
		VOICE(OpsGenieClientConstants.API.VOICE);
		private String value;
		private Method(String value) {
			this.value = value;
		}
		public String value() {
			return value;
		}
	}

	private String id;
	private Method method;
	private String to;
	private Boolean enabled;
	private String disabledReason;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> json = new HashMap<String, Object>();
		if (getMethod() != null)
			json.put(OpsGenieClientConstants.API.METHOD, getMethod().value());
		json.put(OpsGenieClientConstants.API.ENABLED, getEnabled());
		json.put(OpsGenieClientConstants.API.TO, to);
		json.put(OpsGenieClientConstants.API.DISABLED_REASON, disabledReason);
		return json;
	}

	@Override
	public void fromMap(Map map) throws ParseException {
		setId((String) map.get(OpsGenieClientConstants.API.ID));
		setTo((String) map.get(OpsGenieClientConstants.API.TO));
		setEnabled((Boolean) map.get(OpsGenieClientConstants.API.ENABLED));
		setDisabledReason((String) map.get(OpsGenieClientConstants.API.DISABLED_REASON));
		if (map.containsKey(OpsGenieClientConstants.API.METHOD)) {
			String methodName = (String) map.get(OpsGenieClientConstants.API.METHOD);
			for (Method method : Method.values())
				if (method.value().equals(methodName)) {
					setMethod(method);
					break;
				}
		}
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
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

	public String getDisabledReason() {
		return disabledReason;
	}

	public void setDisabledReason(String disabledReason) {
		this.disabledReason = disabledReason;
	}

}
