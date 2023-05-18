package com.ifountain.opsgenie.client.model.user.forward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents OpsGenie service response for get forwarding request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingResponse extends BaseResponse {
    @JsonProperty("data")
    private Forwarding forwarding;

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

    @Override
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        if (forwarding != null)
            forwarding.setTime();
    }
}
