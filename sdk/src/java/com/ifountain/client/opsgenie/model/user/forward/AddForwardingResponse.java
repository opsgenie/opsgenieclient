package com.ifountain.client.opsgenie.model.user.forward;

import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add forwarding request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
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
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(OpsGenieClientConstants.API.ID);
    }
}
