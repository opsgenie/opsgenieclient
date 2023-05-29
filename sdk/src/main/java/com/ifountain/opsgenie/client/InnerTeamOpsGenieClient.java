package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.team.*;
import com.ifountain.opsgenie.client.model.team.routing_rule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:08 PM
 */
public class InnerTeamOpsGenieClient implements ITeamOpsGenieClient {
    private JsonOpsgenieHttpClient httpClient;

    /**
     * Constructs a new team client to invoke service methods on OpsGenie for
     * teams using the specified client and root URI.
     */
    public InnerTeamOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeam(com.ifountain.opsgenie.client.model.team.AddTeamRequest)
     */
    @Override
    public AddTeamResponse addTeam(AddTeamRequest addTeamRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (AddTeamResponse) httpClient.doPostRequestV2(addTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeam(com.ifountain.opsgenie.client.model.team.UpdateTeamRequest)
     */
    @Override
    public UpdateTeamResponse updateTeam(UpdateTeamRequest updateTeamRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (UpdateTeamResponse) httpClient.doPatchRequestV2(updateTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeam(com.ifountain.opsgenie.client.model.team.DeleteTeamRequest)
     */
    @Override
    public DeleteTeamResponse deleteTeam(DeleteTeamRequest deleteTeamRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (DeleteTeamResponse) httpClient.doDeleteRequestV2(deleteTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeam(com.ifountain.opsgenie.client.model.team.GetTeamRequest)
     */
    @Override
    public GetTeamResponse getTeam(GetTeamRequest getTeamRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (GetTeamResponse) httpClient.doGetRequestV2(getTeamRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeams(com.ifountain.opsgenie.client.model.team.ListTeamsRequest)
     */
    @Override
    public ListTeamsResponse listTeams(ListTeamsRequest listTeamsRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (ListTeamsResponse) httpClient.doGetRequestV2(listTeamsRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamLogs(com.ifountain.opsgenie.client.model.team.ListTeamLogsRequest)
     */
    @Override
    public ListTeamLogsResponse listTeamLogs(ListTeamLogsRequest listTeamLogsRequest)
            throws ParseException, OpsGenieClientException, IOException {
        return (ListTeamLogsResponse) httpClient.doGetRequestV2(listTeamLogsRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeamMember(com.ifountain.opsgenie.client.model.team.AddTeamMemberRequest)
     */
    @Override
    public AddTeamMemberResponse addTeamMember(AddTeamMemberRequest addTeamMemberRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (AddTeamMemberResponse) httpClient.doPostRequest(addTeamMemberRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeamMember(com.ifountain.opsgenie.client.model.team.DeleteTeamMemberRequest)
     */
    @Override
    public DeleteTeamMemberResponse deleteTeamMember(DeleteTeamMemberRequest deleteTeamMemberRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (DeleteTeamMemberResponse) httpClient.doDeleteRequest(deleteTeamMemberRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#addTeamRoutingRule(com.ifountain.opsgenie.client.model.team.routing_rule.AddTeamRoutingRuleRequest)
     */
    @Override
    public AddTeamRoutingRuleResponse addTeamRoutingRule(AddTeamRoutingRuleRequest addTeamRoutingRuleRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (AddTeamRoutingRuleResponse) httpClient.doPostRequest(addTeamRoutingRuleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#updateTeamRoutingRule(com.ifountain.opsgenie.client.model.team.routing_rule.UpdateTeamRoutingRuleRequest)
     */
    @Override
    public UpdateTeamRoutingRuleResponse updateTeamRoutingRule(
            UpdateTeamRoutingRuleRequest updateTeamRoutingRuleRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (UpdateTeamRoutingRuleResponse) httpClient.doPostRequest(updateTeamRoutingRuleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#changeTeamRoutingRuleOrder(com.ifountain.opsgenie.client.model.team.routing_rule.ChangeTeamRoutingRuleOrderRequest)
     */
    @Override
    public ChangeTeamRoutingRuleOrderResponse changeTeamRoutingRuleOrder(ChangeTeamRoutingRuleOrderRequest changeTeamRoutingRuleOrderRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (ChangeTeamRoutingRuleOrderResponse) httpClient.doPostRequest(changeTeamRoutingRuleOrderRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#deleteTeamRoutingRule(com.ifountain.opsgenie.client.model.team.routing_rule.DeleteTeamRoutingRuleRequest)
     */
    @Override
    public DeleteTeamRoutingRuleResponse deleteTeamRoutingRule(
            DeleteTeamRoutingRuleRequest deleteTeamRoutingRuleRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (DeleteTeamRoutingRuleResponse) httpClient.doDeleteRequest(deleteTeamRoutingRuleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#getTeamRoutingRule(com.ifountain.opsgenie.client.model.team.routing_rule.GetTeamRoutingRuleRequest)
     */
    @Override
    public GetTeamRoutingRuleResponse getTeamRoutingRule(GetTeamRoutingRuleRequest getTeamRoutingRuleRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (GetTeamRoutingRuleResponse) httpClient.doGetRequest(getTeamRoutingRuleRequest);
    }

    /**
     * @see com.ifountain.opsgenie.client.ITeamOpsGenieClient#listTeamRoutingRules(com.ifountain.opsgenie.client.model.team.routing_rule.ListTeamRoutingRulesRequest)
     */
    @Override
    public ListTeamRoutingRulesResponse listTeamRoutingRules(ListTeamRoutingRulesRequest listTeamRoutingRulesRequest)
            throws IOException, OpsGenieClientException, ParseException {
        return (ListTeamRoutingRulesResponse) httpClient.doGetRequest(listTeamRoutingRulesRequest);
    }

}
