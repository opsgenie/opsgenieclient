package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Map;

/**
 * Container for the parameters to make a list flat who is on call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listFlatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListFlatWhoIsOnCallRequest)
 */
public class ListFlatWhoIsOnCallRequest extends AbstractListWhoIsOnCallRequest<ListFlatWhoIsOnCallResponse> {

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        json.put(OpsGenieClientConstants.API.FLAT, true);
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListFlatWhoIsOnCallResponse createResponse() {
        return new ListFlatWhoIsOnCallResponse();
    }
}
