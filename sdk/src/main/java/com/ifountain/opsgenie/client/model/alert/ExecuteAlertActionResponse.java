package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an execute alert action request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 10/30/12 5:01 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
 */
public class ExecuteAlertActionResponse extends BaseResponse {
    private String result;

    /**
     * Action execution result.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the action execution result.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @see BaseResponse#deserialize(java.util.Map)
     */
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        result = (String) data.get(OpsGenieClientConstants.API.RESULT);
    }
}
