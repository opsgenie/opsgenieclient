package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.notification_rule.*;

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
     * @see com.ifountain.opsgenie.client.model.notification_rule.AddNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.AddNotificationRuleResponse
     */
    public AddNotificationRuleResponse addNotificationRule(AddNotificationRuleRequest addNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates notificationRule at OpsGenie.
     *
     * @param updateNotificationRuleRequest Object to construct request parameters.
     * @return <code>UpdateNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.UpdateNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.UpdateNotificationRuleResponse
     */
    public UpdateNotificationRuleResponse updateNotificationRule(UpdateNotificationRuleRequest updateNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a notificationRule at OpsGenie.
     *
     * @param deleteNotificationRuleRequest Object to construct request parameters.
     * @return <code>DeleteNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.DeleteNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.DeleteNotificationRuleResponse
     */
    public DeleteNotificationRuleResponse deleteNotificationRule(DeleteNotificationRuleRequest deleteNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Enable notificationRule
     *
     * @param enableNotificationRuleRequest  Object to construct request parameters.
     * @return <code>EnableNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.EnableNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.EnableNotificationRuleResponse
     */
    public EnableNotificationRuleResponse enableNotificationRule(EnableNotificationRuleRequest enableNotificationRuleRequest) throws ParseException, OpsGenieClientException, IOException;
  
    /**
     * Disable notificationRule
     *
     * @param disableNotificationRuleRequest  Object to construct request parameters.
     * @return <code>DisableNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleResponse
     */
    public DisableNotificationRuleResponse disableNotificationRule(DisableNotificationRuleRequest disableNotificationRuleRequest) throws ParseException, OpsGenieClientException, IOException;
  
    
    /**
     * Change a notificationRule at OpsGenie.
     *
     * @param changeNotificationRuleOrderRequest Object to construct request parameters.
     * @return <code>ChangeNotificationRuleOrderResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.ChangeNotificationRuleOrderRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.ChangeNotificationRuleOrderResponse
     */
    public ChangeNotificationRuleOrderResponse changeNotificationRuleOrder(ChangeNotificationRuleOrderRequest changeNotificationRuleOrderRequest) throws IOException, OpsGenieClientException, ParseException;
    
    /**
     * Repeat a notificationRule at OpsGenie.
     *
     * @param repeatNotificationRuleRequest Object to construct request parameters.
     * @return <code>RepeatNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.RepeatNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.RepeatNotificationRuleResponse
     */
    public RepeatNotificationRuleResponse repeatNotificationRule(RepeatNotificationRuleRequest repeatNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;
    /**
     * Get notificationRule details
     *
     * @param getNotificationRuleRequest Object to construct request parameters.
     * @return <code>GetNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.GetNotificationRuleRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.GetNotificationRuleResponse
     */
    public GetNotificationRuleResponse getNotificationRule(GetNotificationRuleRequest getNotificationRuleRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List notificationRule of customer
     *
     * @param listNotificationRuleRequest Object to construct request parameters.
     * @return <code>ListNotificationRuleResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.ListNotificationRulesRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.ListNotificationRulesResponse
     */
    public ListNotificationRulesResponse listNotificationRule(ListNotificationRulesRequest listNotificationRulesRequest) throws IOException, OpsGenieClientException, ParseException;

    
    /**
     * Adds a notificationRuleStep  at OpsGenie.
     *
     * @param addNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>AddNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.AddNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.AddNotificationRuleStepResponse
     */
    public AddNotificationRuleStepResponse addNotificationRuleStep(AddNotificationRuleStepRequest addNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates notificationRuleStep at OpsGenie.
     *
     * @param updateNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>UpdateNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.UpdateNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.UpdateNotificationRuleStepResponse
     */
    public UpdateNotificationRuleStepResponse updateNotificationRuleStep(UpdateNotificationRuleStepRequest updateNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a notificationRuleStep  at OpsGenie.
     *
     * @param deleteNotificationRuleStepRequest Object to construct request parameters.
     * @return <code>DeleteNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.DeleteNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.DeleteNotificationRuleStepResponse
     */
    public DeleteNotificationRuleStepResponse deleteNotificationRuleStep(DeleteNotificationRuleStepRequest deleteNotificationRuleStepRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Enable notificationRuleStep 
     *
     * @param enableNotificationRuleStepRequest  Object to construct request parameters.
     * @return <code>EnableNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.EnableNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.EnableNotificationRuleStepResponse
     */
    public EnableNotificationRuleStepResponse enableNotificationRuleStep(EnableNotificationRuleStepRequest enableNotificationRuleStepRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Disable notificationRuleStep 
     *
     * @param disableNotificationRuleStepRequest  Object to construct request parameters.
     * @return <code>DisableNotificationRuleStepResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleStepRequest
     * @see com.ifountain.opsgenie.client.model.notification_rule.DisableNotificationRuleStepResponse
     */
    public DisableNotificationRuleStepResponse disableNotificationRuleStep(DisableNotificationRuleStepRequest disableNotificationRuleStepRequest) throws ParseException, OpsGenieClientException, IOException;

}
