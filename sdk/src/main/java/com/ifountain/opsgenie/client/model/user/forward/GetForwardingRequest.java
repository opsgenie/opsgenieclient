package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ForwardingIdentifierType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to make a get forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingRequest extends BaseRequest<GetForwardingResponse, GetForwardingRequest> {
    private String identifier;
    private String identifierType;

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when alias and id both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(identifierType) && Objects.isNull(ForwardingIdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ALIAS, OpsGenieClientConstants.API.ID);
    }

    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(Objects.nonNull(identifierType))
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,identifierType);
        else
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    /**
     * Rest api uri of get forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules/" + identifier;
    }

    /**
     * A user defined identifier for the forwarding. Provides ability to assign
     * a known identifier and later use this identifier to get forwarding
     * details.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     Gets identifierType - valid values are id or alias from enum ForwardingIdentifierType
     */
    public String getIdentifierType() {
        return identifierType;
    }

    /**
     * sets type of identifier - valid values should be one of the ForwardingIdentifierType enum mentioned
     */
    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetForwardingResponse createResponse() {
        return new GetForwardingResponse();
    }

    public GetForwardingRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public GetForwardingRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }
}
