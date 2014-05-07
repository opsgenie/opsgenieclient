package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:06 PM
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatRequest extends BaseRequest<HeartbeatResponse> {
    String source;
    /*
    * Source of heartbeat monitor
     */
    public String getSource() {
        return source;
    }

    /*
    * Sets source of heartbeat monitor
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> res = super.serialize();
        if(source != null){
            res.put(ClientConstants.API.SOURCE, source);
        }
        return res;
    }

    /**
     * Rest api uri of heartbeat operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat";
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public HeartbeatResponse createResponse() {
        return new HeartbeatResponse();
    }
}
