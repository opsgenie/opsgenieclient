package com.ifountain.client;

/**
 * @see ClientValidationException
 */
public class ClientValidationException extends ClientException {
    public static final int MISSING_MANDATORY_PROPERTY = 4000;
    public static final int SHOULD_SPECIFY_AT_LEAST_ONE_OF = 5000;
    public ClientValidationException(String message, int code) {
        super(message, code);
    }

    public static ClientValidationException missingMandatoryProperty(String propertyName){
        return new ClientValidationException("Missing mandatory property ["+propertyName+"]", MISSING_MANDATORY_PROPERTY);
    }
    public static ClientValidationException shouldSpecifyAtLeastOneOf(String propertyNames){
        return new ClientValidationException("You should specify at least one of "+propertyNames,SHOULD_SPECIFY_AT_LEAST_ONE_OF);
    }
}
