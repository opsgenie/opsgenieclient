package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

class JsonOpsgenieHttpClient extends AbstractOpsGenieHttpClient {
    /**
     * Constructs a new inner client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public JsonOpsgenieHttpClient(OpsGenieHttpClient httpClient) {
        super(httpClient);
        log = LogFactory.getLog(JsonOpsgenieHttpClient.class);
    }

    @Override
    protected BaseResponse populateResponse(BaseRequest request, OpsGenieHttpResponse httpResponse) throws ParseException, IOException {
        BaseResponse response = super.populateResponse(request, httpResponse);
        OpsGenieJsonResponse jsonResponse = new OpsGenieJsonResponse(httpResponse.getContent());
        response.fromJson(new String(jsonResponse.getContent()));
        return response;
    }

    protected static class OpsGenieJsonResponse {
        byte[] content;
        Map json;

        public OpsGenieJsonResponse(byte[] content) throws IOException {
            this.content = content;
            json = JsonUtils.parse(content);
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public Map getJson() {
            return json;
        }

        public void setJson(Map json) {
            this.json = json;
        }

        public Map json(){
            return json;
        }
    }
}
