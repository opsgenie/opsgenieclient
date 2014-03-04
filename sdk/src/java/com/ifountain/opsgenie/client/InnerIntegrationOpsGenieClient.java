package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Integration Client
 */
public class InnerIntegrationOpsGenieClient implements IIntegrationOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new integration client to invoke service methods on OpsGenie for integrations using the specified client and root URI.
     */
    public InnerIntegrationOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @see com.ifountain.opsgenie.client.IIntegrationOpsGenieClient#enableIntegration(com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest)
     */
    @Override
    public EnableIntegrationResponse enableIntegration(EnableIntegrationRequest enableIntegrationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (EnableIntegrationResponse) httpClient.doPostRequest(enableIntegrationRequest);
    }

}
