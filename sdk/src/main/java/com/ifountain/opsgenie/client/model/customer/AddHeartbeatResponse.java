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
    private String id;
    /**
     * Id of the added  heartbeat monitor
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added heartbeat monitor
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(OpsGenieClientConstants.API.ID);
    }
}
