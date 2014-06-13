package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a enable/disable heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#enableHeartbeat(com.ifountain.opsgenie.client.model.customer.EnableHeartbeatRequest)
 */
public class EnableHeartbeatRequest extends BaseRequest<EnableHeartbeatResponse> {
    private String id;
    private String name;
    private boolean enable;
    /**
     * Rest api uri of deleting heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        if(enable)
        {
            return "/v1/json/heartbeat/enable";
        }
        else{
            return "/v1/json/heartbeat/disable";
        }
    }

    /**
     * Id of heartbeat
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of heartbeat
     */
    public void setId(String id) {
        this.id = id;
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(id != null){
            json.put(OpsGenieClientConstants.API.ID, id);
        }
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        return json;
    }
}
