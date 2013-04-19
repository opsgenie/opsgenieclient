package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for escalation related operations
 *
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface IEscalationOpsGenieClient {
    /**
     * Adds a escalation at OpsGenie.
     *
     * @param addEscalationRequest Object to construct request parameters.
     * @return <code>AddEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.escalation.AddEscalationRequest
     * @see com.ifountain.opsgenie.client.model.escalation.AddEscalationResponse
     */
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, OpsGenieClientException;

    /**
     * Updates escalation at OpsGenie.
     *
     * @param updateEscalationRequest Object to construct request parameters.
     * @return <code>UpdateEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.escalation.UpdateEscalationRequest
     * @see com.ifountain.opsgenie.client.model.escalation.UpdateEscalationResponse
     */
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, OpsGenieClientException;

    /**
     * Deletes a escalation at OpsGenie.
     *
     * @param deleteEscalationRequest Object to construct request parameters.
     * @return <code>DeleteEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.escalation.DeleteEscalationRequest
     * @see com.ifountain.opsgenie.client.model.escalation.DeleteEscalationResponse
     */
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, OpsGenieClientException;

    /**
     * Get escalation details
     *
     * @param getEscalationRequest Object to construct request parameters.
     * @return <code>GetEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.escalation.GetEscalationRequest
     * @see com.ifountain.opsgenie.client.model.escalation.GetEscalationResponse
     */
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List escalations of customer
     *
     * @param listEscalationRequest Object to construct request parameters.
     * @return <code>ListEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.escalation.ListEscalationRequest
     * @see com.ifountain.opsgenie.client.model.escalation.ListEscalationResponse
     */
    public ListEscalationResponse listEscalations(ListEscalationRequest listEscalationRequest) throws IOException, OpsGenieClientException, ParseException;
}
