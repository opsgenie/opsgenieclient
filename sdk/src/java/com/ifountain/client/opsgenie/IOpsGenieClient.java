package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.model.IClient;
import com.ifountain.client.opsgenie.model.customer.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie
 *
 * @author Sezgin Kucukkaraaslan
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IOpsGenieClient extends IClient {
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
     * Opsgenie Alert Policy Client
     * @see IAlertPolicyOpsGenieClient
     * @return IAlertPolicyOpsGenieClient
     */
    IAlertPolicyOpsGenieClient alertPolicy();

    /**
     * Opsgenie Alert Client
     * @see IAlertOpsGenieClient
     * @return IAlertOpsGenieClient
     */
    IAlertOpsGenieClient alert();

    /**
     * Opsgenie Integration Client
     * @see IIntegrationOpsGenieClient
     * @return IIntegrationOpsGenieClient
     */
    IIntegrationOpsGenieClient integration();

    /**
     * Sends heartbeat messages to OpsGenie. If heartbeat monitoring is enabled and OpsGenie does not get a heartbeat message within 10 minutes,
     * OpsGenie creates an alert to notify the specified people.
     *
     * @param heartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.HeartbeatRequest
     * @see com.ifountain.client.opsgenie.model.customer.HeartbeatResponse
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) throws ClientException, IOException, ParseException;

    /**
     * Deletes heartbeat monitor on OpsGenie. If heartbeat monitor is deleted for specified source, OpsGenie will not create alert for expired heartbeat.
     *
     * @param deleteHeartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.DeleteHeartbeatRequest
     * @see com.ifountain.client.opsgenie.model.customer.DeleteHeartbeatResponse
     */
    public DeleteHeartbeatResponse deleteHeartbeat(DeleteHeartbeatRequest deleteHeartbeatRequest) throws ClientException, IOException, ParseException;

    /**
     * Get heartbeat monitor details on OpsGenie.
     *
     * @param getHeartbeatRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.GetHeartbeatRequest
     * @see com.ifountain.client.opsgenie.model.customer.GetHeartbeatResponse
     */
    public GetHeartbeatResponse getHeartbeat(GetHeartbeatRequest getHeartbeatRequest) throws ClientException, IOException, ParseException;
    
    /**
     * List all heartbeat monitor details on OpsGenie.
     *
     * @param listHeartbeatsRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.ListHeartbeatsRequest
     * @see com.ifountain.client.opsgenie.model.customer.ListHeartbeatsResponse
     */
    public ListHeartbeatsResponse listHeartbeats(ListHeartbeatsRequest listHeartbeatsRequest) throws ClientException, IOException, ParseException;

    /**
     * Sets heartbeat configuration
     *
     * @param setHeartbeatConfigRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.SetHeartbeatConfigRequest
     * @see com.ifountain.client.opsgenie.model.customer.SetHeartbeatConfigResponse
     */
    public SetHeartbeatConfigResponse setHeartbeatConfig(SetHeartbeatConfigRequest setHeartbeatConfigRequest) throws ClientException, IOException, ParseException;

    /**
     * Gets heartbeat configuration details
     *
     * @param getHeartbeatConfigRequest Object to construct request parameters.
     * @return Object containing OpsGenie response information.
     * @see com.ifountain.client.opsgenie.model.customer.GetHeartbeatConfigRequest
     * @see com.ifountain.client.opsgenie.model.customer.GetHeartbeatConfigResponse
     */
    public GetHeartbeatConfigResponse getHeartbeatConfig(GetHeartbeatConfigRequest getHeartbeatConfigRequest) throws ClientException, IOException, ParseException;
}
