package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.Map;

/**
 * Container for the parameters to make a who is on call api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#whoIsOnCall(com.ifountain.opsgenie.client.model.schedule.WhoIsOnCallRequest)
 */
public class WhoIsOnCallRequest extends AbstractWhoIsOnCallRequest<WhoIsOnCallResponse> {
    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseGetRequest#_serialize()
     */
    public void _serialize(Map json){
        super._serialize(json);
        json.put(OpsGenieClientConstants.API.FLAT, false);
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseGetRequest#createResponse()
     */
    public WhoIsOnCallResponse createResponse() {
        return new WhoIsOnCallResponse();
    }
}
