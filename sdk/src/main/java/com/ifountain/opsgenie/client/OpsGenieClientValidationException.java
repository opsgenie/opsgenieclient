package com.ifountain.opsgenie.client;

/**
 * @see OpsGenieClientValidationException
 */
public class OpsGenieClientValidationException extends OpsGenieClientException {
    public static final int MISSING_MANDATORY_PROPERTY = 4000;

    public OpsGenieClientValidationException(String message, int code) {
        super(message, code);
    }

    public static OpsGenieClientValidationException missingMandatoryProperty(String propertyName) {
        return new OpsGenieClientValidationException("Missing mandatory property [" + propertyName + "]", MISSING_MANDATORY_PROPERTY);
    }

    //multiple miising property case!
    public static OpsGenieClientValidationException missingMultipleMandatoryProperty(String... propertyNames) {
        if (propertyNames != null && propertyNames.length > 0) {
            String missingProperties = "[" + propertyNames[0];
            for (int i = 1; i < propertyNames.length; i++)
                missingProperties += " or " + propertyNames[i];
            missingProperties += "]";
            return new OpsGenieClientValidationException("Missing mandatory property " + missingProperties, MISSING_MANDATORY_PROPERTY);
        }
        return null;
    }
}
