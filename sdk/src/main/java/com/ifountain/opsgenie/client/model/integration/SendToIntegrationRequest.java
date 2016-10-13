package com.ifountain.opsgenie.client.model.integration;

import com.ifountain.opsgenie.client.model.BaseRequestWithHttpParameters;
import org.codehaus.jackson.annotate.JsonAnyGetter;

import java.util.Map;

/**
 * Container for the parameters to send integration webhook call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IIntegrationOpsGenieClient#sendToIntegration(com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest)
 */
public class SendToIntegrationRequest extends BaseRequestWithHttpParameters<SendToIntegrationResponse> {
    private String endPoint;
    private Map<String, Object> contentParameters;

    @JsonAnyGetter
    public Map<String, Object> getContentParameters() {
        return contentParameters;
    }

    public void setContentParameters(Map<String, Object> contentParameters) {
        this.contentParameters = contentParameters;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Rest api uri of send to integration operation.
     */
    @Override
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public SendToIntegrationResponse createResponse() {
        return new SendToIntegrationResponse();
    }
}
