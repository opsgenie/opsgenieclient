package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeat(com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatRequest extends BaseRequest<GetHeartbeatResponse> {
    private String id;
    private String name;
    /**
     * Rest api uri of get heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
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
     * Name of heartbeat monitor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of heartbeat monitor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @deprecated
     * Use getName
     */
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated
     * Use setSource
     */
    public void setSource(String source) {
        setName(source);
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
        else if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatResponse createResponse() {
        return new GetHeartbeatResponse();
    }
}
