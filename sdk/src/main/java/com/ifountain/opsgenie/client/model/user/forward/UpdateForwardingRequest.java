package com.ifountain.opsgenie.client.model.user.forward;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import com.ifountain.opsgenie.client.model.beans.BaseUserObj;
import com.ifountain.opsgenie.client.model.beans.ForwardingIdentifierType;
import org.apache.commons.lang3.StringUtils;

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
    private BaseUserObj fromUser;
    private BaseUserObj toUser;
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
        if(StringUtils.isEmpty(fromUser.getUsername()) && StringUtils.isEmpty(fromUser.getId()))
            throw OpsGenieClientValidationException.error("Either username or id in from user is mandatory");
        if(toUser == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.TO_USER);
        if(StringUtils.isEmpty(toUser.getUsername()) && StringUtils.isEmpty(toUser.getId()))
            throw OpsGenieClientValidationException.error("Either username or id in to user is mandatory");
        if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.START_DATE,OpsGenieClientConstants.API.END_DATE);
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
     * Gets User which forwarding will be created for
     */
    public BaseUserObj getFromUser() {
        return fromUser;
    }

    /**
     * Sets User who forwarding will be created for
     */
    public void setFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username and id of user who forwarding will be directed to
     */
    public BaseUserObj getToUser() {
        return toUser;
    }

    /**
     * Sets username and id of user who forwarding will be directed to
     */
    public void setToUser(BaseUserObj toUser) {
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

    public UpdateForwardingRequest withFromUser(BaseUserObj fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public UpdateForwardingRequest withToUser(BaseUserObj toUser) {
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
