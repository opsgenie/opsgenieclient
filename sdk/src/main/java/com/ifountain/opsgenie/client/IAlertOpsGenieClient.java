package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.InputStreamAttachRequest;
import com.ifountain.opsgenie.client.model.alert.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for alert related operations
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface IAlertOpsGenieClient {
    /**
     * Creates alerts at OpsGenie.
     *
     * @param createAlertRequest Object to construct request parameters.
     * @return <code>CreateAlertResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertResponse
     */
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertResponse
     */
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Deletes alerts at OpsGenie.
     *
     * @param deleteAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertResponse
     */
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest deleteAlertRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertResponse
     */
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Lists alerts from OpsGenie.
     *
     * @param listAlertsRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.alert.ListAlertsRequest
     * @see com.ifountain.opsgenie.client.model.alert.ListAlertsResponse
     */
    public ListAlertsResponse listAlerts(ListAlertsRequest listAlertsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Count alerts from OpsGenie.
     *
     * @param countAlertsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CountAlertsRequest
     * @see com.ifountain.opsgenie.client.model.alert.CountAlertsRequest
     */
    public CountAlertsResponse countAlerts(CountAlertsRequest countAlertsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteResponse
     */
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Acknowledges alerts in OpsGenie.
     *
     *
     * @param acknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeRequest
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse
     */
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * UnAcknowledges alerts in OpsGenie.
     *
     *
     * @param unAcknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.UnAcknowledgeRequest
     * @see com.ifountain.opsgenie.client.model.alert.UnAcknowledgeResponse
     */
    public UnAcknowledgeResponse unAcknowledge(UnAcknowledgeRequest unAcknowledgeRequest) throws OpsGenieClientException, IOException, ParseException;


    /**
     * Renotifies recipients in OpsGenie.
     *
     *
     * @param renotifyRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.RenotifyRequest
     * @see com.ifountain.opsgenie.client.model.alert.RenotifyResponse
     */
    public RenotifyResponse renotify(RenotifyRequest renotifyRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Takes the ownership of an alert.
     *
     * @param takeOwnershipRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipRequest
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipResponse
     */
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Assigns the ownership of an alert to the specified user.
     *
     * @param assignRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AssignRequest
     * @see com.ifountain.opsgenie.client.model.alert.AssignResponse
     */
    public AssignResponse assign(AssignRequest assignRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Adds a new team to an alert.
     *
     * @param addAlertTeamRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddAlertTeamRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddAlertTeamResponse
     */
    public AddAlertTeamResponse addTeam(AddAlertTeamRequest addAlertTeamRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Adds a new recipient to an alert.
     *
     * @param addRecipientRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientResponse
     */
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws OpsGenieClientException, IOException, ParseException;


    /**
     * Adds tags to an alert.
     *
     * @param addTagsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddTagsRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddTagsResponse
     */
    public AddTagsResponse addTags(AddTagsRequest addTagsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Remove tags from an alert.
     *
     * @param removeTagsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.RemoveTagsRequest
     * @see com.ifountain.opsgenie.client.model.alert.RemoveTagsResponse
     */
    public RemoveTagsResponse removeTags(RemoveTagsRequest removeTagsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.FileAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.InputStreamAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Executes actions on alerts in OpsGenie.
     *
     * @param executeAlertActionRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionRequest
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionResponse
     */
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Lists alert logs
     *
     * @param listAlertLogsRequest  Object to construct request parameters.
     * @return Object containing alert logs
     * @see ListAlertLogsRequest
     * @see ListAlertLogsResponse
     */
    public ListAlertLogsResponse listAlertLogs(ListAlertLogsRequest listAlertLogsRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Lists alert recipients
     *
     * @param listAlertRecipientsRequest  Object to construct request parameters.
     * @return Object containing alert recipients
     * @see ListAlertRecipientsRequest
     * @see ListAlertRecipientsResponse
     */
    public ListAlertRecipientsResponse listAlertRecipients(ListAlertRecipientsRequest listAlertRecipientsRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Lists alert notes
     *
     * @param listAlertNotesRequest  Object to construct request parameters.
     * @return Object containing alert logs
     * @see ListAlertNotesRequest
     * @see ListAlertNotesResponse
     */
    public ListAlertNotesResponse listAlertNotes(ListAlertNotesRequest listAlertNotesRequest) throws ParseException, OpsGenieClientException, IOException;


    /**
     * Adds details to an alert.
     *
     * @param addDetailsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddDetailsRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddDetailsResponse
     */
    public AddDetailsResponse addDetails(AddDetailsRequest addDetailsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Removes details to an alert.
     *
     * @param removeDetailsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.RemoveDetailsRequest
     * @see com.ifountain.opsgenie.client.model.alert.RemoveDetailsResponse
     */
    public RemoveDetailsResponse removeDetails(RemoveDetailsRequest removeDetailsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * snooze an alert.
     *
     * @param snoozeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.SnoozeRequest
     * @see com.ifountain.opsgenie.client.model.alert.SnoozeResponse
     */
    public SnoozeResponse snooze(SnoozeRequest snoozeRequest) throws OpsGenieClientException, IOException, ParseException;


}
