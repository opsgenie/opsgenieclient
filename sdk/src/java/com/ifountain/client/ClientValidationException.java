package com.ifountain.client;

/**
 * @see ClientValidationException
 */
public class ClientValidationException extends ClientException {
    public static final int MISSING_MANDATORY_PROPERTY = 4000;
    public ClientValidationException(String message, int code) {
        super(message, code);
    }

    public static ClientValidationException missingMandatoryProperty(String propertyName){
        return new ClientValidationException("Missing mandatory property ["+propertyName+"]", MISSING_MANDATORY_PROPERTY);
    }
}
