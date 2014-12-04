package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make a list schedule overrides api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 10:42 AM
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(ListScheduleOverridesRequest)
 */
public class ListScheduleOverridesRequest extends BaseRequest<ListScheduleOverridesResponse>{
    private String schedule;

    /**
     * Rest api uri of list schedule override operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/override";
    }

    /**
     * Id or the name of the schedule that the overrides belong to.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the id or the name of the schedule that the overrides belong to.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(getSchedule() != null){
            json.put(OpsGenieClientConstants.API.SCHEDULE, getSchedule());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public ListScheduleOverridesResponse createResponse() {
        return new ListScheduleOverridesResponse();
    }

}
