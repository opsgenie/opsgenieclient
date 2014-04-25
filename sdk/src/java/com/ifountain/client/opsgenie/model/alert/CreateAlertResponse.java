package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.OpsGenieClientConstants;
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
    public String getAlertId() {
        return id;
    }

    /**
     * Sets the id of the created alert.
     */
    public void setAlertId(String alertId) {
        this.id = alertId;
    }

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
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        if(data.containsKey(OpsGenieClientConstants.API.ALERT_ID)){
            setAlertId((String) data.get(OpsGenieClientConstants.API.ALERT_ID));
        }
        if(data.containsKey(OpsGenieClientConstants.API.ID)){
            setId((String) data.get(OpsGenieClientConstants.API.ID));
        }

    }
}
