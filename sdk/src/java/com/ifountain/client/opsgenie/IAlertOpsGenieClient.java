package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.InputStreamAttachRequest;
import com.ifountain.client.opsgenie.model.alert.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for alert related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IAlertOpsGenieClient {
    /**
     * Creates alerts at OpsGenie.
     *
     * @param createAlertRequest Object to construct request parameters.
     * @return <code>CreateAlertResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.CreateAlertRequest
     * @see com.ifountain.client.opsgenie.model.alert.CreateAlertResponse
     */
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, ClientException, ParseException;

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.CloseAlertRequest
     * @see com.ifountain.client.opsgenie.model.alert.CloseAlertResponse
     */
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws ClientException, IOException, ParseException;

    /**
     * Deletes alerts at OpsGenie.
     *
     * @param deleteAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.DeleteAlertRequest
     * @see com.ifountain.client.opsgenie.model.alert.DeleteAlertResponse
     */
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest deleteAlertRequest) throws ClientException, IOException, ParseException;

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertRequest
     * @see com.ifountain.client.opsgenie.model.alert.GetAlertResponse
     */
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws ClientException, IOException, ParseException;

    /**
     * Lists alerts from OpsGenie.
     *
     * @param listAlertsRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.client.opsgenie.model.alert.ListAlertsRequest
     * @see com.ifountain.client.opsgenie.model.alert.ListAlertsResponse
     */
    public ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws ClientException, IOException, ParseException;

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.AddNoteRequest
     * @see com.ifountain.client.opsgenie.model.alert.AddNoteResponse
     */
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws ClientException, IOException, ParseException;

    /**
     * Acknowledges alerts in OpsGenie.     *
     *
     * @param acknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.AcknowledgeRequest
     * @see com.ifountain.client.opsgenie.model.alert.AcknowledgeResponse
     */
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws ClientException, IOException, ParseException;

    /**
     * Renotifies recipients in OpsGenie.     *
     *
     * @param renotifyRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.RenotifyRequest
     * @see com.ifountain.client.opsgenie.model.alert.RenotifyResponse
     */
    public RenotifyResponse renotify(RenotifyRequest renotifyRequest) throws ClientException, IOException, ParseException;

    /**
     * Takes the ownership of an alert.
     *
     * @param takeOwnershipRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.TakeOwnershipRequest
     * @see com.ifountain.client.opsgenie.model.alert.TakeOwnershipResponse
     */
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws ClientException, IOException, ParseException;

    /**
     * Assigns the ownership of an alert to the specified user.
     *
     * @param assignRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.AssignRequest
     * @see com.ifountain.client.opsgenie.model.alert.AssignResponse
     */
    public AssignResponse assign(AssignRequest assignRequest) throws ClientException, IOException, ParseException;

    /**
     * Adds a new recipient to an alert.
     *
     * @param addRecipientRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.AddRecipientRequest
     * @see com.ifountain.client.opsgenie.model.alert.AddRecipientResponse
     */
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws ClientException, IOException, ParseException;

    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.FileAttachRequest
     * @see com.ifountain.client.opsgenie.model.alert.AttachResponse
     */
    public AttachResponse attach(FileAttachRequest attachRequest) throws ClientException, IOException, ParseException;

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.InputStreamAttachRequest
     * @see com.ifountain.client.opsgenie.model.alert.AttachResponse
     */
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws ClientException, IOException, ParseException;

    /**
     * Executes actions on alerts in OpsGenie.
     *
     * @param executeAlertActionRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.alert.ExecuteAlertActionRequest
     * @see com.ifountain.client.opsgenie.model.alert.ExecuteAlertActionResponse
     */
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws ClientException, IOException, ParseException;

    /**
     * Lists alert logs
     *
     * @param listAlertLogsRequest  Object to construct request parameters.
     * @return Object containing alert logs
     * @see ListAlertLogsRequest
     * @see ListAlertLogsResponse
     */
    public ListAlertLogsResponse listAlertLogs(ListAlertLogsRequest listAlertLogsRequest) throws ParseException, ClientException, IOException;

    /**
     * Lists alert recipients
     *
     * @param listAlertRecipientsRequest  Object to construct request parameters.
     * @return Object containing alert recipients
     * @see ListAlertRecipientsRequest
     * @see ListAlertRecipientsResponse
     */
    public ListAlertRecipientsResponse listAlertRecipients(ListAlertRecipientsRequest listAlertRecipientsRequest) throws ParseException, ClientException, IOException;
}
