package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a enable/disable heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#enableHeartbeat(com.ifountain.opsgenie.client.model.customer.EnableHeartbeatRequest)
 */
public class EnableHeartbeatRequest extends BaseRequest<EnableHeartbeatResponse> {
    private String name;
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


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public EnableHeartbeatResponse createResponse() {
        return new EnableHeartbeatResponse();
    }
}
