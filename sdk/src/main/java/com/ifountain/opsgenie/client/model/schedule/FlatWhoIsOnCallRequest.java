package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a flat who is on call api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#flatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.FlatWhoIsOnCallRequest)
 */
public class FlatWhoIsOnCallRequest extends AbstractWhoIsOnCallRequest<FlatWhoIsOnCallResponse> {
    
    @Override
    public Map serialize() throws OpsGenieClientValidationException {
    	Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.FLAT, true);
    	return json;
    }

    public FlatWhoIsOnCallResponse createResponse() {
        return new FlatWhoIsOnCallResponse();
    }
}
