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
 * @author Mustafa Sener
 * @version 22.03.2013 08:46
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#listForwardings(ListForwardingsRequest)
 */
public class ListForwardingsResponse extends BaseResponse {
    private List<Forwarding> forwardings;

    /**
     * Forwarding setting beans
     */
    public List<Forwarding> getForwardings() {
        return forwardings;
    }

    /**
     * Sets forwarding setting beans
     */
    public void setForwardings(List<Forwarding> forwardings) {
        this.forwardings = forwardings;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        forwardings = new ArrayList<Forwarding>();
        if (data.containsKey(ClientConstants.API.FORWARDINGS)) {
            List<Map<String, Object>> forwardingMaps = (List<Map<String, Object>>) data.get(ClientConstants.API.FORWARDINGS);
            for (Map<String, Object> forwardingMap : forwardingMaps) {
                Forwarding forwarding = new Forwarding();
                forwarding.fromMap(forwardingMap);
                forwardings.add(forwarding);
            }
        }
    }
}
