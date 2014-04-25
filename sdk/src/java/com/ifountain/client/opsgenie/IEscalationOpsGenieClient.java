package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for escalation related operations
 *
 * @see OpsGenieClient
 */
public interface IEscalationOpsGenieClient {
    /**
     * Adds a escalation at OpsGenie.
     *
     * @param addEscalationRequest Object to construct request parameters.
     * @return <code>AddEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.escalation.AddEscalationRequest
     * @see com.ifountain.client.opsgenie.model.escalation.AddEscalationResponse
     */
    public AddEscalationResponse addEscalation(AddEscalationRequest addEscalationRequest) throws IOException, ClientException, ParseException;

    /**
     * Updates escalation at OpsGenie.
     *
     * @param updateEscalationRequest Object to construct request parameters.
     * @return <code>UpdateEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.escalation.UpdateEscalationRequest
     * @see com.ifountain.client.opsgenie.model.escalation.UpdateEscalationResponse
     */
    public UpdateEscalationResponse updateEscalation(UpdateEscalationRequest updateEscalationRequest) throws IOException, ClientException, ParseException;

    /**
     * Deletes a escalation at OpsGenie.
     *
     * @param deleteEscalationRequest Object to construct request parameters.
     * @return <code>DeleteEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.escalation.DeleteEscalationRequest
     * @see com.ifountain.client.opsgenie.model.escalation.DeleteEscalationResponse
     */
    public DeleteEscalationResponse deleteEscalation(DeleteEscalationRequest deleteEscalationRequest) throws IOException, ClientException, ParseException;

    /**
     * Get escalation details
     *
     * @param getEscalationRequest Object to construct request parameters.
     * @return <code>GetEscalationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.escalation.GetEscalationRequest
     * @see com.ifountain.client.opsgenie.model.escalation.GetEscalationResponse
     */
    public GetEscalationResponse getEscalation(GetEscalationRequest getEscalationRequest) throws IOException, ClientException, ParseException;

    /**
     * List escalations of customer
     *
     * @param listEscalationsRequest Object to construct request parameters.
     * @return <code>ListEscalationsResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.escalation.ListEscalationsRequest
     * @see com.ifountain.client.opsgenie.model.escalation.ListEscalationsResponse
     */
    public ListEscalationsResponse listEscalations(ListEscalationsRequest listEscalationsRequest) throws IOException, ClientException, ParseException;
}
