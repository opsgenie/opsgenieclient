package com.ifountain.opsgenie.client.model.integration;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.ifountain.opsgenie.client.model.BaseRequestWithHttpParameters;

import java.util.Map;

/**
 * Container for the parameters to send integration webhook call.
 *
 * @see com.ifountain.opsgenie.client.IIntegrationOpsGenieClient#sendToIntegration(com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest)
 */
public class SendToIntegrationRequest extends BaseRequestWithHttpParameters<SendToIntegrationResponse, SendToIntegrationRequest> {
    private String endPoint;
    private Map<String, Object> contentParameters;

    @JsonAnyGetter
    public Map<String, Object> getContentParameters() {
        return contentParameters;
    }

    public void setContentParameters(Map<String, Object> contentParameters) {
        this.contentParameters = contentParameters;
    }

    /**
     * Rest api uri of send to integration operation.
     */
    @Override
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public SendToIntegrationResponse createResponse() {
        return new SendToIntegrationResponse();
    }

    public SendToIntegrationRequest withEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public SendToIntegrationRequest withContentParameters(Map<String, Object> contentParameters) {
        this.contentParameters = contentParameters;
        return this;
    }
}
