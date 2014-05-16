package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Alert Recipient bean
 *
 * @author Mustafa Sener
 * @version 25.06.2013 10:44
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
    public Map<String,Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.METHOD, getMethod());
        json.put(ClientConstants.API.USERNAME, getUsername());
        json.put(ClientConstants.API.STATE, getState());
        json.put(ClientConstants.API.STATE_CHANGED_AT, getStateChangedAt());
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) {
        setMethod((String) map.get(ClientConstants.API.METHOD));
        setUsername((String) map.get(ClientConstants.API.USERNAME));
        setState((String) map.get(ClientConstants.API.STATE));
        setStateChangedAt(((Number) map.get(ClientConstants.API.STATE_CHANGED_AT)).longValue());
    }
}
