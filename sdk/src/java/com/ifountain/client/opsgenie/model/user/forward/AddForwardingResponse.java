package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Mustafa Sener
 * @version 22.03.2013 08:46
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingResponse extends BaseResponse {
    private String id;

    /**
     * Id of the created forwarding
     */
    public String getId() {
        return id;
    }

    /**
     * Id of the created forwarding
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(ClientConstants.API.ID);
    }
}
