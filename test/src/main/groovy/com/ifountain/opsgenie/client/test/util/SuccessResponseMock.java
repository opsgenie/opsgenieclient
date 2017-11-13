package com.ifountain.opsgenie.client.test.util;


import com.opsgenie.oas.sdk.model.SuccessResponse;

/**
 * @author Celal Emre CICEK
 * @version 11/07/2017
 */

public class SuccessResponseMock extends SuccessResponse {
    private String identifier = null;
    private String identifierType = null;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }
}
