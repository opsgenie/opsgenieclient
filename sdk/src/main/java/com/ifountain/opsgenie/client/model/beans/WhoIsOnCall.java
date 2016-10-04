package com.ifountain.opsgenie.client.model.beans;

import java.util.List;

/**
 * WhoIsOnCall bean
 */

public class WhoIsOnCall extends BaseWhoIsOnCall {
    private List<WhoIsOnCall> participants;
    private Boolean isEnabled;

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

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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
