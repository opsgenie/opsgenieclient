package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
 */
public class ListForwardingsRequest extends BaseRequest<ListForwardingsResponse> {
    private String user;

    /**
     * Rest api uri of list forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Username of user who forwarding is created for. If not specified all
     * forwardings belongs to customer will be returned
     */
    public String getUser() {
        return user;
    }

    /**
     * Username of user who forwarding is created for
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListForwardingsResponse createResponse() {
        return new ListForwardingsResponse();
    }

}
