package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Abstract Container for the alert requests.
 *
 * @author Sezgin Kucukkaraaslan
 * @deprecated As of release 2.8.0, will not be supoprted
 */
@Deprecated
public abstract class BaseAlertRequestWithId<T extends BaseResponse, K extends BaseAlertRequestWithId> extends BaseRequest<T, K> {
    private String id;
    private String alias;
    private String tinyId;

    /**
     * The id of the alert.
     *
     * @deprecated use getId
     */
    @JsonIgnore
    @Deprecated
    public String getAlertId() {
        return id;
    }

    /**
     * Sets the id of the alert. Either this or alias should be set.
     *
     * @deprecated use setId
     */
    @Deprecated
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
     * Sets the user defined identifier of the alert. Either this, alertId or tinyId
     * should be set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public K withId(String id) {
        this.id = id;
        return (K) this;
    }

    public K withAlias(String alias) {
        this.alias = alias;
        return (K) this;
    }

    public K withTinyId(String tinyId) {
        this.tinyId = tinyId;
        return (K) this;
    }


}
