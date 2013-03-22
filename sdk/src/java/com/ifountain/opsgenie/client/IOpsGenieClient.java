package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.*;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;
import com.ifountain.opsgenie.client.model.user.forward.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IOpsGenieClient {
    /**
     * Creates alerts at OpsGenie.
     *
     * @param createAlertRequest Object to construct request parameters.
     * @return <code>CreateAlertResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CreateAlertResponse
     */
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, OpsGenieClientException;

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.CloseAlertResponse
     */
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException;

    /**
     * Deletes alerts at OpsGenie.
     *
     * @param deleteAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.DeleteAlertResponse
     */
    public DeleteAlertResponse deleteAlert(DeleteAlertRequest deleteAlertRequest) throws OpsGenieClientException, IOException;

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertRequest
     * @see com.ifountain.opsgenie.client.model.alert.GetAlertResponse
     */
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws OpsGenieClientException, IOException;

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddNoteResponse
     */
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException;

    /**
     * Acknowledges alerts in OpsGenie.
     *
     * @param acknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeRequest
     * @see com.ifountain.opsgenie.client.model.alert.AcknowledgeResponse
     */
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException;

    /**
     * Takes the ownership of an alert.
     *
     * @param takeOwnershipRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipRequest
     * @see com.ifountain.opsgenie.client.model.alert.TakeOwnershipResponse
     */
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws OpsGenieClientException, IOException;

    /**
     * Assigns the ownership of an alert to the specified user.
     *
     * @param assignRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AssignRequest
     * @see com.ifountain.opsgenie.client.model.alert.AssignResponse
     */
    public AssignResponse assign(AssignRequest assignRequest) throws OpsGenieClientException, IOException;

    /**
     * Adds a new recipient to an alert.
     *
     * @param addRecipientRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientRequest
     * @see com.ifountain.opsgenie.client.model.alert.AddRecipientResponse
     */
    public AddRecipientResponse addRecipient(AddRecipientRequest addRecipientRequest) throws OpsGenieClientException, IOException;

    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.FileAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException;

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.InputStreamAttachRequest
     * @see com.ifountain.opsgenie.client.model.alert.AttachResponse
     */
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException;

    /**
     * Executes actions on alerts in OpsGenie.
     *
     * @param executeAlertActionRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionRequest
     * @see com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionResponse
     */
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException;

    /**
     * Sends heartbeat messages to OpsGenie. If heartbeat monitoring is enabled and OpsGenie does not get a heartbeat message within 10 minutes,
     * OpsGenie creates an alert to notify the specified people.
     *
     * @param heartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatResponse
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException;

    /**
     * Adds user notification forwarding setting. All of notifications will be sent to forwarded user during configured
     * time settings.
     *
     * @param addForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.AddForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.AddForwardingResponse
     */
    public AddForwardingResponse addForwarding(AddForwardingRequest addForwardingRequest) throws IOException, OpsGenieClientException;


    /**
     * Updates user notification forwarding setting.
     * time settings.
     *
     * @param updateForwardingRequest  Object to construct request parameters
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.UpdateForwardingResponse
     */
    public UpdateForwardingResponse updateForwarding(UpdateForwardingRequest updateForwardingRequest) throws IOException, OpsGenieClientException;

    /**
     * @param deleteForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.DeleteForwardingResponse
     */
    public DeleteForwardingResponse deleteForwarding(DeleteForwardingRequest deleteForwardingRequest) throws IOException, OpsGenieClientException;

    /**
     * @param getForwardingRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.GetForwardingRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.GetForwardingResponse
     */
    public GetForwardingResponse getForwarding(GetForwardingRequest getForwardingRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * @param listForwardingsRequest Object to construct request parameters
     * @return  Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.user.forward.ListForwardingsRequest
     * @see com.ifountain.opsgenie.client.model.user.forward.ListForwardingsResponse
     */
    public ListForwardingsResponse listForwardings(ListForwardingsRequest listForwardingsRequest) throws IOException, OpsGenieClientException, ParseException;


    /**
     * Set root endpoint uri that the client uses to send http requests. Default is https://api.opsgenie.com. Mostly used for testing.
     *
     * @param rootUri Uri to set.
     */
    public void setRootUri(String rootUri);

    /**
     * Closes client
     */
    public void close();
}
