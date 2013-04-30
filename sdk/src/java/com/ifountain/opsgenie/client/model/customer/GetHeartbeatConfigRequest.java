package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get heartbeat configuration api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeatConfig(GetHeartbeatConfigRequest)
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatConfigResponse createResponse() {
        return new GetHeartbeatConfigResponse();
    }
}
