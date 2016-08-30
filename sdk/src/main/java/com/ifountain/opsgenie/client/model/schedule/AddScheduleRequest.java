package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ScheduleRotation;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
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
    @JsonIgnore
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
    @JsonProperty("rotations")
    public List<Map> getRotationsMap() {
        if (rotations != null) {
            List<Map> rotationMaps = new ArrayList<Map>();
            for (ScheduleRotation rotation : rotations) {
                if (rotation.getRotationLength() < 1)
                    rotation.setRotationLength(1);
                rotation.setScheduleTimeZone(getTimeZone());
                rotationMaps.add(rotation.toMap());
            }
            return rotationMaps;
        }
        return null;
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
     * Timezone to determine forwarding start and end dates. If not given GMT is used.
     */
    @JsonProperty("timezone")
    public String getTimeZoneId() {
        if (timeZone == null)
            return null;
        return timeZone.getID();
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddScheduleResponse createResponse() {
        return new AddScheduleResponse();
    }
}
