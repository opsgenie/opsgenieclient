package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Team.TeamMember;

import java.util.List;

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

    /**
     * Rest api uri of addding team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
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
