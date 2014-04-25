package com.ifountain.client.opsgenie.model.customer;

import com.ifountain.client.model.BaseRequest;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.client.opsgenie.IOpsGenieClient#listHeartbeats(ListHeartbeatsRequest)
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
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListHeartbeatsResponse createResponse() {
        return new ListHeartbeatsResponse();
    }
}
