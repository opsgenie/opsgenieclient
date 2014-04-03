package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

class JsonOpgenieHttpClient {
    private Log log = LogFactory.getLog(JsonOpgenieHttpClient.class);
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

    protected BaseResponse doPostRequest(BaseRequest request) throws IOException, OpsGenieClientException, ParseException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        String uri = rootUri + request.getEndPoint();
        Map parameters = request.serialize();
        log.info("Executing OpsGenie request to ["+uri+"] with Parameters:"+parameters);
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, JsonUtils.toJsonAsBytes(parameters), headers);
        OpsGenieJsonResponse jsonResponse = handleResponse(httpResponse);
        return populateResponse(request.createResponse(), jsonResponse);
    }
    protected BaseResponse doPostRequest(BaseRequest request, MultipartEntity entity) throws IOException, OpsGenieClientException, ParseException {
        String uri = rootUri + request.getEndPoint();
        log.info("Executing OpsGenie request to ["+uri+"] with multipart data");
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, entity);
        OpsGenieJsonResponse jsonResponse = handleResponse(httpResponse);
        return populateResponse(request.createResponse(), jsonResponse);
    }

    protected BaseResponse doDeleteRequest(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        try {
            String uri = rootUri + request.getEndPoint();
            Map parameters = request.serialize();
            log.info("Executing OpsGenie request to ["+uri+"] with Parameters:"+parameters);
            OpsGenieHttpResponse httpResponse = httpClient.delete(uri, parameters);
            OpsGenieJsonResponse jsonResponse = handleResponse(httpResponse);
            return populateResponse(request.createResponse(), jsonResponse);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
    protected BaseResponse doGetRequest(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        try {
            String uri = rootUri + request.getEndPoint();
            Map parameters = request.serialize();
            log.info("Executing OpsGenie request to ["+uri+"] with Parameters:"+parameters);
            OpsGenieHttpResponse httpResponse = httpClient.get(uri, parameters);
            OpsGenieJsonResponse jsonResponse = handleResponse(httpResponse);
            return populateResponse(request.createResponse(), jsonResponse);

        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    private BaseResponse populateResponse(BaseResponse response, OpsGenieJsonResponse jsonResponse) throws ParseException {
        response.deserialize(jsonResponse.getJson());
        response.setJson(new String(jsonResponse.getContent()));
        return response;
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
