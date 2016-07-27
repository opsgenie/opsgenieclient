package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.notificationRule.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for notificationRule related operations
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface INotificationRuleOpsGenieClient {
    /**
     * Adds a notificationRule at OpsGenie.
     *
     * @param addNotificationRuleRequest Object to construct request parameters.
     * @return <code>AddNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleResponse
     */
    public AddNotificationRuleResponse addNotificationRule(AddNotificationRuleRequest addNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates notificationRule at OpsGenie.
     *
     * @param updateNotificationRuleRequest Object to construct request parameters.
     * @return <code>UpdateNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleResponse
     */
    public UpdateNotificationRuleResponse updateNotificationRule(UpdateNotificationRuleRequest updateNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a notificationRule at OpsGenie.
     *
     * @param deleteNotificationRuleRequest Object to construct request parameters.
     * @return <code>DeleteNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleResponse
     */
    public DeleteNotificationRuleResponse deleteNotificationRule(DeleteNotificationRuleRequest deleteNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Enable notificationRule
     *
     * @param enableNotificationRuleRequest  Object to construct request parameters.
     * @return <code>EnableNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleResponse
     */
    public EnableNotificationRuleResponse enableNotificationRule(EnableNotificationRuleRequest enableNotificationRuleRequest) throws ParseException, OpsGenieClientException, IOException;
  
    /**
     * Disable notificationRule
     *
     * @param disableNotificationRuleRequest  Object to construct request parameters.
     * @return <code>DisableNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleResponse
     */
    public DisableNotificationRuleResponse disableNotificationRule(DisableNotificationRuleRequest disableNotificationRuleRequest) throws ParseException, OpsGenieClientException, IOException;
  
    
    /**
     * Change a notificationRule at OpsGenie.
     *
     * @param changeNotificationRuleOrderRequest Object to construct request parameters.
     * @return <code>ChangeNotificationRuleOrderResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.ChangeNotificationRuleOrderRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.ChangeNotificationRuleOrderResponse
     */
    public ChangeNotificationRuleOrderResponse changeNotificationRuleOrder(ChangeNotificationRuleOrderRequest changeNotificationRuleOrderRequest) throws IOException, OpsGenieClientException, ParseException;
    
    /**
     * Repeat a notificationRule at OpsGenie.
     *
     * @param repeatNotificationRuleRequest Object to construct request parameters.
     * @return <code>RepeatNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.RepeatNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.RepeatNotificationRuleResponse
     */
    public RepeatNotificationRuleResponse repeatNotificationRule(RepeatNotificationRuleRequest repeatNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;
    /**
     * Get notificationRule details
     *
     * @param getNotificationRuleRequest Object to construct request parameters.
     * @return <code>GetNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.GetNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.GetNotificationRuleResponse
     */
    public GetNotificationRuleResponse getNotificationRule(GetNotificationRuleRequest getNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List notificationRule of customer
     *
     * @param listNotificationRuleRequest Object to construct request parameters.
     * @return <code>ListNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.ListNotificationRulesRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.ListNotificationRulesResponse
     */
    public ListNotificationRulesResponse listNotificationRule(ListNotificationRulesRequest listNotificationRulesRequest) throws IOException, OpsGenieClientException, ParseException;

    
    /**
     * Adds a notificationRuleStep  at OpsGenie.
     *
     * @param addNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>AddNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleStepResponse
     */
    public AddNotificationRuleStepResponse addNotificationRuleStep(AddNotificationRuleStepRequest addNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates notificationRuleStep at OpsGenie.
     *
     * @param updateNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>UpdateNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleStepResponse
     */
    public UpdateNotificationRuleStepResponse updateNotificationRuleStep(UpdateNotificationRuleStepRequest updateNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a notificationRuleStep  at OpsGenie.
     *
     * @param deleteNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>DeleteNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleStepResponse
     */
    public DeleteNotificationRuleStepResponse deleteNotificationRuleStep(DeleteNotificationRuleStepRequest deleteNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Enable notificationRuleStep 
     *
     * @param enableNotificationRuleStepRequest  Object to construct request parameters.
     * @return <code>EnableNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleStepResponse
     */
    public EnableNotificationRuleStepResponse enableNotificationRuleStep(EnableNotificationRuleStepRequest enableNotificationRuleStepRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Disable notificationRuleStep 
     *
     * @param disableNotificationRuleStepRequest  Object to construct request parameters.
     * @return <code>DisableNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleStepResponse
     */
    public DisableNotificationRuleStepResponse disableNotificationRuleStep(DisableNotificationRuleStepRequest disableNotificationRuleStepRequest) throws ParseException, OpsGenieClientException, IOException;

}
