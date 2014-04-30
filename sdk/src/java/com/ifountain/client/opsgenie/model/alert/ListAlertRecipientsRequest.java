package com.ifountain.client.opsgenie.model.alert;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a list alert recipient api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 4:30 PM
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient#listAlertRecipients(ListAlertRecipientsRequest)
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
    public Map serialize() throws ClientValidationException {
        Map resp = super.serialize();
        if(!(resp.containsKey(ClientConstants.API.ID)
                || resp.containsKey(ClientConstants.API.ALERT_ID)
                ||resp.containsKey(ClientConstants.API.ALIAS)
        || resp.containsKey(ClientConstants.API.TINY_ID)) )
        {
            throw ClientValidationException.missingMandatoryProperty(ClientConstants.API.ID);
        }
        return resp;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public ListAlertRecipientsResponse createResponse() {
        return new ListAlertRecipientsResponse();
    }
}
