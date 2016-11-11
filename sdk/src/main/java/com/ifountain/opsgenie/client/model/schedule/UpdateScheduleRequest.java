package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.ifountain.opsgenie.client.model.beans.ScheduleRotation;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(UpdateScheduleRequest)
 */
public class UpdateScheduleRequest extends BaseRequest<AddScheduleResponse, AddScheduleRequest> implements ObjectWithTimeZone {
    private String id;
    private String name;
    private Boolean enabled;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private List<ScheduleRotation> rotations;
    private String team;
    private String description;

    /**
     * Id of schedule to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of schedule to be updated.
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
     * Get Name of the team that the schedule will be assigned to. If left empty, the schedule will
     * be global and not belong to any team.
     *
     * @return String team Name
     */
    public String getTeam() {
        return team;

    }

    /**
     * Sets Name of the team that the schedule will be assigned to. If left empty, the schedule will
     * be global and not belong to any team.
     *
     * @param team String team Name
     */
    public void setTeam(String team) {
        this.team = team;
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

    public UpdateScheduleRequest withId(String id) {
        this.id = id;
        return this;
    }

    public UpdateScheduleRequest withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateScheduleRequest withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UpdateScheduleRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public UpdateScheduleRequest withRotations(List<ScheduleRotation> rotations) {
        this.rotations = rotations;
        return this;
    }

    public UpdateScheduleRequest withTeam(String team) {
        this.team = team;
        return this;
    }

    public UpdateScheduleRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Rest api uri of updating schedule operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateScheduleResponse createResponse() {
        return new UpdateScheduleResponse();
    }
}
