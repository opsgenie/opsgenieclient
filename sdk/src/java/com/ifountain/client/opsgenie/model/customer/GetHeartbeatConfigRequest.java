package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a get heartbeat configuration api call.
 *
 * @author Mustafa Sener
 * @version 30.04.2013 22:36
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#getHeartbeatConfig(GetHeartbeatConfigRequest)
 */
public class GetHeartbeatConfigRequest extends BaseRequest<GetHeartbeatConfigResponse> {
    /**
     * Rest api uri of get heartbeat configuration operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat/config";
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatConfigResponse createResponse() {
        return new GetHeartbeatConfigResponse();
    }
}
