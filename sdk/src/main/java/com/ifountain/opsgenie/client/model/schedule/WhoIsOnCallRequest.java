package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a who is on call api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest)
 */
public class WhoIsOnCallRequest extends AbstractWhoIsOnCallRequest<WhoIsOnCallResponse> {

	@Override
	public Map serialize() throws OpsGenieClientValidationException {
		Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.FLAT, false);
		return json;
	}

    public WhoIsOnCallResponse createResponse() {
        return new WhoIsOnCallResponse();
    }
}
