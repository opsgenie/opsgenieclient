package com.ifountain.opsgenie.client.model.alert;

import java.util.List;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make an create alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertRequest extends BaseRequest<CreateAlertResponse> {
    private String message;
    private String source;
    private String entity;
    private String alias;
    private String description;
    private List<String> recipients;
    private List<String> teams;
    private List<String> tags;
    private List<String> actions;
    private Map<String, String> details;
    private String user;
    private String note;

    /**
     * Rest api uri of alert create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * Alert text.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets alert text.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Field to specify the source of the alert.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of alert.
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
     * Sets the entity that the alert is related to.
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * A user defined identifier for the alert and there can be only one alert with open status with the same alias.
     * Provides ability to assign a known id and later use this id to perform additional actions such as log, close,
     * attach for the same alert.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the alert.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Alert text in long form.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set alert text in long form.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The user names of individual users or names of groups that the alert will be sent.
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * Sets the user names of individual users or names of groups that the alert will be sent.
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * The team names that the alert will be sent.
     */
    public List<String> getTeams() {
        return teams;
    }

    /**
     * Sets the team names that the alert will be sent.
     */
    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    /**
     * Labels attached to the alert.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the labels attached to the alert.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * List of actions that can be executed. Custom actions can be defined to enable users to execute
     * actions for each alert. If action callback URL is specified on Settings page of customer, that
     * callback URL will be called when action is executed.
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
     * Sets additional alert properties.
     */
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    /**
     * The user who is performing alert create operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing alert create operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public CreateAlertResponse createResponse() {
        return new CreateAlertResponse();
    }
}
