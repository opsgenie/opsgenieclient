package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 4/9/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
class JsonOpgenieHttpClient {
    /**
     * Http client object *
     */
    private OpsGenieHttpClient httpClient;

    /**
     * OpsGenie services endpoint uri. Default is https://api.opsgenie.com *
     */
    private String rootUri = OpsGenieClientConstants.OPSGENIE_API_URI;

    /**
     * Constructs a new inner client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public JsonOpgenieHttpClient(OpsGenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected OpsGenieJsonResponse handleResponse(OpsGenieHttpResponse response) throws IOException, OpsGenieClientException {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return new OpsGenieJsonResponse(response.getContent());
        } else {
            String contentType = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && contentType.toLowerCase().startsWith("application/json")) {
                Map error = JsonUtils.parse(response.getContent());
                throw new OpsGenieClientException((String) error.get("error"), (Integer) error.get("code"));
            } else {
                throw new IOException(new String(response.getContent(), "UTF-8"));
            }
        }
    }

    protected OpsGenieJsonResponse doPostRequest(BaseRequest updateForwardingRequest, Map json) throws IOException, OpsGenieClientException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + updateForwardingRequest.getEndPoint(), JsonUtils.toJsonAsBytes(json), headers);
        return handleResponse(httpResponse);
    }
    protected OpsGenieJsonResponse doPostRequest(BaseRequest updateForwardingRequest, MultipartEntity entity) throws IOException, OpsGenieClientException {
        OpsGenieHttpResponse httpResponse = httpClient.post(rootUri + updateForwardingRequest.getEndPoint(), entity);
        return handleResponse(httpResponse);
    }

    protected OpsGenieJsonResponse doDeleteRequest(BaseRequest request, Map<String, Object> json) throws OpsGenieClientException, IOException {
        try {
            OpsGenieHttpResponse httpResponse = httpClient.delete(rootUri + request.getEndPoint(), json);
            return handleResponse(httpResponse);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
    protected OpsGenieJsonResponse doGetRequest(BaseRequest request, Map<String, Object> json) throws OpsGenieClientException, IOException {
        try {
            OpsGenieHttpResponse httpResponse = httpClient.get(rootUri + request.getEndPoint(), json);
            return handleResponse(httpResponse);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    protected String getRootUri() {
        return rootUri;
    }

    protected void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    private OpsGenieHttpClient getHttpClient() {
        return httpClient;
    }

    private void setHttpClient(OpsGenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void close(){
        this.httpClient.close();
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
