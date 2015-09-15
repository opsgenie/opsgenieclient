package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a create heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#updateHeartbeat(UpdateHeartbeatRequest)
 */
public class UpdateHeartbeatRequest extends AddHeartbeatRequest{
    private String id;
    /**
     * Rest api uri of updating heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
    }

    /**
     * Return id of heartbeat monitor
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of heartbeat monitor
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddHeartbeatResponse createResponse() {
        return new UpdateHeartbeatResponse();
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        return json;
    }
}
