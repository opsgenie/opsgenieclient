package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.WhoIsOnCall;

import java.util.List;

/**
 * Represents OpsGenie service response for list who is on call request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listWhoIsOnCall(ListWhoIsOnCallRequest)
 */
public class ListWhoIsOnCallResponse extends BaseResponse {
    @JsonProperty("oncalls")
    private List<WhoIsOnCall> whoIsOnCallList;

    /**
     * List of on calls
     *
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
     */
    public List<WhoIsOnCall> getWhoIsOnCallList() {
        return whoIsOnCallList;
    }

    /**
     * Sets list of on calls
     *
     * @see com.ifountain.opsgenie.client.model.beans.WhoIsOnCall
     */
    public void setWhoIsOnCallList(List<WhoIsOnCall> whoIsOnCallList) {
        this.whoIsOnCallList = whoIsOnCallList;
    }

}
