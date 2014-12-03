package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.team.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:08 PM
 */
public class InnerTeamOpsGenieClient implements ITeamOpsGenieClient {
    private JsonOpsgenieHttpClient httpClient;

    /**
     * Constructs a new team client to invoke service methods on OpsGenie for teams using the specified client and root URI.
     */
    public InnerTeamOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(com.ifountain.opsgenie.client.model.team.AddTeamRequest)
     */
    @Override
    public AddTeamResponse addTeam(AddTeamRequest addTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        return (AddTeamResponse) httpClient.doPostRequest(addTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeam(com.ifountain.opsgenie.client.model.team.UpdateTeamRequest)
     */
    @Override
    public UpdateTeamResponse updateTeam(UpdateTeamRequest updateTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        return (UpdateTeamResponse) httpClient.doPostRequest(updateTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeam(com.ifountain.opsgenie.client.model.team.DeleteTeamRequest)
     */
    @Override
    public DeleteTeamResponse deleteTeam(DeleteTeamRequest deleteTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DeleteTeamResponse) httpClient.doDeleteRequest(deleteTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(com.ifountain.opsgenie.client.model.team.GetTeamRequest)
     */
    @Override
    public GetTeamResponse getTeam(GetTeamRequest getTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        return (GetTeamResponse) httpClient.doGetRequest(getTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeams(com.ifountain.opsgenie.client.model.team.ListTeamsRequest)
     */
    @Override
    public ListTeamsResponse listTeams(ListTeamsRequest listTeamsRequest) throws IOException, OpsGenieClientException, ParseException {
        return (ListTeamsResponse) httpClient.doGetRequest(listTeamsRequest);
    }
}
