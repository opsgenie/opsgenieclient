package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.Map;

/**
 * Container for the parameters to make a flat who is on call api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#flatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.FlatWhoIsOnCallRequest)
 */
public class FlatWhoIsOnCallRequest extends AbstractWhoIsOnCallRequest<FlatWhoIsOnCallResponse> {
    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseGetRequest#_serialize(Map) ()
     */
    public void _serialize(Map json){
        super._serialize(json);
        json.put(OpsGenieClientConstants.API.FLAT, true);
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseGetRequest#createResponse()
     */
    public FlatWhoIsOnCallResponse createResponse() {
        return new FlatWhoIsOnCallResponse();
    }
}
