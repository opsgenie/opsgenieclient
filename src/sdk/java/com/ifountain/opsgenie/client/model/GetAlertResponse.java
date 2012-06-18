package com.ifountain.opsgenie.client.model;

import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an get alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertResponse implements Response {
    private String id;
    private String alias;
    private String message;
    private String description;
    private String source;
    private String entity;
    private String status;
    private long createdAt;
    private List<String> recipients;
    private List<String> tags;
    private List<String> actions;
    private Map<String, String> details;
    private String json;

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
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the alert.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The creation time of alert in milliseconds.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time of the alert in milliseconds.
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * The user names of individuval users or group names that will receive the alert.
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets the user names of individuval users or group names that will receive the alert.
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

    /**
     * The low level json response that is returned from OpsGenie service for get alert api call.
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Sets the low level json response that is returned from OpsGenie service for get alert api call.
     */
    public String getJson() {
        return json;
    }
}
