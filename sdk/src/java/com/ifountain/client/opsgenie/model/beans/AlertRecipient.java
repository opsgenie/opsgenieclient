package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.model.IBean;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Alert bean
 */
public class AlertRecipient implements IBean {
    private String username;
    private String state;
    private long stateChangedAt;
    private String method;

    /**
     * Returns username of alert recipient
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of alert recipient
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns state of alert recipient
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state of alert recipient
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns state change date of alert recipient
     */
    public long getStateChangedAt() {
        return stateChangedAt;
    }

    /**
     * Sets state change date of alert recipient
     */
    public void setStateChangedAt(long stateChangedAt) {
        this.stateChangedAt = stateChangedAt;
    }


    /**
     * Returns method of alert recipient
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method of alert recipient
     */
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.METHOD, getMethod());
        json.put(OpsGenieClientConstants.API.USERNAME, getUsername());
        json.put(OpsGenieClientConstants.API.STATE, getState());
        json.put(OpsGenieClientConstants.API.STATE_CHANGED_AT, getStateChangedAt());
        return json;
    }

    @Override
    public void fromMap(Map map) {
        setMethod((String) map.get(OpsGenieClientConstants.API.METHOD));
        setUsername((String) map.get(OpsGenieClientConstants.API.USERNAME));
        setState((String) map.get(OpsGenieClientConstants.API.STATE));
        setStateChangedAt(((Number) map.get(OpsGenieClientConstants.API.STATE_CHANGED_AT)).longValue());
    }
}
