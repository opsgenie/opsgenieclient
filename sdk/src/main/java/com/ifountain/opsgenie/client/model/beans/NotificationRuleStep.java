package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Contact bean
 */
public class NotificationRuleStep extends BeanWithId {
    private Method method;
    private String to;
    private Integer sendAfter;
    private Boolean enabled;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Integer getSendAfter() {
        return sendAfter;
    }

    public void setSendAfter(Integer sendAfter) {
        this.sendAfter = sendAfter;
    }


    public NotificationRuleStep withMethod(Method method) {
        this.method = method;
        return this;
    }

    public NotificationRuleStep withTo(String to) {
        this.to = to;
        return this;
    }

    public NotificationRuleStep withSendAfter(Integer sendAfter) {
        this.sendAfter = sendAfter;
        return this;
    }

    public NotificationRuleStep withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
