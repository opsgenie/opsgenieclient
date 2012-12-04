package com.ifountain.opsgenie.client.model;

/**
 * Container for the parameters to make a delete alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/4/12 3:38 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#deleteAlert(DeleteAlertRequest)
 */
public class DeleteAlertRequest extends BaseRequest{
    private String alertId;
    private String user;

    /**
     * The id of the alert that will be deleted.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that will be deleted. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * Rest api uri of delete alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * The user who is performing the delete alert operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the delete alert operation.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
