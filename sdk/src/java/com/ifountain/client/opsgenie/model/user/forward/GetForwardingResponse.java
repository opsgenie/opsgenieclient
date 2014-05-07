package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Forwarding;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get forwarding request.
 *
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingResponse extends BaseResponse {
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
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        forwarding = new Forwarding();
        forwarding.fromMap(data);
    }
}
