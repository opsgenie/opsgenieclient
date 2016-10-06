package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * WhoIsOnCall bean
 */

public class WhoIsOnCall extends BaseWhoIsOnCall {
    private List<WhoIsOnCall> participants;
    @JsonProperty("isEnabled")
    private Boolean enabled;

    private Boolean forwarded;
    private WhoIsOnCall forwardedFrom;

    private Integer escalationTime;
    private String notifyType;

    public List<WhoIsOnCall> getParticipants() {
        return participants;
    }

    public void setParticipants(List<WhoIsOnCall> participants) {
        this.participants = participants;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getForwarded() {
        return forwarded;
    }

    public void setForwarded(Boolean forwarded) {
        this.forwarded = forwarded;
    }

    public WhoIsOnCall getForwardedFrom() {
        return forwardedFrom;
    }

    public void setForwardedFrom(WhoIsOnCall forwardedFrom) {
        this.forwardedFrom = forwardedFrom;
    }

    public Integer getEscalationTime() {
        return escalationTime;
    }

    public void setEscalationTime(Integer escalationTime) {
        this.escalationTime = escalationTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }
}
