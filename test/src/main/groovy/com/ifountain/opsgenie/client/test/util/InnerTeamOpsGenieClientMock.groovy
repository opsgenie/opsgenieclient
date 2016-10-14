package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.ITeamOpsGenieClient
import com.ifountain.opsgenie.client.OpsGenieClientException
import com.ifountain.opsgenie.client.model.team.*
import com.ifountain.opsgenie.client.model.team.routing_rule.*

import java.text.ParseException

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 2:21 PM
 */
public class InnerTeamOpsGenieClientMock implements ITeamOpsGenieClient{
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private AddTeamResponse addTeamResponse;
    private UpdateTeamResponse updateTeamResponse;
    private DeleteTeamResponse deleteTeamResponse;
    private GetTeamResponse getTeamResponse;
    private ListTeamsResponse listTeamsResponse;
    private ListTeamLogsResponse listTeamLogsResponse;

    public InnerTeamOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public AddTeamResponse addTeam(AddTeamRequest addTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(addTeamRequest);
        return addTeamResponse;
    }

    @Override
    public UpdateTeamResponse updateTeam(UpdateTeamRequest updateTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(updateTeamRequest);
        return updateTeamResponse;
    }

    @Override
    public DeleteTeamResponse deleteTeam(DeleteTeamRequest deleteTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(deleteTeamRequest);
        return deleteTeamResponse;
    }

    @Override
    public GetTeamResponse getTeam(GetTeamRequest getTeamRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(getTeamRequest);
        return getTeamResponse;
    }

    @Override
    public ListTeamsResponse listTeams(ListTeamsRequest listTeamsRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(listTeamsRequest);
        return listTeamsResponse;
    }

    @Override
    public ListTeamLogsResponse listTeamLogs(ListTeamLogsRequest listTeamLogsRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(listTeamLogsRequest);
        return listTeamLogsResponse;
    }

    @Override
    AddTeamMemberResponse addTeamMember(AddTeamMemberRequest addTeamMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    DeleteTeamMemberResponse deleteTeamMember(DeleteTeamMemberRequest deleteTeamMemberRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    AddTeamRoutingRuleResponse addTeamRoutingRule(AddTeamRoutingRuleRequest addTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    UpdateTeamRoutingRuleResponse updateTeamRoutingRule(UpdateTeamRoutingRuleRequest updateTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    ChangeTeamRoutingRuleOrderResponse changeTeamRoutingRuleOrder(ChangeTeamRoutingRuleOrderRequest changeTeamRoutingRuleOrderRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    DeleteTeamRoutingRuleResponse deleteTeamRoutingRule(DeleteTeamRoutingRuleRequest deleteTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    GetTeamRoutingRuleResponse getTeamRoutingRule(GetTeamRoutingRuleRequest getTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    @Override
    ListTeamRoutingRulesResponse listTeamRoutingRules(ListTeamRoutingRulesRequest listTeamRoutingRulesRequest) throws IOException, OpsGenieClientException, ParseException {
        return null
    }

    public void setAddTeamResponse(AddTeamResponse addTeamResponse) {
        this.addTeamResponse = addTeamResponse;
    }

    public void setUpdateTeamResponse(UpdateTeamResponse updateTeamResponse) {
        this.updateTeamResponse = updateTeamResponse;
    }

    public void setDeleteTeamResponse(DeleteTeamResponse deleteTeamResponse) {
        this.deleteTeamResponse = deleteTeamResponse;
    }

    public void setGetTeamResponse(GetTeamResponse getTeamResponse) {
        this.getTeamResponse = getTeamResponse;
    }

    public void setListTeamsResponse(ListTeamsResponse listTeamsResponse) {
        this.listTeamsResponse = listTeamsResponse;
    }

    public void setListTeamLogsResponse(ListTeamLogsResponse listTeamLogsResponse) {
        this.listTeamLogsResponse = listTeamLogsResponse;
    }
}
