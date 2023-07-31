package com.ifountain.opsgenie.client.model.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.BaseUserObj;
import com.ifountain.opsgenie.client.model.beans.IdentifierType;
import com.ifountain.opsgenie.client.model.beans.Role;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Container for the parameters to make an add team api call.
 *
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeamMember(AddTeamMemberRequest)
 */
public class AddTeamMemberRequest extends BaseRequest<AddTeamMemberResponse, AddTeamMemberRequest> {
    @JsonIgnore
    private String teamIdentifier;
    @JsonIgnore
    private String teamIdentifierType;
    private BaseUserObj user;
    private Role role;

    /**
     * Rest api uri of addding team member operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/teams/" + teamIdentifier + "/members";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isNotEmpty(teamIdentifierType) && Objects.isNull(IdentifierType.getFromValues(teamIdentifierType)))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE);
        if (StringUtils.isEmpty(teamIdentifier))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.NAME, OpsGenieClientConstants.API.ID);
        if(user == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER);
        if(StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getId()))
            throw OpsGenieClientValidationException.error("Either id or username should be present in user field");
    }

    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(StringUtils.isNotEmpty(teamIdentifierType))
            params.put(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE,teamIdentifierType);
        else
            params.put(OpsGenieClientConstants.API.TEAM_IDENTIFIER_TYPE,OpsGenieClientConstants.API.ID);
        return params;
    }

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public String getTeamIdentifierType() {
        return teamIdentifierType;
    }

    public void setTeamIdentifierType(String teamIdentifierType) {
        this.teamIdentifierType = teamIdentifierType;
    }

    public BaseUserObj getUser() {
        return user;
    }

    public void setUser(BaseUserObj user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddTeamMemberResponse createResponse() {
        return new AddTeamMemberResponse();
    }

    public AddTeamMemberRequest withTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
        return this;
    }

    public AddTeamMemberRequest withTeamIdentifierType(String teamIdentifierType) {
        this.teamIdentifierType = teamIdentifierType;
        return this;
    }

    public AddTeamMemberRequest withRole(Role role) {
        this.role = role;
        return this;
    }

    public AddTeamMemberRequest withUser(BaseUserObj user) {
        this.user = user;
        return this;
    }
}
