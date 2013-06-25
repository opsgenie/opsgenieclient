package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a list alert recipient api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#listAlertRecipients(ListAlertRecipientsRequest)
 */
public class ListAlertRecipientsRequest extends BaseAlertRequestWithId<ListAlertRecipientsResponse> {
    /**
     * Rest api uri of get alert operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/alert/recipient";
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map resp = super.serialize();
        if(!(resp.containsKey(OpsGenieClientConstants.API.ID)
                || resp.containsKey(OpsGenieClientConstants.API.ALERT_ID)
                ||resp.containsKey(OpsGenieClientConstants.API.ALIAS)
        || resp.containsKey(OpsGenieClientConstants.API.TINY_ID)) )
        {
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        }
        return resp;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertRecipientsResponse createResponse() {
        return new ListAlertRecipientsResponse();
    }
}
