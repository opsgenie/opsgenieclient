package com.ifountain.opsgenie.client.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a enable/disable heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#enableHeartbeat(com.ifountain.opsgenie.client.model.customer.EnableHeartbeatRequest)
 */
public class EnableHeartbeatRequest extends BaseRequest<EnableHeartbeatResponse, EnableHeartbeatRequest> {
    private String name;
    @JsonIgnore
    private boolean enable;

    /**
     * Rest api uri of deleting heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        if (enable) {
            return "/v1/json/heartbeat/enable";
        } else {
            return "/v1/json/heartbeat/disable";
        }
    }

    /**
     * Name of heartbeat
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of heartbeat
     */
    public void setName(String name) {
        this.name = name;
    }

    public EnableHeartbeatRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Enable/disable state of heartbeat monitor
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets enable/disable state of heartbeat monitor
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public EnableHeartbeatResponse createResponse() {
        return new EnableHeartbeatResponse();
    }
}
