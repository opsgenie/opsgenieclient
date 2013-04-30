package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.customer.*;

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
     * Opsgenie User Client
     * @see IUserOpsGenieClient
     * @return IUserOpsGenieClient
     */
    IUserOpsGenieClient user();

    /**
     * Opsgenie Group Client
     * @see IGroupOpsGenieClient
     * @return IGroupOpsGenieClient
     */
    IGroupOpsGenieClient group();

    /**
     * Opsgenie Escalation Client
     * @see IGroupOpsGenieClient
     * @return IGroupOpsGenieClient
     */
    IEscalationOpsGenieClient escalation();

    /**
     * Opsgenie Schedule Client
     * @see IScheduleOpsGenieClient
     * @return IScheduleOpsGenieClient
     */
    IScheduleOpsGenieClient schedule();

    /**
     * Opsgenie Alert Client
     * @see IAlertOpsGenieClient
     * @return IAlertOpsGenieClient
     */
    IAlertOpsGenieClient alert();

    /**
     * Sends heartbeat messages to OpsGenie. If heartbeat monitoring is enabled and OpsGenie does not get a heartbeat message within 10 minutes,
     * OpsGenie creates an alert to notify the specified people.
     *
     * @param heartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.customer.HeartbeatResponse
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Deletes heartbeat monitor on OpsGenie. If heartbeat monitor is deleted for specified source, OpsGenie will not create alert for expired heartbeat.
     *
     * @param deleteHeartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.DeleteHeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.customer.DeleteHeartbeatResponse
     */
    public DeleteHeartbeatResponse deleteHeartbeat(DeleteHeartbeatRequest deleteHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Get heartbeat monitor details on OpsGenie.
     *
     * @param getHeartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest
     * @see com.ifountain.opsgenie.client.model.customer.GetHeartbeatResponse
     */
    public GetHeartbeatResponse getHeartbeat(GetHeartbeatRequest getHeartbeatRequest) throws OpsGenieClientException, IOException, ParseException;
    
    /**
     * List all heartbeat monitor details on OpsGenie.
     *
     * @param listHeartbeatsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.ListHeartbeatsRequest
     * @see com.ifountain.opsgenie.client.model.customer.ListHeartbeatsResponse
     */
    public ListHeartbeatsResponse listHeartbeats(ListHeartbeatsRequest listHeartbeatsRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Sets heartbeat configuration
     *
     * @param setHeartbeatConfigRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.SetHeartbeatConfigRequest
     * @see com.ifountain.opsgenie.client.model.customer.SetHeartbeatConfigResponse
     */
    public SetHeartbeatConfigResponse setHeartbeatConfig(SetHeartbeatConfigRequest setHeartbeatConfigRequest) throws OpsGenieClientException, IOException, ParseException;

    /**
     * Gets heartbeat configuration details
     *
     * @param getHeartbeatConfigRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.customer.GetHeartbeatConfigRequest
     * @see com.ifountain.opsgenie.client.model.customer.GetHeartbeatConfigResponse
     */
    public GetHeartbeatConfigResponse getHeartbeatConfig(GetHeartbeatConfigRequest getHeartbeatConfigRequest) throws OpsGenieClientException, IOException, ParseException;
    
    

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
