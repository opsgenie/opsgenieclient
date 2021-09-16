package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents OpsGenie service response for list flat who is on call request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listFlatWhoIsOnCall(ListFlatWhoIsOnCallRequest)
 */
public class ListFlatWhoIsOnCallResponse extends BaseResponse{
    @JsonProperty("oncalls")
    private List<FlatWhoIsOnCall> flatWhoIsOnCallList;

    /**
     * List of flat on calls
     *
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public List<FlatWhoIsOnCall> getWhoIsOnCallList() {
        return flatWhoIsOnCallList;
    }

    /**
     * Sets list of flat on calls
     *
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public void setWhoIsOnCallList(List<FlatWhoIsOnCall> whoIsOnCallList) {
        this.flatWhoIsOnCallList = whoIsOnCallList;
    }

}
