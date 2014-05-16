package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.integration.EnableIntegrationRequest;
import com.ifountain.client.opsgenie.model.integration.EnableIntegrationResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for integration related operations
 *
 * @author Halit Okumus
 * @version 04.03.2014 09:40
 * @see OpsGenieClient
 */
public interface IIntegrationOpsGenieClient {
    /**
     * Enable or disable integration
     *
     * @param enableIntegrationRequest  Object to construct request parameters.
     * @see com.ifountain.client.opsgenie.model.integration.EnableIntegrationRequest
     * @see com.ifountain.client.opsgenie.model.integration.EnableIntegrationResponse
     */
    public EnableIntegrationResponse enableIntegration(EnableIntegrationRequest enableIntegrationRequest) throws ParseException, ClientException, IOException;
}
