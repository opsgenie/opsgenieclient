package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatRequest extends BaseRequest<HeartbeatResponse, HeartbeatRequest> {
    private String name;

    /*
    * Name of heartbeat monitor
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of heartbeat monitor
     **/
    public void setName(String name) {
        this.name = name;
    }

    public HeartbeatRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @deprecated use getName()
     */
    @Deprecated
    @JsonIgnore
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated Use setName()
     **/
    @Deprecated
    public void setSource(String source) {
        setName(source);
    }

    /**
     * Rest api uri of heartbeat operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat/send";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public HeartbeatResponse createResponse() {
        return new HeartbeatResponse();
    }
}
