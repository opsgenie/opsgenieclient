package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Represents OpsGenie service response for list forwardings request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listForwardings(ListForwardingsRequest)
 */
public class ListForwardingsResponse extends BaseResponse {
    private List<Forwarding> forwardings;

    /**
     * Gets forwarding objects.
     */
    public List<Forwarding> getForwardings() {
        return forwardings;
    }

    /**
     * Sets forwarding objects.
     */
    public void setForwardings(List<Forwarding> forwardings) {
        this.forwardings = forwardings;
    }

    @Override
    public void fromJson(String json) throws IOException, ParseException {
        super.fromJson(json);
        if (forwardings != null)
            for (Forwarding forwarding : forwardings)
                forwarding.setTime();
    }
}
