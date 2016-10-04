package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Alert;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * Represents the OpsGenie service response for an get alert request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertResponse extends BaseResponse {
    @JsonUnwrapped
    private Alert alert;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
