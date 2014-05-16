package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for a create alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:41 AM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#createAlert(CreateAlertRequest)
 */
public class CreateAlertResponse extends BaseResponse {
    private String id;
    /**
     * Id of the created alert
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the created alert.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    /**
     * @see BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        if(data.containsKey(ClientConstants.API.ID)){
            setId((String) data.get(ClientConstants.API.ID));
        }
        if(data.containsKey(ClientConstants.API.ALERT_ID)){
            setId((String) data.get(ClientConstants.API.ALERT_ID));
        }
    }
}
