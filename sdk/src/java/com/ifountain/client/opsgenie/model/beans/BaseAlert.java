package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.Map;

/**
 * BaseIncident Bean
 * @author Tuba Ozturk
 * @version 6.5.2014 11:33
 */
public class BaseAlert implements IBean{
    public enum Status{
        open,
        closed
    }
    private String id;
    private String alias;
    private String message;
    private Map<String, String> systemData;
    private String tinyId;
    private Status status;
    private int count;
    private long createdAt;
    private long updatedAt;
    private boolean isSeen;
    private boolean acknowledged;

    /**
     * Returns type of integration which created alert
     * @return
     */
    public Map<String, String> getSystemData() {
        return systemData;
    }

    /**
     * Sets alert system data. System data contains system parameters such as ackTime, closeTime, integrationType, etc...
     * @param systemData
     */
    public void setSystemData(Map<String, String> systemData) {
        this.systemData = systemData;
    }

    /**
     * The tiny id of the
     */
    public String getTinyId() {
        return tinyId;
    }

    /**
     * Sets the tiny id of the
     */
    public void setTinyId(String tinyId) {
        this.tinyId = tinyId;
    }

    /**
     * The id of the
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * User defined identifier of the
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Alert text.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the alert text.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The status of the
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the
     */
    public void setStatus(Status status) {
        this.status = status;
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

    /**
     * Last update time of alert in nanoseconds.
     */
    public long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets last update time of the alert in nanoseconds.
     */
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Count of the
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of the
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Indicates whether the alert is seen by any recipient.
     */
    public boolean isSeen() {
        return isSeen;
    }

    /**
     * Sets seen state of the
     */
    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    /**
     * The acknowledged state of the
     */
    public boolean isAcknowledged() {
        return acknowledged;
    }

    /**
     * Sets the acknowledged state of the
     */
    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String,Object> json = new HashMap<String,Object>();
        json.put(ClientConstants.API.ID, getId());
        json.put(ClientConstants.API.MESSAGE, getMessage());
        json.put(ClientConstants.API.SYSTEM_DATA, getSystemData());
        json.put(ClientConstants.API.ALIAS, getAlias());
        json.put(ClientConstants.API.CREATED_AT, getCreatedAt());
        json.put(ClientConstants.API.UPDATED_AT, getUpdatedAt());
        json.put(ClientConstants.API.COUNT, getCount());
        json.put(ClientConstants.API.IS_SEEN, isSeen());
        json.put(ClientConstants.API.TINY_ID, getTinyId());
        json.put(ClientConstants.API.STATUS, getStatus().name());
        json.put(ClientConstants.API.ACKNOWLEDGED, isAcknowledged());
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) {
        setId((String) map.get(ClientConstants.API.ID));
        setMessage((String) map.get(ClientConstants.API.MESSAGE));
        setAlias((String) map.get(ClientConstants.API.ALIAS));
        status = Status.valueOf(((String) map.get(ClientConstants.API.STATUS)).toLowerCase());
        setSystemData((Map<String, String>) map.get(ClientConstants.API.SYSTEM_DATA));
        setTinyId((String) map.get(ClientConstants.API.TINY_ID));
        setSeen((Boolean) map.get(ClientConstants.API.IS_SEEN));
        setAcknowledged((Boolean) map.get(ClientConstants.API.ACKNOWLEDGED));
        setCreatedAt(((Number) map.get(ClientConstants.API.CREATED_AT)).longValue());
        setUpdatedAt(((Number) map.get(ClientConstants.API.UPDATED_AT)).longValue());
        if(map.containsKey(ClientConstants.API.COUNT)){
            setCount(((Number) map.get(ClientConstants.API.COUNT)).intValue());
        }
    }
}
