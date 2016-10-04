package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Contact bean
 */
public class Contact extends Bean {

	public static enum Method {
        EMAIL(OpsGenieClientConstants.API.EMAIL), SMS(OpsGenieClientConstants.API.SMS),
        VOICE(OpsGenieClientConstants.API.VOICE),
        @Deprecated
        MOBILE_APP(OpsGenieClientConstants.API.MOBILE_APP);
        private String value;

		private Method(String value) {
			this.value = value;
		}

        @JsonValue
        public String value() {
            return value;
		}

        @JsonCreator
        public static Method fromName(String value) {
            if (value == null)
                return null;
            for (Method method : Method.values()) {
                if (method.value().toLowerCase().equals(value.toLowerCase()))
                    return method;
            }
            return null;
        }
    }

	private String id;
	private Method method;
	private String to;
	private Boolean enabled;
	private String disabledReason;

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
