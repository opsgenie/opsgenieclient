package com.ifountain.opsgenie.client.model.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;

import java.util.Date;
import java.util.TimeZone;

/**
 * Base Container for the parameters to make a who is on call api call.
 */
abstract class AbstractWhoIsOnCallRequest<T extends BaseResponse, K extends AbstractWhoIsOnCallRequest> extends BaseRequest<T, K> implements ObjectWithTimeZone {
    private String name;
    private Date time;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    private String id;

    /**
     * Id of object to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of object to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Target date of WhoIsOnCall request
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets target date of WhoIsOnCall request
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * TimeZone for request
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets timeZone for request
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Name of schedule to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of schedule to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEndPoint() {
        return "/v1.1/json/schedule/whoIsOnCall";
    }

    @Override
    public TimeZone getObjectTimeZone() {
        return timeZone;
    }

    public K withName(String name) {
        this.name = name;
        return (K) this;
    }

    public K withTime(Date time) {
        this.time = time;
        return (K) this;
    }

    public K withTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        return (K) this;
    }

    public K withId(String id) {
        this.id = id;
        return (K) this;
    }

}
