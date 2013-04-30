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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(source != null){
            json.put(OpsGenieClientConstants.API.SOURCE, source);
        }
        else {
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.SOURCE);
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
