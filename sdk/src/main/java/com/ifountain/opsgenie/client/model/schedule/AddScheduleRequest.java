package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.ifountain.opsgenie.client.model.beans.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add schedule api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addSchedule(AddScheduleRequest)
 */
public class AddScheduleRequest extends BaseRequest<AddScheduleResponse, AddScheduleRequest> implements ObjectWithTimeZone {
    private String name;
    private Boolean enabled;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private List<ScheduleRotation> rotations;
    private DataWithName ownerTeam;
    private String description;

    /**
     * Rest api uri of addding schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/schedules";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isEmpty(name))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.NAME);
        validateRotations(rotations);
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
        if (getTimeZone() != null && rotations != null)
            for (ScheduleRotation scheduleRotation : rotations)
                scheduleRotation.setScheduleTimeZone(getTimeZone());
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddScheduleResponse createResponse() {
        return new AddScheduleResponse();
    }

    /**
     * Get Name of the team that the schedule will be assigned to. If left empty, the schedule will
     * be global and not belong to any team.
     *
     * @return String team Name
     */
    public DataWithName getOwnerTeam() {
        return ownerTeam;

    }

    /**
     * Sets Name of the team that the schedule will be assigned to. If left empty, the schedule will
     * be global and not belong to any team.
     *
     * @param ownerTeam String team Name
     */
    public void setOwnerTeam(DataWithName ownerTeam) {
        this.ownerTeam = ownerTeam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public AddScheduleRequest withName(String name) {
        this.name = name;
        return this;
    }

    public AddScheduleRequest withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AddScheduleRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public AddScheduleRequest withRotations(List<ScheduleRotation> rotations) {
        this.rotations = rotations;
        return this;
    }

    public AddScheduleRequest withTeam(DataWithName team) {
        this.ownerTeam = team;
        return this;
    }

    public AddScheduleRequest withDescription(String description) {
        this.description = description;
        return this;
    }

}
