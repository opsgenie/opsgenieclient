package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse;

import java.util.Map;

/**
 * Container for the parameters to make a heartbeat api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 3:06 PM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#heartbeat(HeartbeatRequest)
 */
public class HeartbeatRequest extends BaseRequest<HeartbeatResponse> {
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
