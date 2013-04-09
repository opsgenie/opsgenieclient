package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to make a get forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingRequest extends BaseRequest {
    private String id;
    private String alias;

    /**
     * Rest api uri of get forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be returned.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be returned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the forwarding.
     * Provides ability to assign a known id and later use this id to get forwarding details.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
