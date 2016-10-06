package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents OpsGenie service response for creating heartbeat request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addHeartbeat(AddHeartbeatRequest)
 */
public class AddHeartbeatResponse extends BaseResponse {
    private String name;

    /**
     * Name of the added heartbeat monitor
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the added heartbeat monitor
     */
    public void setName(String name) {
        this.name = name;
    }

}
