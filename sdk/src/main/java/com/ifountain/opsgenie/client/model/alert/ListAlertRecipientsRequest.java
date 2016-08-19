package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;


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
    public void validate() throws OpsGenieClientValidationException { 
    	if(!(this.getId() != null
            || this.getAlertId() != null
            || this.getAlias() != null
    		|| this.getTinyId() != null) )
        throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ID,
        		OpsGenieClientConstants.API.ALERT_ID,OpsGenieClientConstants.API.ALIAS,OpsGenieClientConstants.API.TINY_ID);
    	super.validate();
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListAlertRecipientsResponse createResponse() {
        return new ListAlertRecipientsResponse();
    }
}
