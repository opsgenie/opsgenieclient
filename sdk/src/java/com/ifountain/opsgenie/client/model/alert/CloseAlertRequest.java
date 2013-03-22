package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a close alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 2:05 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#closeAlert(CloseAlertRequest)
 */
public class CloseAlertRequest extends BaseRequest {
    private String alertId;
    private String alias;
    private String user;
    private String note;

    /**
     * The id of the alert that will be closed.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that will be closed. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * User defined identifier of the alert that will be closed.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that will be closed. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Rest api uri of close alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/close";
    }

    /**
     * The user who is performing the close alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the close alert operation.
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
}
