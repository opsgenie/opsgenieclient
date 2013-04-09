package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to make a list forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#listForwardings(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
 */
public class ListForwardingsRequest extends BaseRequest {
    private String user;

    /**
     * Rest api uri of list forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Username of user who forwarding is created for.
     * If not specified all forwardings belongs to customer will be returned
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

}
