package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.util.Date;
import java.util.TimeZone;

/**
 * Represents OpsGenie service response for get forwarding request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingResponse extends BaseResponse {
    private Forwarding forwarding;
    private String json;

    /**
     * Forwarding setting bean
     */
    public Forwarding getForwarding() {
        return forwarding;
    }

    /**
     * Sets forwarding setting bean
     */
    public void setForwarding(Forwarding forwarding) {
        this.forwarding = forwarding;
    }

    /**
     * The low level json response that is returned from OpsGenie service for get alert api call.
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Sets the low level json response that is returned from OpsGenie service for get alert api call.
     */
    public String getJson() {
        return json;
    }
}
