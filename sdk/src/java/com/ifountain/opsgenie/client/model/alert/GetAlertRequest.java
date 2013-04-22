package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a get alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertRequest extends BaseRequest<GetAlertResponse> {
    private String id;
    private String alias;

    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    /**
     * The id of the alert that will be retreived.
     * @deprecated  use getId
     */
    public String getAlertId() {
        return id;
    }

    /**
     * Sets the id of the alert that will be retreived. Either this or alias should be set.
     * @deprecated use setId
     */
    public void setAlertId(String alertId) {
        this.id = alertId;
    }

    /**
     * The id of the alert that will be retreived.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the alert that will be retreived. Either this or alias should be set.
     */
    public void setId(String alertId) {
        this.id = alertId;
    }

    /**
     * User defined identifier of the alert that will be retreived.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the user defined identifier of the alert that will be retreived. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() {
        Map parameters = super.serialize();
        if (getId() != null)
            parameters.put(OpsGenieClientConstants.API.ID, getId());
        if (getAlias() != null)
            parameters.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        return parameters;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetAlertResponse createResponse() {
        return new GetAlertResponse();
    }
}
