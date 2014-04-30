package com.ifountain.client.opsgenie.model.escalation;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for add escalation request.
 *
 * @see com.ifountain.client.opsgenie.IEscalationOpsGenieClient#addEscalation(AddEscalationRequest)
 */
public class AddEscalationResponse extends BaseResponse{
    private String id;
    /**
     * Id of the added escalation
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the added escalation
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        id = (String) data.get(ClientConstants.API.ID);
    }
}
