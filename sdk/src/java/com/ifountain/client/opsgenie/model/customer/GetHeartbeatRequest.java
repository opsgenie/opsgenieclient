package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#getHeartbeat(com.ifountain.client.opsgenie.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatRequest extends BaseRequest<GetHeartbeatResponse> {
    private String source;
    /**
     * Rest api uri of get heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat";
    }


    /**
     * Source of heartbeat monitor.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets source of heartbeat monitor.
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(source != null){
            json.put(ClientConstants.API.SOURCE, source);
        }
        else {
            throw ClientValidationException.missingMandatoryProperty(ClientConstants.API.SOURCE);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatResponse createResponse() {
        return new GetHeartbeatResponse();
    }
}
