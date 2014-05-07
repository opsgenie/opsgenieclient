package com.ifountain.client.opsgenie.model.beans;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see com.ifountain.client.opsgenie.model.beans.BaseAlert
 * Alert bean
 */
public class Alert extends BaseAlert {
    private String description;
    private String source;
    private String entity;
    private String owner;
    private List<String> recipients;
    private List<String> tags;
    private List<String> actions;
    private Map<String, String> details;

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
    public Map<String,Object> toMap() {
        Map<String,Object> json = super.toMap();
        json.put(ClientConstants.API.RECIPIENTS, getRecipients());
        json.put(ClientConstants.API.SOURCE, getSource());
        json.put(ClientConstants.API.ENTITY, getEntity());
        json.put(ClientConstants.API.DESCRIPTION, getDescription());
        json.put(ClientConstants.API.TAGS, getTags());
        json.put(ClientConstants.API.ACTIONS, getActions());
        json.put(ClientConstants.API.DETAILS, getDetails());
        json.put(ClientConstants.API.OWNER, getOwner());
        return json;
    }

    @Override
    public void fromMap(Map<String,Object> map) {
        super.fromMap(map);
        setDescription((String) map.get(ClientConstants.API.DESCRIPTION));
        setSource((String) map.get(ClientConstants.API.SOURCE));
        setEntity((String) map.get(ClientConstants.API.ENTITY));
        setOwner((String) map.get(ClientConstants.API.OWNER));
        setTags((List<String>) map.get(ClientConstants.API.TAGS));
        setActions((List<String>) map.get(ClientConstants.API.ACTIONS));
        setRecipients((List<String>) map.get(ClientConstants.API.RECIPIENTS));
        setDetails((Map<String, String>) map.get(ClientConstants.API.DETAILS));
    }
}
