package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseRequestWithHttpParameters;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mehmet Mustafa Demir
 */
public abstract class AbstractOpsGenieHttpClient {
    protected Log log;
    private String apiKey;

    /**
     * Api key used for authenticating API requests.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the customer key used for authenticating API requests.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * OpsGenie services endpoint uri. Default is https://api.opsgenie.com *
     */
    private String rootUri = OpsGenieClientConstants.OPSGENIE_API_URI;

    /**
     * Http client object *
     */
    protected OpsGenieHttpClient httpClient;

    /**
     * Constructs a new inner client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public AbstractOpsGenieHttpClient(OpsGenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected String getRootUri() {
        return rootUri;
    }

    protected void setRootUri(String rootUri) {
        this.rootUri = rootUri;
    }

    public void close() {
        this.httpClient.close();
    }

    protected BaseResponse doPostRequest(BaseRequest request) throws IOException, OpsGenieClientException, ParseException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        String uri = rootUri + request.getEndPoint();
        String json = JsonUtils.toJson(request);
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + json);
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, json, headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doPostRequest(BaseRequestWithHttpParameters request) throws OpsGenieClientException, IOException, ParseException, URISyntaxException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        String uri = rootUri + request.getEndPoint();
        String json = JsonUtils.toJson(request);
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + json);
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, json, headers, request.getHttpParameters());
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doPostRequest(BaseRequest request, MultipartEntity entity) throws IOException, OpsGenieClientException, ParseException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        String uri = rootUri + request.getEndPoint();
        log.info("Executing OpsGenie request to [" + uri + "] with multipart data");
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, entity);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doDeleteRequest(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        try {
            if (request.getApiKey() == null)
                request.setApiKey(getApiKey());
            request.validate();
            String uri = rootUri + request.getEndPoint();
            Map parameters = JsonUtils.toMap(request);
            log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + parameters);
            OpsGenieHttpResponse httpResponse = httpClient.delete(uri, parameters);
            handleResponse(httpResponse);
            return populateResponse(request, httpResponse);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    protected BaseResponse doGetRequest(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        try {
            if (request.getApiKey() == null)
                request.setApiKey(getApiKey());
            request.validate();
            String uri = rootUri + request.getEndPoint();
            Map parameters = JsonUtils.toMap(request);
            log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + parameters);
            OpsGenieHttpResponse httpResponse = httpClient.get(uri, parameters);
            handleResponse(httpResponse);
            return populateResponse(request, httpResponse);

        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    protected void handleResponse(OpsGenieHttpResponse response) throws IOException, OpsGenieClientException {
        if (response.getStatusCode() != HttpStatus.SC_OK) {
            String contentType = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
            if (contentType != null && contentType.toLowerCase().startsWith("application/json")) {
                Map error = JsonUtils.parse(response.getContent());
                throw new OpsGenieClientException((String) error.get("error"), (Integer) error.get("code"));
            } else {
                throw new IOException(new String(response.getContent(), "UTF-8"));
            }
        }
    }

    protected BaseResponse populateResponse(BaseRequest request, OpsGenieHttpResponse httpResponse) throws ParseException, IOException {
        return request.createResponse();
    }
}
