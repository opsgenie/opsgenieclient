package com.ifountain.opsgenie.client.model.team;

import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a list teams api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeams(ListTeamsRequest)
 */
public class ListTeamsRequest extends BaseRequest<ListTeamsResponse, ListTeamsRequest> {
    @Override
    public ListTeamsResponse createResponse() {
        return new ListTeamsResponse();
    }

    /**
     * Rest api uri of list teams operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/team";
    }
}
