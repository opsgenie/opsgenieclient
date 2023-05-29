package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Links;
import com.ifountain.opsgenie.client.model.beans.Team.TeamMember;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;

/**
 * Container for the parameters to make an add team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(AddTeamRequest)
 */
public class AddTeamRequest extends BaseRequest<AddTeamResponse, AddTeamRequest> {
    private String name;
    private String description;
    private List<TeamMember> members;
    private Links links;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Rest api uri of addding team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/teams";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when api key is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (StringUtils.isEmpty(name))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.NAME);
        if(Objects.nonNull(members)){
            for(TeamMember member: members){
                if(member.getUser() == null)
                    throw OpsGenieClientValidationException.error("User should be present in member field");
                if(StringUtils.isEmpty(member.getUser().getUsername()) && StringUtils.isEmpty(member.getUser().getId()))
                    throw OpsGenieClientValidationException.error("Either username or id of user should be present");
            }
        }
    }

    /**
     * Name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the team.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Members of team
     */
    public List<TeamMember> getMembers() {
        return members;
    }

    /**
     * Sets the members of team.
     */
    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddTeamResponse createResponse() {
        return new AddTeamResponse();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddTeamRequest withName(String name) {
        this.name = name;
        return this;
    }

    public AddTeamRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public AddTeamRequest withMembers(List<TeamMember> members) {
        this.members = members;
        return this;
    }
}
