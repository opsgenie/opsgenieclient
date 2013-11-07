package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.automation.EnableAutomationRequest;
import com.ifountain.opsgenie.client.model.automation.EnableAutomationResponse;
import com.ifountain.opsgenie.client.model.escalation.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Automation Client
 */
public class InnerAutomationOpsGenieClient implements IAutomationOpsGenieClient{
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerAutomationOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @see com.ifountain.opsgenie.client.IAutomationOpsGenieClient#enableAutomation(com.ifountain.opsgenie.client.model.automation.EnableAutomationRequest)
     */
    @Override
    public EnableAutomationResponse enableAutomation(EnableAutomationRequest enableAutomationRequest) throws IOException, OpsGenieClientException, ParseException {
        return (EnableAutomationResponse) httpClient.doPostRequest(enableAutomationRequest);
    }

}
