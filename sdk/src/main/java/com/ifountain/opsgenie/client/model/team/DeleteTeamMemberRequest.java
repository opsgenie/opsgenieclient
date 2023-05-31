package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to remove a team member api call.
 *
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeamMember(DeleteTeamMemberRequest)
 */
public class DeleteTeamMemberRequest extends BaseRequest<DeleteTeamMemberResponse, DeleteTeamMemberRequest> {
    private String teamIdentifier;
    private String memberIdentifier;
    private String teamIdentifierType;

    @Override
    public String getEndPoint() {
        return "/v2/teams/"+ teamIdentifier + "/members/" + memberIdentifier;
    }

    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(Objects.nonNull(teamIdentifierType))
            params.put(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE,teamIdentifierType);
        else
            params.put(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(Objects.nonNull(teamIdentifierType) && Objects.isNull(IdentifierType.getFromValues(teamIdentifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE);
        if (teamIdentifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
        if(memberIdentifier == null)
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.USERNAME,OpsGenieClientConstants.API.USER_ID);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteTeamMemberResponse createResponse() {
        return new DeleteTeamMemberResponse();
    }

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public String getMemberIdentifier() {
        return memberIdentifier;
    }

    public void setMemberIdentifier(String memberIdentifier) {
        this.memberIdentifier = memberIdentifier;
    }

    public String getTeamIdentifierType() {
        return teamIdentifierType;
    }

    public void setTeamIdentifierType(String teamIdentifierType) {
        this.teamIdentifierType = teamIdentifierType;
    }

    public DeleteTeamMemberRequest withTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
        return this;
    }

    public DeleteTeamMemberRequest withTeamIdentifierType(String teamIdentifierType) {
        this.teamIdentifierType = teamIdentifierType;
        return this;
    }

    public DeleteTeamMemberRequest withMemberIdentifier(String memberIdentifier) {
        this.memberIdentifier = memberIdentifier;
        return this;
    }
}
