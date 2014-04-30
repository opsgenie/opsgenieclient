package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Forwarding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list forwardings request.
 *
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#listForwardings(ListForwardingsRequest)
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

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        forwardings = new ArrayList<Forwarding>();
        if (data.containsKey(ClientConstants.API.FORWARDINGS)) {
            List<Map> forwardingMaps = (List<Map>) data.get(ClientConstants.API.FORWARDINGS);
            for (Map forwardingMap : forwardingMaps) {
                Forwarding forwarding = new Forwarding();
                forwarding.fromMap(forwardingMap);
                forwardings.add(forwarding);
            }
        }
    }
}
