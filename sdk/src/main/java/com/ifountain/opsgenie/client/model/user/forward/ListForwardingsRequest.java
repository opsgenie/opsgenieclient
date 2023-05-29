package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
 */
public class ListForwardingsRequest extends BaseRequest<ListForwardingsResponse, ListForwardingsRequest> {

    /**
     * Rest api uri of list forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListForwardingsResponse createResponse() {
        return new ListForwardingsResponse();
    }

}
