package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list schedule overrides api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#listScheduleOverrides(ListScheduleOverridesRequest)
 */
public class ListScheduleOverridesRequest extends BaseRequest<ListScheduleOverridesResponse> {
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListScheduleOverridesResponse createResponse() {
        return new ListScheduleOverridesResponse();
    }

}
