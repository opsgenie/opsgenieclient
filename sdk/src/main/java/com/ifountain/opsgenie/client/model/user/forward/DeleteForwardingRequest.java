package com.ifountain.opsgenie.client.model.user.forward;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.ForwardingIdentifierType;

import java.util.Objects;

/**
 * Container for the parameters to make an delete forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteForwarding(DeleteForwardingRequest)
 */
public class DeleteForwardingRequest extends BaseRequest<DeleteForwardingResponse, DeleteForwardingRequest> {
    private String identifier;
    private String identifierType;

    /**
     * Rest api uri of delete forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/forwarding-rules/"+identifier;
    }

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
     * A user defined identifier for the forwarding. Provides ability to assign
     * a known id and later use this id to perform additional actions for the
     * same forwarding. If a forwarding exists with specified alias for from
     * user, it will update existing one.
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteForwardingResponse createResponse() {
        return new DeleteForwardingResponse();
    }

    public DeleteForwardingRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public DeleteForwardingRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }
}
