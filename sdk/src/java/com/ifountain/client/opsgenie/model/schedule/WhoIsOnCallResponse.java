package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.WhoIsOnCall;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for who is on call request.
 *
 * @author Mustafa Sener
 * @version 22.04.2013 15:10
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#whoIsOnCall(WhoIsOnCallRequest)
 */
public class WhoIsOnCallResponse extends BaseResponse{
    private WhoIsOnCall whoIsOnCall;

    /**
     * Returns the participant of the specified schedule who is on call in specified/current time.
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public WhoIsOnCall getWhoIsOnCall() {
        return whoIsOnCall;
    }

    /**
     * Sets the participant of the specified schedule who is on call in specified/current time.
     * @see com.ifountain.client.opsgenie.model.beans.WhoIsOnCall
     */
    public void setWhoIsOnCall(WhoIsOnCall whoIsOnCall) {
        this.whoIsOnCall = whoIsOnCall;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        whoIsOnCall = new WhoIsOnCall();
        whoIsOnCall.fromMap(data);
    }
}
