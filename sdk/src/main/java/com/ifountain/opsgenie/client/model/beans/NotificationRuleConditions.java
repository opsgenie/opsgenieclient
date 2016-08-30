package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Use Jackson
 * NotificationRuleConditions bean
 */
public class NotificationRuleConditions implements IBean {
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

        private Field(String value) {
            this.value = value;
        }

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

        private Operation(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

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

    @Override
    public Map toMap() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(OpsGenieClientConstants.API.NOT, getNot());
        if (getField() != null)
            json.put(OpsGenieClientConstants.API.FIELD, getField().value());
        json.put(OpsGenieClientConstants.API.EXPECTED_VALUE, getExpectedValue());
        if (getOperation() != null)
            json.put(OpsGenieClientConstants.API.OPERATION, getOperation().value());
        return json;
    }

    @Override
    public void fromMap(Map map) throws ParseException {
        if (map.containsKey(OpsGenieClientConstants.API.FIELD)) {
            String fieldName = (String) map.get(OpsGenieClientConstants.API.FIELD);
            for (Field field : Field.values())
                if (field.value().equals(fieldName)) {
                    setField(field);
                    break;
                }
        }
        if (map.containsKey(OpsGenieClientConstants.API.NOT))
            this.not = (Boolean) map.get(OpsGenieClientConstants.API.NOT);
        if (map.containsKey(OpsGenieClientConstants.API.EXPECTED_VALUE))
            this.expectedValue = (String) map.get(OpsGenieClientConstants.API.EXPECTED_VALUE);
        if (map.containsKey(OpsGenieClientConstants.API.OPERATION)) {
            String operationName = (String) map.get(OpsGenieClientConstants.API.OPERATION);
            for (Operation operation : Operation.values())
                if (operation.value().equals(operationName)) {
                    setOperation(operation);
                    break;
                }
        }

    }

}
