package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.util.List;

/**
 * Represents OpsGenie service response for list forwardings request.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#listForwarding(com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest)
 */
public class ListForwardingsResponse extends BaseResponse {
    private List<Forwarding> forwardings;
    private String json;

    /**
     * Forwarding setting beans
     */
    public List<Forwarding> getForwardings() {
        return forwardings;
    }

    /**
     * Sets forwarding setting bean
     */
    public void setForwardings(List<Forwarding> forwardings) {
        this.forwardings = forwardings;
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
