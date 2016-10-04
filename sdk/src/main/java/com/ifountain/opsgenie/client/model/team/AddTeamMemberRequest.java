package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.beans.Team.TeamMember.Role;

/**
 * Container for the parameters to make an add team api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeamMember(AddTeamMemberRequest)
 */
public class AddTeamMemberRequest extends BaseRequest<AddTeamMemberResponse> {
    private String id;
    private String name;
    private String userId;
    private String username;
    private Role role;

    /**
     * Rest api uri of addding team member operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team/member";
    }

    /**
     * Id of team which member will be added
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of team which member will be added
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of team which member will be added
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of team which member will be added
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddTeamMemberResponse createResponse() {
        return new AddTeamMemberResponse();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
