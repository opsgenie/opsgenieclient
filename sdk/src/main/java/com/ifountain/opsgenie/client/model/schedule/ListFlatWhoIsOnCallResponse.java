package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list flat who is on call request.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listFlatWhoIsOnCall(com.ifountain.opsgenie.client.model.schedule.ListFlatWhoIsOnCallRequest)
 */
public class ListFlatWhoIsOnCallResponse extends BaseResponse{
    private List<FlatWhoIsOnCall> flatWhoIsOnCallList;

    /**
     * List of flat on calls
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public List<FlatWhoIsOnCall> getWhoIsOnCallList() {
        return flatWhoIsOnCallList;
    }

    /**
     * Sets list of flat on calls
     * @see com.ifountain.opsgenie.client.model.beans.FlatWhoIsOnCall
     */
    public void setWhoIsOnCallList(List<FlatWhoIsOnCall> whoIsOnCallList) {
        this.flatWhoIsOnCallList = whoIsOnCallList;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> oncallMaps = (List<Map>) data.get(OpsGenieClientConstants.API.ON_CALLS);
        if(oncallMaps != null){
            flatWhoIsOnCallList = new ArrayList<FlatWhoIsOnCall>();
            for(Map oncallMap:oncallMaps) {
                FlatWhoIsOnCall whoIsOnCall = new FlatWhoIsOnCall();
                whoIsOnCall.fromMap(oncallMap);
                flatWhoIsOnCallList.add(whoIsOnCall);
            }
        }
    }
}
