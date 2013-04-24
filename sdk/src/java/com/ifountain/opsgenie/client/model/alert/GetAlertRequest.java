package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a get alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#getAlert(GetAlertRequest)
 */
public class GetAlertRequest extends BaseAlertRequestWithId<GetAlertResponse> {
    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetAlertResponse createResponse() {
        return new GetAlertResponse();
    }
}
