package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Omer Ozkan
 */
public class HeartbeatPingRequest extends BaseRequest<HeartbeatResponse, HeartbeatPingRequest> {
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

    public HeartbeatPingRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Rest api uri of heartbeat operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/heartbeats/" + name + "/ping";
    }

    /**
     * @see BaseRequest#createResponse()
     */
    @Override
    public HeartbeatResponse createResponse() {
        return new HeartbeatResponse();
    }
}
