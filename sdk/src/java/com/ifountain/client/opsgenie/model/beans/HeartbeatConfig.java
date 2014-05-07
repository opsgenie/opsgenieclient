package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HeartbeatConfig bean
 */
public class HeartbeatConfig implements IBean {
    private Boolean enabled;
    private String message;
    private List<String> recipients;


    /**
     * Returns state of
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the state of
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
     * Recipients  of heartbeat alert
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /*
    * Sets recipients  of heartbeat alert
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.ENABLED, enabled);
        json.put(ClientConstants.API.MESSAGE, message);
        json.put(ClientConstants.API.RECIPIENTS, recipients);
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> resp) {
        setEnabled((Boolean) resp.get(ClientConstants.API.ENABLED));
        setMessage((String) resp.get(ClientConstants.API.MESSAGE));
        setRecipients((List<String>) resp.get(ClientConstants.API.RECIPIENTS));
    }
}
