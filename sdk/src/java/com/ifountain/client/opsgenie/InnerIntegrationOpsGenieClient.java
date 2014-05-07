package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.JsonHttpClient;
import com.ifountain.client.opsgenie.model.integration.EnableIntegrationRequest;
import com.ifountain.client.opsgenie.model.integration.EnableIntegrationResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Integration Client
 */
public class InnerIntegrationOpsGenieClient implements IIntegrationOpsGenieClient{
    private JsonHttpClient httpClient;
    /**
     * Constructs a new integration client to invoke service methods on OpsGenie for integrations using the specified client and root URI.
     */
    public InnerIntegrationOpsGenieClient(JsonHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @see IIntegrationOpsGenieClient#enableIntegration(com.ifountain.client.opsgenie.model.integration.EnableIntegrationRequest)
     */
    @Override
    public EnableIntegrationResponse enableIntegration(EnableIntegrationRequest enableIntegrationRequest) throws IOException, ClientException, ParseException {
        return (EnableIntegrationResponse) httpClient.doPostRequest(enableIntegrationRequest);
    }

}
