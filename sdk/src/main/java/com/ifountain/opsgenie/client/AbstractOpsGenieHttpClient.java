package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseRequestWithHttpParameters;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.ifountain.opsgenie.client.util.LogUtils;
import org.apache.commons.logging.Log;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOpsGenieHttpClient {
    protected Log log;
    /**
     * Http client object *
     */
    protected OpsGenieHttpClient httpClient;
    private String apiKey;
    /**
     * OpsGenie services endpoint uri. Default is https://api.opsgenie.com *
     */
    private String rootUri = OpsGenieClientConstants.OPSGENIE_API_URI;

    /**
     * Constructs a new inner client to invoke service methods on OpsGenie using the specified client configuration options.
     */
    public AbstractOpsGenieHttpClient(OpsGenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

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
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + LogUtils.getInsensitiveLogMessage(JsonUtils.toMap(request)));
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, json, headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doPostRequest(BaseRequestWithHttpParameters request) throws OpsGenieClientException, IOException, ParseException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        String uri = rootUri + request.getEndPoint();
        String json = JsonUtils.toJson(request);
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + LogUtils.getInsensitiveLogMessage(JsonUtils.toMap(request)));
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
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        String uri = rootUri + request.getEndPoint();
        Map parameters = JsonUtils.toMap(request);
        log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + LogUtils.getInsensitiveLogMessage(parameters));
        OpsGenieHttpResponse httpResponse = httpClient.delete(uri, parameters);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doGetRequest(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
        String uri = rootUri + request.getEndPoint();

        Map parameters = JsonUtils.toMap(request);
        log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + LogUtils.getInsensitiveLogMessage(parameters));
        OpsGenieHttpResponse httpResponse = httpClient.get(uri, parameters);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doPostRequestV2(BaseRequest request) throws IOException, OpsGenieClientException, ParseException {
        setAndValidateApiKey(request);
        Map<String, String> headers = request.getReqHeadersForPostOrPatch();
        String uri = rootUri + request.getEndPoint();
        String json = JsonUtils.toJson(request);
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + LogUtils.getInsensitiveLogMessage(JsonUtils.toMap(request)));
        OpsGenieHttpResponse httpResponse = httpClient.post(uri, json, headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }
    protected BaseResponse doGetRequestV2(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        setAndValidateApiKey(request);
        String uri = rootUri + request.getEndPoint();

        Map<String,Object> parameters = request.getRequestParams();
        Map<String,String> headers = request.getReqHeadersForGetOrDelete();
        log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + LogUtils.getInsensitiveLogMessage(parameters));
        OpsGenieHttpResponse httpResponse = httpClient.get(uri, parameters,headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doDeleteRequestV2(BaseRequest request) throws OpsGenieClientException, IOException, ParseException {
        setAndValidateApiKey(request);
        String uri = rootUri + request.getEndPoint();
        Map<String,Object> parameters = request.getRequestParams();
        Map<String,String> headers = request.getReqHeadersForGetOrDelete();
        log.info("Executing OpsGenie request to [" + uri + "] with Parameters:" + LogUtils.getInsensitiveLogMessage(parameters));
        OpsGenieHttpResponse httpResponse = httpClient.delete(uri, parameters,headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    protected BaseResponse doPatchRequestV2(BaseRequest request) throws IOException, OpsGenieClientException, ParseException {
        setAndValidateApiKey(request);
        Map<String,Object> parameters = request.getRequestParams();
        Map<String, String> headers = request.getReqHeadersForPostOrPatch();
        String uri = rootUri + request.getEndPoint();
        String json = JsonUtils.toJson(request);
        log.info("Executing OpsGenie request to [" + uri + "] with content Parameters:" + LogUtils.getInsensitiveLogMessage(JsonUtils.toMap(request)));
        OpsGenieHttpResponse httpResponse = httpClient.patch(uri, json, parameters,headers);
        handleResponse(httpResponse);
        return populateResponse(request, httpResponse);
    }

    private void setAndValidateApiKey(BaseRequest request) throws OpsGenieClientValidationException {
        if (request.getApiKey() == null)
            request.setApiKey(getApiKey());
        request.validate();
    }

    protected void handleResponse(OpsGenieHttpResponse response) throws IOException, OpsGenieClientException {
        if ((response.getStatusCode() != HttpStatus.SC_OK) && (response.getStatusCode() != HttpStatus.SC_CREATED)) {
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
