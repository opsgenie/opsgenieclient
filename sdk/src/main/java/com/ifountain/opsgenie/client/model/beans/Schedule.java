package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Schedule bean
 */
public class Schedule extends BeanWithId implements ConvertFromTimeZone {
    private String name;
    private DataWithName ownerTeam;
    private String description;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private Boolean enabled;
    private List<ScheduleRotation> rotations;

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
    public DataWithName getOwnerTeam() {
        return ownerTeam;
    }

    /**
     * Sets the name of the team that schedule belongs to.
     */
    public void setOwnerTeam(DataWithName ownerTeam) {
        this.ownerTeam = ownerTeam;
    }

    @Override
    public void setTime() throws ParseException {
        if (getTimeZone() != null && rotations != null && rotations.size() > 0) {
            for (ScheduleRotation scheduleRotation : rotations) {
                scheduleRotation.setScheduleTimeZone(getTimeZone());
                SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
                sdf.setTimeZone(getTimeZone());
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


    public Schedule withName(String name) {
        this.name = name;
        return this;
    }

    public Schedule withOwnerTeam(DataWithName team) {
        this.ownerTeam = team;
        return this;
    }

    public Schedule withDescription(String description) {
        this.description = description;
        return this;
    }

    public Schedule withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public Schedule withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Schedule withRotations(List<ScheduleRotation> rotations) {
        this.rotations = rotations;
        return this;
    }

}
