package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.InputStreamAttachRequest;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;
import com.ifountain.opsgenie.client.model.user.*;
import com.ifountain.opsgenie.client.model.user.forward.*;

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
    public BaseResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException, ParseException;

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
     * Adds a new recipient to an alert.
     *
     * @param addRecipientRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientResponse
     */
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws OpsGenieClientException, IOException, ParseException;

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
}
