package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseGetRequest;

import java.util.Map;

/**
 * Container for the parameters to make a get team api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:00 PM
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(GetTeamRequest)
 */
public class GetTeamRequest extends BaseGetRequest<GetTeamResponse> {
    private String name;

    /**
     * Rest api uri of getting team operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }

    /**
     * Name of team to be queried.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of team to be queried.
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void _serialize(Map json) {
        if (getName() != null) {
            json.put(OpsGenieClientConstants.API.NAME, getName());
        }
    }

    @Override
    public GetTeamResponse createResponse() {
        return new GetTeamResponse();
    }

}
