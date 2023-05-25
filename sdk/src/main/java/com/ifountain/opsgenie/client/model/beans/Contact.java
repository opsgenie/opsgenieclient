package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;


/**
 * Contact bean
 */
public class Contact extends Bean {
    private Method method;
    private String to;
    private Status status;
    private String id;
    private Integer applyOrder;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getApplyOrder() {
        return applyOrder;
    }

    public void setApplyOrder(Integer applyOrder) {
        this.applyOrder = applyOrder;
    }

    public Contact withMethod(Method method) {
        this.method = method;
        return this;
    }

    public Contact withTo(String to) {
        this.to = to;
        return this;
    }

    public Contact withEnabled(Status status) {
        this.status = status;
        return this;
    }

    public Contact withId(String id) {
        this.id = id;
        return this;
    }

    public enum Method {
        EMAIL(OpsGenieClientConstants.API.EMAIL), SMS(OpsGenieClientConstants.API.SMS),
        VOICE(OpsGenieClientConstants.API.VOICE);
        private String value;

        Method(String value) {
            this.value = value;
        }

        @JsonCreator
        public static Method fromName(String value) {
            for (Method method : Method.values()) {
                if (method.value().equalsIgnoreCase(value))
                    return method;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }
}
