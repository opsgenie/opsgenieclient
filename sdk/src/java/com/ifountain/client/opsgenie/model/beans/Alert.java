package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Alert bean
 */
public class Alert implements IBean {
    public enum Status{
        open,
        closed
    }
    private String id;
    private String alias;
    private String message;
    private String description;
    private String source;
    private String entity;
    private Map<String, String> systemData;
    private String tinyId;
    private Status status;
    private String owner;
    private int count;
    private long createdAt;
    private long updatedAt;
    private boolean isSeen;
    private boolean acknowledged;
    private List<String> recipients;
    private List<String> tags;
    private List<String> actions;
    private Map<String, String> details;

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
     * Alert text in long form.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the alert text in long form.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Source of the 
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the 
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * The entity that the alert is related to.
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Sets the entitiy that the alert is related to.
     */
    public void setEntity(String entity) {
        this.entity = entity;
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
     * The owner of the 
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the 
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

    /**
     * The user names of individuval users or group names that will receive the 
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets the user names of individuval users or group names that will receive the 
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * List of alert labels.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the list of alert labels.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * List of actions that can be executed.
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * Sets the list of actions that can be executed.
     */
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    /**
     * Additional alert properties.
     */
    public Map<String, String> getDetails() {
        return details;
    }

    /**
     * Sets the additional alert properties.
     */
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    @Override
    public Map toMap() {
        Map json = new HashMap();
        json.put(ClientConstants.API.ID, getId());
        json.put(ClientConstants.API.RECIPIENTS, getRecipients());
        json.put(ClientConstants.API.MESSAGE, getMessage());
        json.put(ClientConstants.API.SYSTEM_DATA, getSystemData());
        json.put(ClientConstants.API.ALIAS, getAlias());
        json.put(ClientConstants.API.SOURCE, getSource());
        json.put(ClientConstants.API.ENTITY, getEntity());
        json.put(ClientConstants.API.DESCRIPTION, getDescription());
        json.put(ClientConstants.API.TAGS, getTags());
        json.put(ClientConstants.API.ACTIONS, getActions());
        json.put(ClientConstants.API.DETAILS, getDetails());
        json.put(ClientConstants.API.CREATED_AT, getCreatedAt());
        json.put(ClientConstants.API.UPDATED_AT, getUpdatedAt());
        json.put(ClientConstants.API.COUNT, getCount());
        json.put(ClientConstants.API.IS_SEEN, isSeen());
        json.put(ClientConstants.API.TINY_ID, getTinyId());
        json.put(ClientConstants.API.STATUS, getStatus().name());
        json.put(ClientConstants.API.OWNER, getOwner());
        json.put(ClientConstants.API.ACKNOWLEDGED, isAcknowledged());
        return json;
    }

    @Override
    public void fromMap(Map map) {
        setId((String) map.get(ClientConstants.API.ID));
        setMessage((String) map.get(ClientConstants.API.MESSAGE));
        setAlias((String) map.get(ClientConstants.API.ALIAS));
        setDescription((String) map.get(ClientConstants.API.DESCRIPTION));
        setSource((String) map.get(ClientConstants.API.SOURCE));
        setEntity((String) map.get(ClientConstants.API.ENTITY));
        status = Status.valueOf(((String) map.get(ClientConstants.API.STATUS)).toLowerCase());
        setOwner((String) map.get(ClientConstants.API.OWNER));
        setSystemData((Map<String, String>) map.get(ClientConstants.API.SYSTEM_DATA));
        setTinyId((String) map.get(ClientConstants.API.TINY_ID));
        setSeen((Boolean) map.get(ClientConstants.API.IS_SEEN));
        setAcknowledged((Boolean) map.get(ClientConstants.API.ACKNOWLEDGED));
        setTags((List<String>) map.get(ClientConstants.API.TAGS));
        setActions((List<String>) map.get(ClientConstants.API.ACTIONS));
        setRecipients((List<String>) map.get(ClientConstants.API.RECIPIENTS));
        setDetails((Map<String, String>) map.get(ClientConstants.API.DETAILS));
        setCreatedAt(((Number) map.get(ClientConstants.API.CREATED_AT)).longValue());
        setUpdatedAt(((Number) map.get(ClientConstants.API.UPDATED_AT)).longValue());
        if(map.containsKey(ClientConstants.API.COUNT)){
            setCount(((Number) map.get(ClientConstants.API.COUNT)).intValue());
        }
    }
}
