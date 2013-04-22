package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Forwarding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list forwardings request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listForwardings(ListForwardingsRequest)
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
        if (data.containsKey(OpsGenieClientConstants.API.FORWARDINGS)) {
            List<Map> forwardingMaps = (List<Map>) data.get(OpsGenieClientConstants.API.FORWARDINGS);
            for (Map forwardingMap : forwardingMaps) {
                Forwarding forwarding = new Forwarding();
                forwarding.fromMap(forwardingMap);
                forwardings.add(forwarding);
            }
        }
    }
}
