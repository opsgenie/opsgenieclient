package com.ifountain.opsgenie.client.model.beans;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum IdentifierType {
    ID("id"),

    NAME("name");

    private String value;

    IdentifierType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static IdentifierType getFromValues(String value) {
        for (IdentifierType b : IdentifierType.values()) {
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
