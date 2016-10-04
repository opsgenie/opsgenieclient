package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Schedule bean
 */
public class Schedule extends Bean implements ConvertFromTimeZone {
    private String id;
    private String name;
    private String team;
    private String description;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Boolean enabled;
    @JsonProperty("rules")
    private List<ScheduleRotation> rotations;

    /**
     * Id of schedule
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule
     */
    public void setId(String id) {
        this.id = id;
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
     * Rotations of schedule
     *
     * @see ScheduleRotation
     */
    public List<ScheduleRotation> getRotations() {
        return rotations;
    }

    /**
     * Sets rotations of schedule
     *
     * @see ScheduleRotation
     */
    public void setRotations(List<ScheduleRotation> rotations) {
        this.rotations = rotations;
    }

    /**
     * Name of the team that schedule belongs to, if any
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the name of the team that schedule belongs to.
     */
    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public void setTime() throws ParseException {
        if (getTimeZone() != null && rotations != null && rotations.size() > 0) {
            for (ScheduleRotation scheduleRotation : rotations) {
                scheduleRotation.setScheduleTimeZone(getTimeZone());
                SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
                String endDateString = null, startDateString = null;
                if (scheduleRotation.getEndDate() != null)
                    endDateString = sdf.format(scheduleRotation.getEndDate());
                if (scheduleRotation.getStartDate() != null)
                    startDateString = sdf.format(scheduleRotation.getStartDate());
                sdf.setTimeZone(getTimeZone());
                if (endDateString != null)
                    scheduleRotation.setEndDate(sdf.parse(endDateString));
                if (startDateString != null)
                    scheduleRotation.setStartDate(sdf.parse(startDateString));
            }
        }
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return this.timeZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
