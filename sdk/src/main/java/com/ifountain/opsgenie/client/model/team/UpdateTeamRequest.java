package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.util.List;

/**
 * Container for the parameters to make an update team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeam(UpdateTeamRequest)
 */
public class UpdateTeamRequest extends BaseRequest<UpdateTeamResponse, UpdateTeamRequest> {
    private String id;
    private String name;
    private String description;
    private List<Team.TeamMember> members;


    /**
     * Id of team to be updated.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of team to be updated.
     */
    public void setId(String id) {
        this.id = id;
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
    public List<Team.TeamMember> getMembers() {
        return members;
    }

    /**
     * Sets the members of team.
     */
    public void setMembers(List<Team.TeamMember> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateTeamRequest withId(String id) {
        this.id = id;
        return this;
    }

    public UpdateTeamRequest withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateTeamRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdateTeamRequest withMembers(List<Team.TeamMember> members) {
        this.members = members;
        return this;
    }

    /**
     * Rest api uri of updating team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
    }


    @Override
    public UpdateTeamResponse createResponse() {
        return new UpdateTeamResponse();
    }
}
