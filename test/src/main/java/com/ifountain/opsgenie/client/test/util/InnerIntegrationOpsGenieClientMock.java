package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IIntegrationOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest;
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationResponse;

import java.io.IOException;
import java.text.ParseException;

public class InnerIntegrationOpsGenieClientMock implements IIntegrationOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private EnableIntegrationResponse enableIntegrationResponse;
    private SendToIntegrationResponse sendToIntegrationResponse;

    public InnerIntegrationOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public EnableIntegrationResponse enableIntegration(EnableIntegrationRequest enableIntegrationRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(enableIntegrationRequest);
        return enableIntegrationResponse;
    }

    @Override
    public SendToIntegrationResponse sendToIntegration(SendToIntegrationRequest sendToIntegrationRequest) throws ParseException, OpsGenieClientException, IOException {
        requestProcessor.processRequest(sendToIntegrationRequest);
        return sendToIntegrationResponse;
    }

    public void setEnableIntegrationResponse(EnableIntegrationResponse enableIntegrationResponse) {
        this.enableIntegrationResponse = enableIntegrationResponse;
    }

    public void setSendToIntegrationResponse(SendToIntegrationResponse sendToIntegrationResponse) {
        this.sendToIntegrationResponse = sendToIntegrationResponse;
    }
}
