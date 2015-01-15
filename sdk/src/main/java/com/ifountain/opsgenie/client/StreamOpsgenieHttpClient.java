package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.AbstractInputStreamResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

class StreamOpsgenieHttpClient extends AbstractOpsGenieHttpClient {
    /**
     * Constructs a new inner client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public StreamOpsgenieHttpClient(OpsGenieHttpClient httpClient) {
        super(httpClient);
        log = LogFactory.getLog(StreamOpsgenieHttpClient.class);
    }

    @Override
    protected BaseResponse populateResponse(BaseRequest request, OpsGenieHttpResponse httpResponse) throws ParseException, IOException {
        AbstractInputStreamResponse response = (AbstractInputStreamResponse) super.populateResponse(request, httpResponse);
        response.setInputStream(new ByteArrayInputStream(httpResponse.getContent()));
        return response;
    }
}
