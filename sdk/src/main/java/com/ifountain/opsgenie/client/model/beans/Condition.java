package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Condition bean
 */
public class Condition extends Bean {
    private Boolean not;
    private Field field;
    private String expectedValue;
    private Operation operation;

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public static enum Field {
        ACTIONS(OpsGenieClientConstants.API.ACTIONS),
        ALIAS(OpsGenieClientConstants.API.ALIAS),
        DESCRIPTION(OpsGenieClientConstants.API.DESCRIPTION),
        ENTITY(OpsGenieClientConstants.API.ENTITY),
        EXTRA_PROPERTIES(OpsGenieClientConstants.API.EXTRA_PROPERTIES),
        MESSAGE(OpsGenieClientConstants.API.MESSAGE),
        RECIPIENTS(OpsGenieClientConstants.API.RECIPIENTS),
        SOURCE(OpsGenieClientConstants.API.SOURCE),
        TAGS(OpsGenieClientConstants.API.TAGS),
        TEAMS(OpsGenieClientConstants.API.TEAMS);
        private String value;

        Field(String value) {
            this.value = value;
        }

        @JsonCreator
        public static Field fromValue(String value) {
            if (value == null)
                return null;
            for (Field field : Field.values()) {
                if (field.value().toLowerCase().equals(value.toLowerCase()))
                    return field;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

    public static enum Operation {
        CONTAINS(OpsGenieClientConstants.API.OPERATION_CONTAINS),
        EQUALS(OpsGenieClientConstants.API.OPERATION_EQUALS),
        STARTS_WITH(OpsGenieClientConstants.API.OPERATION_STARTS_WITH),
        ENDS_WITH(OpsGenieClientConstants.API.OPERATION_ENDS_WITH),
        MATCHES(OpsGenieClientConstants.API.OPERATION_MATCHES),
        IS_EMPTY(OpsGenieClientConstants.API.OPERATION_IS_EMPTY),
        EQUALS_IGNORE_WHITE_SPACE(OpsGenieClientConstants.API.OPERATION_EQUALS_IGNORE_WHITE_SPACE),
        CONTAINS_KEY(OpsGenieClientConstants.API.OPERATION_CONTAINS_KEY),
        CONTAINS_VALUE(OpsGenieClientConstants.API.OPERATION_CONTAINS_VALUE);

        private String value;

        Operation(String value) {
            this.value = value;
        }

        @JsonCreator
        public static Operation fromValue(String value) {
            if (value == null)
                return null;
            for (Operation operation : Operation.values()) {
                if (operation.value().toLowerCase().equals(value.toLowerCase()))
                    return operation;
            }
            return null;
        }

        @JsonValue
        public String value() {
            return value;
        }
    }

}
