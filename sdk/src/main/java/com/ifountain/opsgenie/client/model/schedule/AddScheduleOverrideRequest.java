package com.ifountain.opsgenie.client.model.schedule;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Container for the parameters to make an add schedule override api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IScheduleOpsGenieClient#addScheduleOverride(AddScheduleOverrideRequest)
 */
public class AddScheduleOverrideRequest extends BaseRequest<AddScheduleOverrideResponse, AddScheduleOverrideRequest> implements ObjectWithTimeZone {
    private String alias;
    private String schedule;
    private String user;
    private Date startDate;
    private Date endDate;
    @JsonProperty("timezone")
    private TimeZone timeZone = TimeZone.getTimeZone("GMT");
    private List<String> rotationIds;

    /**
     * Rest api uri of schedule override create operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/schedule/override";
    }

    /**
     * A user defined identifier for the schedule override. Provides ability to
     * assign a known id and later use this id to perform additional actions for
     * the same override. If an override exists with specified alias for from
     * user, it will update existing one.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the schedule override.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Id or the name of the schedule that the override will belong to.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the id or the name of the schedule that the override will belong to.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /*
     * Rotation id list that override will apply.
     */
    public List<String> getRotationIds() {
        return rotationIds;
    }

    /*
     * * Sets the rotation id list that override will apply.
     */
    public void setRotationIds(List<String> rotationId) {
        this.rotationIds = rotationId;
    }

    /**
     * Username of user whom the override will be created for.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username of user whom the override will be created for
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Start date of the schedule override.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the schedule override.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date of the schedule override.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the schedule override.
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
     * Sets timezone to determine schedule override start and end dates. If not
     * given GMT is used.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddScheduleOverrideResponse createResponse() {
        return new AddScheduleOverrideResponse();
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public AddScheduleOverrideRequest withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public AddScheduleOverrideRequest withSchedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    public AddScheduleOverrideRequest withUser(String user) {
        this.user = user;
        return this;
    }

    public AddScheduleOverrideRequest withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public AddScheduleOverrideRequest withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public AddScheduleOverrideRequest withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public AddScheduleOverrideRequest withRotationIds(List<String> rotationIds) {
        this.rotationIds = rotationIds;
        return this;
    }

}
