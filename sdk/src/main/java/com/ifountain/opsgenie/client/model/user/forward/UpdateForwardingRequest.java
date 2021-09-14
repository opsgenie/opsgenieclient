package com.ifountain.opsgenie.client.model.user.forward;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;


import java.util.Date;
import java.util.TimeZone;

/**
 * Container for the parameters to make an update forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#updateForwarding(com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest)
 */
public class UpdateForwardingRequest extends BaseRequest<UpdateForwardingResponse, UpdateForwardingRequest> implements ObjectWithTimeZone {
    private String id;
    private String alias;
    private String fromUser;
    private String toUser;
    private Date startDate;
    private Date endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone = TimeZone.getTimeZone("GMT");


    /**
     * Rest api uri of update forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be updated.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the forwarding. Provides ability to assign
     * a known id and later use this id to perform additional actions for the
     * same forwarding. If a forwarding exists with specified alias for from
     * user, it will update existing one.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Username of user which forwarding will be created for
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets Username of user who forwarding will be created for
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * Username of user who forwarding will be directed to
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets username of user who forwarding will be directed to
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * Start date of forwarding will be started
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of forwarding will be started
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of forwarding will be discarded
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of forwarding will be discarded
     */
    public void setEndDate(Date endDate) {
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

    public UpdateForwardingRequest withId(String id) {
        this.id = id;
        return this;
    }

    public UpdateForwardingRequest withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public UpdateForwardingRequest withFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public UpdateForwardingRequest withToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public UpdateForwardingRequest withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public UpdateForwardingRequest withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public UpdateForwardingRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when alias and id both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getId() == null && getAlias() == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ALIAS, OpsGenieClientConstants.API.ID);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateForwardingResponse createResponse() {
        return new UpdateForwardingResponse();
    }
}
