package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.Alert;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Represents the OpsGenie service response for a get alert request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertResponse extends BaseResponse {
    private Alert alert;

    /*
     * Returns the alert with specified id, alias or tiny id.
     */
    public Alert getAlert() {
        return alert;
    }

    /**
     * Sets the alert with specified id, alias or tiny id.
     */
    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    /**
     * @see BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        alert = new Alert();
        alert.fromMap(data);
    }
}
