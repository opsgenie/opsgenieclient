package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Represents OpsGenie service response for get forwarding request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
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
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        forwarding = new Forwarding();
        forwarding.fromMap(data);
    }
}
