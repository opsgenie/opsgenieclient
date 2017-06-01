package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * Represents the OpsGenie service response for an execute alert action request.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#executeAlertAction(ExecuteAlertActionRequest)
 * @deprecated As of release 2.8.0, replaced by {@link com.ifountain.opsgenie.client.swagger.model.SuccessResponse}
 */
@Deprecated
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

}
