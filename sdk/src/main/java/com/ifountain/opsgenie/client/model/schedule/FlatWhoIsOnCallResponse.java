package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for flat who is on call request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#flatWhoIsOnCall(FlatWhoIsOnCallRequest)
 */
public class FlatWhoIsOnCallResponse extends BaseResponse {
    private FlatWhoIsOnCall whoIsOnCall;

    /**
     * Details of schedule flat on call
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public FlatWhoIsOnCall getWhoIsOnCall() {
        return whoIsOnCall;
    }

    /**
     * Sets details of schedule flat on call
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public void setWhoIsOnCall(FlatWhoIsOnCall whoIsOnCall) {
        this.whoIsOnCall = whoIsOnCall;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        whoIsOnCall = new FlatWhoIsOnCall();
        whoIsOnCall.fromMap(data);
    }
}
