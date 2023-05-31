package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to make a delete team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeam(DeleteTeamRequest)
 */
public class DeleteTeamRequest extends BaseRequest<DeleteTeamResponse, DeleteTeamRequest> {
    private String identifier;
    private String identifierType;
    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when name and id are both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(identifierType) && Objects.isNull(IdentifierType.getFromValues(identifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.IDENTIFIER_TYPE);
        if (identifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
    }

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

    /**
     * Rest api uri of deleting team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/teams/"+identifier;
    }

    /**
     * Rest api params of deleting team operation.
     */
    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(Objects.nonNull(identifierType))
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,identifierType);
        else
            params.put(OpsGenieClientConstants.API.IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    @Override
    public DeleteTeamResponse createResponse() {
        return new DeleteTeamResponse();
    }

    public DeleteTeamRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public DeleteTeamRequest withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }

}
