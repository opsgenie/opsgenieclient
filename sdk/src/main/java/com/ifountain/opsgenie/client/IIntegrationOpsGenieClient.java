package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for integration related operations
 *
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IIntegrationOpsGenieClient {
    /**
     * Enable or disable integration
     *
     * @param enableIntegrationRequest  Object to construct request parameters.
     * @see com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest
     * @see com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse
     */
    public EnableIntegrationResponse enableIntegration(EnableIntegrationRequest enableIntegrationRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * Send data to OpsGenie webhook for processing
     *
     * @param sendToIntegrationRequest  Object to construct request parameters.
     * @see com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest
     * @see com.ifountain.opsgenie.client.model.integration.SendToIntegrationResponse
     */
    public SendToIntegrationResponse sendToIntegration(SendToIntegrationRequest sendToIntegrationRequest) throws ParseException, OpsGenieClientException, IOException, URISyntaxException;
}
