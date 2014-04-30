package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Alert bean
 */
public class AlertLog implements IBean {
    private String log;
    private String owner;
    private String logType;
    private long createdAt;

    /**
     * Gets log message
     */
    public String getLog() {
        return log;
    }

    /**
     * Sets log message
     */
    public void setLog(String log) {
        this.log = log;
    }


    /**
     * Gets log type
     */
    public String getLogType() {
        return logType;
    }

    /**
     * Sets log type
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * The owner of the alert log
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the alert log
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * The creation time of alert in nanoseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the alert in nanoseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(ClientConstants.API.CREATED_AT, getCreatedAt());
        json.put(ClientConstants.API.OWNER, getOwner());
        json.put(ClientConstants.API.LOG, getLog());
        json.put(ClientConstants.API.LOG_TYPE, getLogType());
        return json;
    }

    @Override
    public void fromMap(Map map) {
        setOwner((String) map.get(ClientConstants.API.OWNER));
        setLogType((String) map.get(ClientConstants.API.LOG_TYPE));
        setLog((String) map.get(ClientConstants.API.LOG));
        setCreatedAt(((Number) map.get(ClientConstants.API.CREATED_AT)).longValue());
    }
}
