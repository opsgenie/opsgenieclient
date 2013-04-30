package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:06 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
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
    public Map serialize() throws OpsGenieClientValidationException {
        Map res = super.serialize();
        if(source != null){
            res.put(OpsGenieClientConstants.API.SOURCE, source);
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public HeartbeatResponse createResponse() {
        return new HeartbeatResponse();
    }
}
