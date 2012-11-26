package com.ifountain.opsgenie.client.model;

/**
 * Container for the parameters to make a take ownership api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:32 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#takeOwnership(TakeOwnershipRequest)
 */
public class TakeOwnershipRequest extends BaseRequest{
    private String alertId;
    private String alias;
    private String user;

    /**
     * The id of the alert that will be owned.
     */
    public String getAlertId() {
        return alertId;
    }

    /**
     * Sets the id of the alert that will be owned. Either this or alias should be set.
     */
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    /**
     * User defined identifier of the alert that will be owned.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that will be owned. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Rest api uri of take ownership operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/takeOwnership";
    }

    /**
     * The user who is performing the take ownership operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the take ownership operation.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
