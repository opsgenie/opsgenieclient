package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientValidationException;
import com.ifountain.client.OpsGenieClientConstants;

import java.util.Map;

/**
 * Container for the parameters to make a get alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#getAlert(GetAlertRequest)
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
    public Map serialize() throws ClientValidationException {
        Map resp = super.serialize();
        if(!(resp.containsKey(OpsGenieClientConstants.API.ID)
                || resp.containsKey(OpsGenieClientConstants.API.ALERT_ID)
                ||resp.containsKey(OpsGenieClientConstants.API.ALIAS)
        || resp.containsKey(OpsGenieClientConstants.API.TINY_ID)) )
        {
            throw ClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        }
        return resp;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public GetAlertResponse createResponse() {
        return new GetAlertResponse();
    }
}
