package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Alert bean
 */
public class Alert extends Bean {
    public enum Status {
        open, closed
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
    private List<String> teams;
    private List<String> tags;
    private List<String> actions;
    private Map<String, String> details;

    /**
     * Returns type of integration which created alert.
     *
     * @return Map<String, String>
     */
    public Map<String, String> getSystemData() {
        return systemData;
    }

    /**
     * Sets alert system data. System data contains system parameters such as
     * ackTime, closeTime, integrationType, etc...
     *
     * @param systemData
     */
    public void setSystemData(Map<String, String> systemData) {
        this.systemData = systemData;
    }

    /**
     * The tiny id of the alert.
     */
    public String getTinyId() {
        return tinyId;
    }

    /**
     * Sets the tiny id of the alert.
     */
    public void setTinyId(String tinyId) {
        this.tinyId = tinyId;
    }

    /**
     * The id of the alert.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the alert.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * User defined identifier of the alert.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert.
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
     * Source of the alert.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the alert.
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
     * The status of the alert.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the alert.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * The owner of the alert.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the alert.
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
     * Count of the alert.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of the alert.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Indicates whether the alert is seen by any recipient.
     */
    @JsonProperty("isSeen")
    public boolean isSeen() {
        return isSeen;
    }

    /**
     * Sets seen state of the alert.
     */
    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    /**
     * The acknowledged state of the alert.
     */
    public boolean isAcknowledged() {
        return acknowledged;
    }

    /**
     * Sets the acknowledged state of the alert.
     */
    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    /**
     * The user names of individuval users or group names that will receive the
     * alert.
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets the user names of individuval users or group names that will receive
     * the alert.
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * The teams names that will receive the alert.
     */
    public List<String> getTeams() {
        return teams;
    }

    /**
     * Sets the teams names that will receive the alert.
     */
    public void setTeams(List<String> teams) {
        this.teams = teams;
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

}
