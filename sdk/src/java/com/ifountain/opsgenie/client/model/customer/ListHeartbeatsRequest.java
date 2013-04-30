package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeat(ListHeartbeatsRequest)
 */
public class ListHeartbeatsRequest extends BaseRequest<ListHeartbeatsResponse> {
    /**
     * Rest api uri of get heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat";
    }


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListHeartbeatsResponse createResponse() {
        return new ListHeartbeatsResponse();
    }
}
