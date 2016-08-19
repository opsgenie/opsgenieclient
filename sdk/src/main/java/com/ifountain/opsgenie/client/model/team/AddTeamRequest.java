package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container for the parameters to make an add team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 11:59 AM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(AddTeamRequest)
 */
public class AddTeamRequest extends BaseRequest<AddTeamResponse>{
    private String name;
    private List<Team.TeamMember> members;


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
     * Map Members of team
     */
	@JsonProperty("members")
    public List<Map> getMembersMap() {
        if(getMembers() != null){
            List<Map> memberMaps = new ArrayList<Map>();
            for(Team.TeamMember member:getMembers())
                memberMaps.add(member.toMap());
            return memberMaps;
        }
        return null;
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

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public AddTeamResponse createResponse() {
        return new AddTeamResponse();
    }
}
