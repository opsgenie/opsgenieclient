package com.ifountain.opsgenie.client.model.beans;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ForwardingIdentifierType {
    ID("id"),

    ALIAS("alias");

    private String value;

    ForwardingIdentifierType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ForwardingIdentifierType getFromValues(String value) {
        for (ForwardingIdentifierType b : ForwardingIdentifierType.values()) {
            if (String.valueOf(b.value).equals(value)) {
                return b;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}