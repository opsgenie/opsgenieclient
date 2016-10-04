package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.List;
import java.util.Map;

/**
 * HeartbeatConfig bean
 */
@Deprecated
public class HeartbeatConfig extends Bean {
    private Boolean enabled;
    private String message;
    private List<String> recipients;

    /**
     * Enable state
     *
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable state
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * message of heartbeat alert
     */
    public String getMessage() {
        return message;
    }

    /*
     * Sets message of heartbeat alert
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * Recipients of heartbeat alert
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /*
     * Sets recipients of heartbeat alert
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public void fromMap(Map resp) {
        setEnabled((Boolean) resp.get(OpsGenieClientConstants.API.ENABLED));
        setMessage((String) resp.get(OpsGenieClientConstants.API.MESSAGE));
        setRecipients((List<String>) resp.get(OpsGenieClientConstants.API.RECIPIENTS));
    }
}
