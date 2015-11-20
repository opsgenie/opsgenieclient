package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list who is on call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallRequest extends AbstractListWhoIsOnCallRequest<ListWhoIsOnCallResponse> {
    @Override
    /**
     * @see BaseRequest#serialize() (Map) ()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.FLAT, false);
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListWhoIsOnCallResponse createResponse() {
        return new ListWhoIsOnCallResponse();
    }
}
