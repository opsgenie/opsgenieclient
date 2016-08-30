package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a delete team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:00 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeam(DeleteTeamRequest)
 */
public class DeleteTeamRequest extends BaseRequest<DeleteTeamResponse>{
    private String id;
    private String name;
    /**
     * Rest api uri of deleting team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }

    /**
     * Id of team to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of team to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of team to be deleted.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of team to be deleted.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DeleteTeamResponse createResponse() {
        return new DeleteTeamResponse();
    }
}
