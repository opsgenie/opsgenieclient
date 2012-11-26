package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.*;

import java.io.IOException;

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
     * @see com.ifountain.opsgenie.client.model.CreateAlertRequest
     * @see com.ifountain.opsgenie.client.model.CreateAlertResponse
     */
    public CreateAlertResponse createAlert(CreateAlertRequest createAlertRequest) throws IOException, OpsGenieClientException;

    /**
     * Closes alerts at OpsGenie.
     *
     * @param closeAlertRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.CloseAlertRequest
     * @see com.ifountain.opsgenie.client.model.CloseAlertResponse
     */
    public CloseAlertResponse closeAlert(CloseAlertRequest closeAlertRequest) throws OpsGenieClientException, IOException;

    /**
     * Retrieves specified alert details from OpsGenie.
     *
     * @param getAlertRequest Object to construct request parameters.
     * @return Object containing retreived alert information.
     * @see com.ifountain.opsgenie.client.model.GetAlertRequest
     * @see com.ifountain.opsgenie.client.model.GetAlertResponse
     */
    public GetAlertResponse getAlert(GetAlertRequest getAlertRequest) throws OpsGenieClientException, IOException;

    /**
     * Add notes to alerts in OpsGenie.
     *
     * @param addNoteRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.AddNoteRequest
     * @see com.ifountain.opsgenie.client.model.AddNoteResponse
     */
    public AddNoteResponse addNote(AddNoteRequest addNoteRequest) throws OpsGenieClientException, IOException;

    /**
     * Acknowledges alerts in OpsGenie.
     *
     * @param acknowledgeRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.AcknowledgeRequest
     * @see com.ifountain.opsgenie.client.model.AcknowledgeResponse
     */
    public AcknowledgeResponse acknowledge(AcknowledgeRequest acknowledgeRequest) throws OpsGenieClientException, IOException;

    /**
     * Takes the ownership of an alert.
     *
     * @param takeOwnershipRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.TakeOwnershipRequest
     * @see com.ifountain.opsgenie.client.model.TakeOwnershipResponse
     */
    public TakeOwnershipResponse takeOwnership(TakeOwnershipRequest takeOwnershipRequest) throws OpsGenieClientException, IOException;

    /**
     * Assigns the ownership of an alert to the specified user.
     *
     * @param assignRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.AssignRequest
     * @see com.ifountain.opsgenie.client.model.AssignResponse
     */
    public AssignResponse assign(AssignRequest assignRequest) throws OpsGenieClientException, IOException;

    /**
     * Attaches files to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.FileAttachRequest
     * @see com.ifountain.opsgenie.client.model.AttachResponse
     */
    public AttachResponse attach(FileAttachRequest attachRequest) throws OpsGenieClientException, IOException;

    /**
     * Attaches ${@link java.io.InputStream} content to the alerts in OpsGenie.
     *
     * @param attachRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.InputStreamAttachRequest
     * @see com.ifountain.opsgenie.client.model.AttachResponse
     */
    public AttachResponse attach(InputStreamAttachRequest attachRequest) throws OpsGenieClientException, IOException;

    /**
     * Executes actions on alerts in OpsGenie.
     *
     * @param executeAlertActionRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.ExecuteAlertActionRequest
     * @see com.ifountain.opsgenie.client.model.ExecuteAlertActionResponse
     */
    public ExecuteAlertActionResponse executeAlertAction(ExecuteAlertActionRequest executeAlertActionRequest) throws OpsGenieClientException, IOException;

    /**
     * Sends heartbeat messages to OpsGenie. If heartbeat monitoring is enabled and OpsGenie does not get a heartbeat message within 10 minutes,
     * OpsGenie creates an alert to notify the specified people.
     *
     * @param heartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.HeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.HeartbeatResponse
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException;

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
