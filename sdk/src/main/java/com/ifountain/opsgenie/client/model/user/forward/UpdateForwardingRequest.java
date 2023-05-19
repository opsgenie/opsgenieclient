package com.ifountain.opsgenie.client.model.user.forward;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.ifountain.opsgenie.client.model.beans.ForwardingIdentifierType;
import com.ifountain.opsgenie.client.model.beans.Team;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
 */
public class UpdateForwardingRequest extends BaseRequest<UpdateForwardingResponse, UpdateForwardingRequest> implements ObjectWithTimeZone {
    @JsonIgnore
    private String identifier;
    @JsonIgnore
    private String identifierType;
    private Team.User fromUser;
    private Team.User toUser;
    private String startDate;
    private String endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone = TimeZone.getTimeZone("GMT");


    /**
     * Rest api uri of update forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules/"+ identifier;
    }

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when alias and id both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(identifierType) && Objects.isNull(ForwardingIdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ALIAS, OpsGenieClientConstants.API.ID);
        if (fromUser == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.FROM_USER);
        if(toUser == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.TO_USER);
        if(!isValidDate(startDate))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.START_DATE);
        if(!isValidDate(endDate))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.END_DATE);
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
     Gets identifierType - valid values are id or alias from enum ForwardingIdentifierType
     */
    public String getIdentifierType() {
        return identifierType;
    }

    /**
     * sets type of identifier - valid values should be one of the ForwardingIdentifierType enum mentioned
     */
    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * A user defined identifier for the forwarding. Provides ability to assign
     * a known id and later use this id to perform additional actions for the
     * same forwarding. If a forwarding exists with specified alias for from
     * user, it will update existing one.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Username of user which forwarding will be created for
     */
    public Team.User getFromUser() {
        return fromUser;
    }

    /**
     * Sets Username of user who forwarding will be created for
     */
    public void setFromUser(Team.User fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who forwarding will be directed to
     */
    public Team.User getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who forwarding will be directed to
     */
    public void setToUser(Team.User toUser) {
        this.toUser = toUser;
    }

    /**
     * Start date of forwarding will be started
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Timezone to determine forwarding start and end dates. If not given GMT is
     * used.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timezone to determine forwarding start and end dates. If not given
     * GMT is used.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public UpdateForwardingRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public UpdateForwardingRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }

    public UpdateForwardingRequest withFromUser(Team.User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public UpdateForwardingRequest withToUser(Team.User toUser) {
        this.toUser = toUser;
        return this;
    }

    public UpdateForwardingRequest withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public UpdateForwardingRequest withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public UpdateForwardingRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateForwardingResponse createResponse() {
        return new UpdateForwardingResponse();
    }
}
