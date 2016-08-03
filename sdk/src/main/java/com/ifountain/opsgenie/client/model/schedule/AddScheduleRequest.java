package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ScheduleRotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleRequest extends BaseRequest<AddScheduleResponse> {
    private String name;
    private Boolean enabled = null;
    private TimeZone timeZone;
    private List<ScheduleRotation> rotations;


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
     * Rotations of schedule
     */
    public List<ScheduleRotation> getRotations() {
        return rotations;
    }

    /**
     * Sets rotations of schedule
     */
    public void setRotations(List<ScheduleRotation> rotations) {
        this.rotations = rotations;
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
		Map json = new HashMap();
		if (getApiKey() != null) 
			json.put(OpsGenieClientConstants.API.API_KEY, getApiKey());
        if(name != null){
            json.put(OpsGenieClientConstants.API.NAME, name);
        }
        if(enabled != null){
            json.put(OpsGenieClientConstants.API.ENABLED, enabled);
        }
        if(timeZone != null){
            json.put(OpsGenieClientConstants.API.TIMEZONE, timeZone.getID());
        }
        if(rotations != null && rotations.size() > 0){
            List<Map> rotationMaps = new ArrayList<Map>();
            for(ScheduleRotation rotation: rotations){
                if(rotation.getRotationLength() < 1)
                    rotation.setRotationLength(1);
                rotation.setScheduleTimeZone(getTimeZone());
                rotationMaps.add(rotation.toMap());
            }
            json.put(OpsGenieClientConstants.API.ROTATIONS, rotationMaps);
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddScheduleResponse createResponse() {
        return new AddScheduleResponse();
    }
}
