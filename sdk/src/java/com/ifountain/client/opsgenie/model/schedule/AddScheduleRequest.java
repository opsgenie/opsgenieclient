package com.ifountain.client.opsgenie.model.schedule;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.client.opsgenie.model.beans.ScheduleRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add schedule api call.
 *
 * @see com.ifountain.client.opsgenie.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleRequest extends BaseRequest<AddScheduleResponse> {
    private String name;
    private Boolean enabled = null;
    private TimeZone timeZone;
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

    /**
     * Returns enable/disable state of schedule
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable/disable state of schedule
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Timezone of schedule
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone of schedule
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws ClientValidationException {
        Map json = super.serialize();
        if(name != null){
            json.put(ClientConstants.API.NAME, name);
        }
        if(enabled != null){
            json.put(ClientConstants.API.ENABLED, enabled);
        }
        if(timeZone != null){
            json.put(ClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(rules != null){
            List<Map> ruleMaps = new ArrayList<Map>();
            for(ScheduleRule rule:rules){
                rule.setScheduleTimeZone(getTimeZone());
                ruleMaps.add(rule.toMap());
            }
            json.put(ClientConstants.API.RULES, ruleMaps);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public AddScheduleResponse createResponse() {
        return new AddScheduleResponse();
    }
}
