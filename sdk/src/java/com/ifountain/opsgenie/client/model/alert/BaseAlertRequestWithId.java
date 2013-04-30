package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.util.Map;

public abstract class BaseAlertRequestWithId<T extends BaseResponse> extends BaseRequest<T>{
    private String id;
    private String alias;
    private String tinyId;

    /**
     * The id of the alert.
     * @deprecated use getId
     */
    public String getAlertId() {
        return id;
    }

    /**
     * Sets the id of the alert. Either this or alias should be set.
     * @deprecated use setId
     */
    public void setAlertId(String alertId) {
        this.id = alertId;
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
     * Sets the id of the alert. Either this or alias should be set.
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
     * Sets the user defined identifier of the alert. Either this or alertId should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map parameters = super.serialize();
        if (getId() != null)
            parameters.put(OpsGenieClientConstants.API.ID, getId());
        if (getAlias() != null)
            parameters.put(OpsGenieClientConstants.API.ALIAS, getAlias());
        if (getTinyId() != null)
            parameters.put(OpsGenieClientConstants.API.TINY_ID, getTinyId());
        return parameters;
    }

}
