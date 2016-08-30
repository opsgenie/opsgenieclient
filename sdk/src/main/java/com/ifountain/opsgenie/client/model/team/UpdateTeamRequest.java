package com.ifountain.opsgenie.client.model.team;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Container for the parameters to make an update team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 11:59 AM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeam(UpdateTeamRequest)
 */
public class UpdateTeamRequest extends AddTeamRequest {
	@JsonSerialize(include=Inclusion.ALWAYS)
    private String id;

    /**
     * Rest api uri of updating team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }

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

    @Override
    public UpdateTeamResponse createResponse() {
        return new UpdateTeamResponse();
    }
}
