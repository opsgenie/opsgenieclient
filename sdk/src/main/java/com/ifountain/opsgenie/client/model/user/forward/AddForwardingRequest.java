package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.util.TimeZone;

/**
 * Container for the parameters to make an add forwarding api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#addForwarding(AddForwardingRequest)
 */
public class AddForwardingRequest extends BaseRequest<AddForwardingResponse, AddForwardingRequest> implements ObjectWithTimeZone {
    private String alias;
    private Team.User fromUser;
    private Team.User toUser;
    private String startDate;
    private String endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone = TimeZone.getTimeZone("GMT");

    /**
     * Rest api uri of forwarding create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules";
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddForwardingResponse createResponse() {
        return new AddForwardingResponse();
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public AddForwardingRequest withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public AddForwardingRequest withFromUser(Team.User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public AddForwardingRequest withToUser(Team.User toUser) {
        this.toUser = toUser;
        return this;
    }

    public AddForwardingRequest withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public AddForwardingRequest withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public AddForwardingRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

}
