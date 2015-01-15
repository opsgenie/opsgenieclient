package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Alert log bean
 */
public class AlertLog implements IBean{
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
     * The creation time of log in milliseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the log in milliseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(OpsGenieClientConstants.API.CREATED_AT, getCreatedAt());
        json.put(OpsGenieClientConstants.API.OWNER, getOwner());
        json.put(OpsGenieClientConstants.API.LOG, getLog());
        json.put(OpsGenieClientConstants.API.LOG_TYPE, getLogType());
        return json;
    }

    @Override
    public void fromMap(Map map) {
        setOwner((String) map.get(OpsGenieClientConstants.API.OWNER));
        setLogType((String) map.get(OpsGenieClientConstants.API.LOG_TYPE));
        setLog((String) map.get(OpsGenieClientConstants.API.LOG));
        setCreatedAt(((Number) map.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
    }
}
