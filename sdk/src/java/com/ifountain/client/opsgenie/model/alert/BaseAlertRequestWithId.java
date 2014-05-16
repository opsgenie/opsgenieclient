package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.model.BaseResponse;

import java.util.Map;

/**
 * Container for the parameters to make alert api calls with id/alias/tinyId.
 *
 * @author Mustafa Sener
 * @version 24.04.2013 14:04
 * @see com.ifountain.client.model.BaseRequest
 */
public abstract class BaseAlertRequestWithId<T extends BaseResponse> extends BaseRequest<T>{
    private String id;
    private String alias;
    private String tinyId;

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
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> parameters = super.serialize();
        if (getId() != null)
            parameters.put(ClientConstants.API.ID, getId());
        if (getAlias() != null)
            parameters.put(ClientConstants.API.ALIAS, getAlias());
        if (getTinyId() != null)
            parameters.put(ClientConstants.API.TINY_ID, getTinyId());
        return parameters;
    }

}
