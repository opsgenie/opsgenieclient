package com.ifountain.opsgenie.client.model;

/**
 * Container for the parameters to make an execute aler action api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 10/30/12 4:59 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
 */
public class ExecuteAlertActionRequest extends BaseRequest {
    private String alertId;
    private String alias;
    private String action;
    private String user;

    /**
     * Rest api uri of execute alert action operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/executeAction";
    }

    /**
     * The id of the alert that the note will be added.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that the note will be added. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * The user defined identifier of the alert that the note will be added.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that the note will be added. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * The action to be executed.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action to be executed.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * The user who is performing the add note operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add note operation.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
