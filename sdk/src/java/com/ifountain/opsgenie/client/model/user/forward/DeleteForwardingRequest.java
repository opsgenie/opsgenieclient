package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to make an delete forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#deleteForwarding(DeleteForwardingRequest)
 */
public class DeleteForwardingRequest extends BaseRequest {
    private String id;

    /**
     * Rest api uri of delete forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }
}
