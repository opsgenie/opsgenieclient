package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 2:01 PM
 */
public class TeamLog implements IBean{
    private String log;
    private String owner;
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
     * The owner of the team log
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the team log
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
        return json;
    }

    @Override
    public void fromMap(Map map) {
        setOwner((String) map.get(OpsGenieClientConstants.API.OWNER));
        setLog((String) map.get(OpsGenieClientConstants.API.LOG));
        setCreatedAt(((Number) map.get(OpsGenieClientConstants.API.CREATED_AT)).longValue());
    }
}
