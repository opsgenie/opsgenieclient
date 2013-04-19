package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;

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
