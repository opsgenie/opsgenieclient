package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeat(com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatRequest extends BaseRequest<GetHeartbeatResponse> {
    private String name;

    /**
     * Rest api uri of get heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
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
     * @deprecated Use getName
     */
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated Use setSource
     */
    public void setSource(String source) {
        setName(source);
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatResponse createResponse() {
        return new GetHeartbeatResponse();
    }
}
