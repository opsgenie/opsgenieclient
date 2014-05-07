package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents the OpsGenie service response for an execute alert action request.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 10/30/12 5:01 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
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
     * @param data
     */
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        result = (String) data.get(ClientConstants.API.RESULT);
    }
}
