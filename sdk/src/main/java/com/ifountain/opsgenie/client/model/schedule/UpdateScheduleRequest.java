package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.ifountain.opsgenie.client.model.beans.DataWithName;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;
import com.ifountain.opsgenie.client.model.beans.ScheduleRotation;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.HashMap;

/**
 * Container for the parameters to make an update schedule api call.
 *
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#updateSchedule(UpdateScheduleRequest)
 */
public class UpdateScheduleRequest extends BaseRequest<AddScheduleResponse, AddScheduleRequest> implements ObjectWithTimeZone {
    @JsonIgnore
    private String identifier;
    @JsonIgnore
    private String identifierType;
    private String name;
    private Boolean enabled;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private List<ScheduleRotation> rotations;
    @JsonProperty("ownerTeam")
    private DataWithName team;
    private String description;

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
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
    public DataWithName getTeam() {
        return team;

    }

    /**
     * Sets Name of the team that the schedule will be assigned to. If left empty, the schedule will
     * be global and not belong to any team.
     *
     * @param team String team Name
     */
    public void setTeam(DataWithName team) {
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

    public UpdateScheduleRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
    public UpdateScheduleRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
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

    public UpdateScheduleRequest withTeam(DataWithName team) {
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
        return "/v2/schedules/" + identifier;
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when name and id are both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(identifierType) && Objects.isNull(IdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
        validateRotations(rotations);
    }
    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(Objects.nonNull(identifierType))
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,identifierType);
        else
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateScheduleResponse createResponse() {
        return new UpdateScheduleResponse();
    }
}
