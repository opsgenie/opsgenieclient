package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ScheduleRule;

import java.util.List;

/**
 * Container for the parameters to make an add schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleRequest extends BaseRequest {
    private String name;
    private List<ScheduleRule> rules;


    /**
     * Rest api uri of addding schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * Name of schedule
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rules of schedule
     */
    public List<ScheduleRule> getRules() {
        return rules;
    }

    /**
     * Sets rules of schedule
     */
    public void setRules(List<ScheduleRule> rules) {
        this.rules = rules;
    }
}
