package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to remove a team member api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeamMember(DeleteTeamMemberRequest)
 */
public class DeleteTeamMemberRequest extends BaseRequest<DeleteTeamMemberResponse> {
    private String id;
    private String name;
    private String userId;
    private String username;

    @Override
    public String getEndPoint() {
        return "/v1/json/team/member";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteTeamMemberResponse createResponse() {
        return new DeleteTeamMemberResponse();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
