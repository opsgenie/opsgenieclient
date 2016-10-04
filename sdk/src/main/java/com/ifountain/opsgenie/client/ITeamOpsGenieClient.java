package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.team.*;
import com.ifountain.opsgenie.client.model.team.routing_rule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for team related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 12/3/2014 12:04 PM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface ITeamOpsGenieClient {
    /**
     * Adds a team at OpsGenie.
     *
     * @param addTeamRequest Object to construct request parameters.
     * @return <code>AddTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.AddTeamRequest
     * @see com.ifountain.opsgenie.client.model.team.AddTeamResponse
     */
    public AddTeamResponse addTeam(AddTeamRequest addTeamRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates team at OpsGenie.
     *
     * @param updateTeamRequest Object to construct request parameters.
     * @return <code>UpdateTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.UpdateTeamRequest
     * @see com.ifountain.opsgenie.client.model.team.UpdateTeamResponse
     */
    public UpdateTeamResponse updateTeam(UpdateTeamRequest updateTeamRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a team at OpsGenie.
     *
     * @param deleteTeamRequest Object to construct request parameters.
     * @return <code>DeleteTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.DeleteTeamRequest
     * @see com.ifountain.opsgenie.client.model.team.DeleteTeamResponse
     */
    public DeleteTeamResponse deleteTeam(DeleteTeamRequest deleteTeamRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Get team details
     *
     * @param getTeamRequest Object to construct request parameters.
     * @return <code>GetTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.GetTeamRequest
     * @see com.ifountain.opsgenie.client.model.team.GetTeamResponse
     */
    public GetTeamResponse getTeam(GetTeamRequest getTeamRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List teams of customer
     *
     * @param listTeamsRequest Object to construct request parameters.
     * @return <code>ListTeamsResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.ListTeamsRequest
     * @see com.ifountain.opsgenie.client.model.team.ListTeamsResponse
     */
    public ListTeamsResponse listTeams(ListTeamsRequest listTeamsRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Lists team logs
     *
     * @param listTeamLogsRequest Object to construct request parameters.
     * @return Object containing alert logs
     * @see com.ifountain.opsgenie.client.model.team.ListTeamLogsRequest
     * @see com.ifountain.opsgenie.client.model.team.ListTeamLogsResponse
     */
    public ListTeamLogsResponse listTeamLogs(ListTeamLogsRequest listTeamLogsRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Adds a team member at OpsGenie.
     *
     * @param addTeamMemberRequest Object to construct request parameters.
     * @return <code>AddTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.AddTeamMemberRequest
     * @see com.ifountain.opsgenie.client.model.team.AddTeamMemberResponse
     */
    public AddTeamMemberResponse addTeamMember(AddTeamMemberRequest addTeamMemberRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Removes a team member at OpsGenie.
     *
     * @param deleteTeamMemberRequest Object to construct request parameters.
     * @return <code>AddTeamResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.DeleteTeamMemberRequest
     * @see com.ifountain.opsgenie.client.model.team.DeleteTeamMemberResponse
     */
    public DeleteTeamMemberResponse deleteTeamMember(DeleteTeamMemberRequest deleteTeamMemberRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Adds a TeamRoutingRule at OpsGenie.
     *
     * @param addTeamRoutingRuleRequest Object to construct request parameters.
     * @return <code>AddTeamRoutingRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.AddTeamRoutingRuleRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.AddTeamRoutingRuleResponse
     */
    public AddTeamRoutingRuleResponse addTeamRoutingRule(AddTeamRoutingRuleRequest addTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates TeamRoutingRule at OpsGenie.
     *
     * @param updateTeamRoutingRuleRequest Object to construct request parameters.
     * @return <code>UpdateTeamRoutingRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.UpdateTeamRoutingRuleRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.UpdateTeamRoutingRuleResponse
     */
    public UpdateTeamRoutingRuleResponse updateTeamRoutingRule(UpdateTeamRoutingRuleRequest updateTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Change order of a TeamRoutingRule at OpsGenie.
     *
     * @param changeTeamRoutingRuleOrderRequest Object to construct request parameters.
     * @return <code>ChangeTeamRoutingRuleOrderResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.ChangeTeamRoutingRuleOrderRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.ChangeTeamRoutingRuleOrderResponse
     */
    public ChangeTeamRoutingRuleOrderResponse changeTeamRoutingRuleOrder(ChangeTeamRoutingRuleOrderRequest changeTeamRoutingRuleOrderRequest) throws IOException, OpsGenieClientException, ParseException;


    /**
     * Deletes a TeamRoutingRule at OpsGenie.
     *
     * @param deleteTeamRoutingRuleRequest Object to construct request parameters.
     * @return <code>DeleteTeamRoutingRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.DeleteTeamRoutingRuleRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.DeleteTeamRoutingRuleResponse
     */
    public DeleteTeamRoutingRuleResponse deleteTeamRoutingRule(DeleteTeamRoutingRuleRequest deleteTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Get TeamRoutingRule details
     *
     * @param getTeamRoutingRuleRequest Object to construct request parameters.
     * @return <code>GetTeamRoutingRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.GetTeamRoutingRuleRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.GetTeamRoutingRuleResponse
     */
    public GetTeamRoutingRuleResponse getTeamRoutingRule(GetTeamRoutingRuleRequest getTeamRoutingRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List TeamRoutingRules of customer
     *
     * @param listTeamRoutingRulesRequest Object to construct request parameters.
     * @return <code>ListTeamRoutingRulesResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.ListTeamRoutingRulesRequest
     * @see com.ifountain.opsgenie.client.model.team.routing_rule.ListTeamRoutingRulesResponse
     */
    public ListTeamRoutingRulesResponse listTeamRoutingRules(ListTeamRoutingRulesRequest listTeamRoutingRulesRequest) throws IOException, OpsGenieClientException, ParseException;

}
