package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for creating heartbeat request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#addHeartbeat(AddHeartbeatRequest)
 */
public class AddHeartbeatResponse extends BaseResponse{
    private String name;
    /**
     * Name of the added  heartbeat monitor
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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        name = (String) data.get(OpsGenieClientConstants.API.NAME);
    }
}
