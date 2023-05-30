package com.ifountain.opsgenie.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.beans.*;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Base class for container objects which provides content parameters for
 * OpsGenie service calls.
 *
 * @author Sezgin Kucukkaraaslan
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public abstract class BaseRequest<T extends BaseResponse,K extends BaseRequest> implements Request {
    @JsonIgnore
    private String apiKey;

    /**
     * Api key used for authenticating API requests.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public K withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return (K) this;
    }

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @JsonIgnore
    public void validate() throws OpsGenieClientValidationException {
        if (apiKey == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.API_KEY);
    }

    @JsonIgnore
    public Map<String,Object> getRequestParams(){
        return new HashMap<>();
    }

    @JsonIgnore
    public Map<String,String> getReqHeadersForGetOrDelete(){
        return addGenieKey();
    }

    public boolean isValidDate(String dateStr) {
        try {
            Instant instantDate = Instant.parse(dateStr);
        }
        catch (DateTimeParseException e){
            return false;
        }
        return true;
    }

    public void validateRotations(List<ScheduleRotation> rotations) throws OpsGenieClientValidationException {
        if(Objects.nonNull(rotations)){
            for(ScheduleRotation rotation : rotations){
                validateScheduleRotation(rotation);
            }
        }
    }

    private void validateScheduleRotation(ScheduleRotation rotation) throws OpsGenieClientValidationException {
        if(rotation.getRotationType() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ROTATION_TYPE);
        if(rotation.getStartDate() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.START_DATE);
        if(!isValidDate(rotation.getStartDate()))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.START_DATE);
        if(rotation.getEndDate()!=null){
            if(!isValidDate(rotation.getEndDate()))
                throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.END_DATE);
            Instant startDate = Instant.parse(rotation.getStartDate());
            Instant endDate = Instant.parse(rotation.getEndDate());
            if(startDate.isAfter(endDate))
                throw OpsGenieClientValidationException.error("Rotation end time should be later than start time.");
        }
        if(rotation.getParticipants().isEmpty())
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.PARTICIPANTS);
        validateParticipants(rotation.getParticipants());
        if(rotation.getTimeRestriction()!=null)
            validateTimeRestriction(rotation.getTimeRestriction());
    }

    private void validateParticipants(List<ScheduleParticipant> participants) throws OpsGenieClientValidationException {
        for(ScheduleParticipant participant : participants){
            if(participant.getType() == null)
                throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.PARTICIPANT_TYPE);
            if(participant.getType().equals(ScheduleParticipant.Type.team) || participant.getType().equals(ScheduleParticipant.Type.escalation)){
                if(StringUtils.isEmpty(participant.getId()) && StringUtils.isEmpty(participant.getName())){
                    throw OpsGenieClientValidationException.error("For participant type team/escalation either team/escalation name or id must be provided.");
                }
            }
            else if(participant.getType()!= ScheduleParticipant.Type.none){
                if(StringUtils.isEmpty(participant.getId()) && StringUtils.isEmpty(participant.getUsername())){
                    throw OpsGenieClientValidationException.error("Username or id must be provided.");
                }
            }
        }
    }

    private void validateTimeRestriction(TimeRestriction timeRestriction) throws OpsGenieClientValidationException {
        if(TimeRestrictionType.getFromValues(timeRestriction.getType())!= null){
            if(TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY.getValue().equalsIgnoreCase(timeRestriction.getType())){
                validateRestrictions(timeRestriction.getRestrictions(),TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY);
            }
            else if(TimeRestrictionType.TIME_OF_DAY.getValue().equalsIgnoreCase(timeRestriction.getType())){
                validateRestrictions(timeRestriction.getRestrictions(),TimeRestrictionType.TIME_OF_DAY);
            }
        }
        else
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.TIME_RESTRICTION_TYPE);
    }

    private void validateRestrictions(List<Restriction> restrictions, TimeRestrictionType timeRestrictionType) throws OpsGenieClientValidationException {
        if(!restrictions.isEmpty()){
            for(Restriction restriction: restrictions){
                validateRestriction(restriction,timeRestrictionType);
            }
        }
        else
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.RESTRICTIONS);
    }

    private void validateRestriction(Restriction restriction, TimeRestrictionType restrictionType) throws OpsGenieClientValidationException {
        if(restriction.getEndHour() == null || restriction.getEndMin() == null || restriction.getStartHour() == null || restriction.getStartMin() == null){
            throw OpsGenieClientValidationException.error("startHour, startMin, endHour, endMin cannot be empty");
        }
        if(restrictionType.equals(TimeRestrictionType.WEEKDAY_AND_TIME_OF_DAY) && (restriction.getEndDay() == null || restriction.getStartDay() == null)){
            throw OpsGenieClientValidationException.error("startDay, endDay cannot be empty");
        }
        if(restriction.getStartHour() >24)
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.RESTRICTION_START_HOUR);
        if(restriction.getEndHour() >24)
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.RESTRICTION_END_HOUR);
        //Minutes may take 0 or 30 as value. Otherwise they will be converted to nearest 0 or 30 automatically. So startMin and endMin do not require validation check
    }

    @JsonIgnore
    public Map<String,String> getReqHeadersForPostOrPatch(){
        Map<String, String> headers = addGenieKey();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        return headers;
    }

    private Map<String, String> addGenieKey() {
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","GenieKey "+apiKey);
        return headers;
    }

    /**
     * @deprecated Use getApiKey
     */
    @JsonIgnore
    @Deprecated
    public String getCustomerKey() {
        return apiKey;
    }

    /**
     * @deprecated Use setApiKey
     */
    @Deprecated
    public void setCustomerKey(String apiKey) {
        setApiKey(apiKey);
    }

    /**
     * convertes request to map
     */
    @Deprecated
    public Map serialize() throws OpsGenieClientValidationException {
        validate();
        try {
            return JsonUtils.toMap(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create response object
     */
    public abstract T createResponse();
}
