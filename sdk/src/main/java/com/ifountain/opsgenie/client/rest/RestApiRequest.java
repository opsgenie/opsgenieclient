package com.ifountain.opsgenie.client.rest;

import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.rest.response.RestFailureResult;
import com.ifountain.opsgenie.client.rest.response.RestSuccessResult;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class RestApiRequest {
    private static final String AUTHENTICATION_TOKEN_TYPE = "GenieKey";
    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String RESPONSE_TIME_HEADER = "X-Response-Time";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final Log logger;
    private final OpsGenieHttpClient httpClient;
    private Object body;
    private String uri;
    private String rootUri;
    private ResponseHandler responseHandler = new DefaultResponseHandler();
    private Map<String, String> headers = new HashMap<String, String>();
    private HttpMethod httpMethod;
    private String rootApiKey;
    private Map<String, Object> parameters = new HashMap<String, Object>();

    RestApiRequest(HttpMethod httpMethod, OpsGenieHttpClient httpClient, Log logger, String rootUri) {
        this.httpMethod = httpMethod;
        this.httpClient = httpClient;
        this.logger = logger;
        this.rootUri = rootUri;
    }

    public RestApiRequest httpEntity(HttpEntity entity) {
        this.body = entity;
        return this;
    }

    public RestApiRequest content(String content) {
        this.body = content;
        return this;
    }

    public RestApiRequest json(Object json) throws IOException, ParseException {
        this.body = json;
        this.headers.put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        return this;
    }

    public RestApiRequest apiKey(String genieKey) {
        this.rootApiKey = genieKey;
        return this;
    }

    private void setGenieKey(String genieKey) {
        this.headers.put(HttpHeaders.AUTHORIZATION, AUTHENTICATION_TOKEN_TYPE + " " + genieKey);
    }

    public RestApiRequest uri(String uri) {
        this.uri = uri;
        return this;
    }

    public RestApiRequest responseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        return this;
    }

    public RestApiRequest addParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public RestApiRequest parameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    private void validate() {
        assertNotNull(this.logger, "log");
        assertNotNull(this.httpClient, "httpClient");
        assertNotBlank(this.uri, "uri");
    }

    private void assertNotNull(Object field, String name) {
        if (field == null) {
            throw new IllegalStateException(name + " cannot be null");
        }
    }

    private void assertNotBlank(String field, String name) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalStateException(name + " cannot be blank");
        }
    }

    public <T> RestSuccessResult<T> getResponse(Class<T> claz) throws OpsGenieClientException, IOException, ParseException {
        validate();
        logger.info("Executing OpsGenie " + httpMethod + " request to [" + uri + "] with Parameters " + parameters + ", Headers " + headers);

        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }

        setGenieKey(this.rootApiKey);
        if (this.body instanceof BaseRequest) {
            BaseRequest baseRequest = (BaseRequest) body;
            if (baseRequest.getApiKey() != null && !baseRequest.getApiKey().trim().isEmpty()) {
                setGenieKey(baseRequest.getApiKey());
            }
        }

        OpsGenieHttpResponse response = null;

        try {
            if (httpMethod == HttpMethod.GET) {
                response = get();
            } else if (httpMethod == HttpMethod.DELETE) {
                response = delete();
            } else if (httpMethod == HttpMethod.POST) {
                response = post();
            } else if (httpMethod == HttpMethod.PATCH) {
                response = patch();
            }
        } catch (URISyntaxException e) {
            throw new OpsGenieClientValidationException("Invalid [name] or [id]", 400);
        }

        if (responseHandler != null) {
            responseHandler.handleResponse(response);
        }

        T data = convertObject(response.getContent(), claz);
        RestSuccessResult<T> result = new RestSuccessResult<T>();
        result.setData(data)
                .setRawData(response.getContentAsString())
                .setStatusCode(response.getStatusCode());

        if (response.getHeaders().containsKey(REQUEST_ID_HEADER)) {
            result.setRequestId(response.getHeaders().get(REQUEST_ID_HEADER));
        }

        if (response.getHeaders().containsKey(RESPONSE_TIME_HEADER)) {
            try {
                result.setTook(Double.valueOf(response.getHeaders().get(RESPONSE_TIME_HEADER)));
            } catch (NumberFormatException e) {
                logger.debug("Number format exception while parsing Response Time header", e);
            }
        }
        return result;
    }

    private OpsGenieHttpResponse get() throws IOException, URISyntaxException, OpsGenieClientException {
        return httpClient.get(generateUrl(), parameters, headers);
    }

    private OpsGenieHttpResponse post() throws IOException, URISyntaxException, ParseException {
        return body != null && body instanceof HttpEntity ?
                postWithBodyAsHttpEntity()
                : postWithBodyAsJson();
    }

    private OpsGenieHttpResponse postWithBodyAsHttpEntity() throws IOException, URISyntaxException {
        if (parameters != null && !parameters.isEmpty()) {
            return httpClient.post(generateUrl(), (HttpEntity) body, headers, parameters);
        } else {
            return httpClient.post(generateUrl(), (HttpEntity) body, headers);
        }
    }

    private OpsGenieHttpResponse postWithBodyAsJson() throws IOException, URISyntaxException, ParseException {
        String json = JsonUtils.toJson(body);

        if (parameters != null && !parameters.isEmpty()) {
            return httpClient.post(generateUrl(), json, headers, parameters);
        } else {
            return httpClient.post(generateUrl(), json, headers);
        }
    }

    private OpsGenieHttpResponse delete() throws IOException, URISyntaxException, OpsGenieClientException {
        return httpClient.delete(generateUrl(), parameters, headers);
    }

    private OpsGenieHttpResponse patch() throws IOException, ParseException, URISyntaxException {
        String json = JsonUtils.toJson(body);
        return httpClient.patch(generateUrl(), json, parameters, headers);
    }

    /**
     * Map is used for generic deserialization (jackson returns LinkedHashMap by default when
     * generic type is used)
     */
    private <T> T convertObject(byte[] json, Class<T> claz) throws IOException {
        TypeReference<RestSuccessResult<Map<?, ?>>> typeReference = new TypeReference<RestSuccessResult<Map<?, ?>>>() {
        };
        RestSuccessResult<T> successResponse = MAPPER.readValue(json, typeReference);
        Map<?, ?> data = (Map<?, ?>) successResponse.getData();
        return MAPPER.convertValue(data, claz);
    }

    private String generateUrl() {
        return rootUri + uri;
    }

    private static class DefaultResponseHandler implements ResponseHandler {

        @Override
        public void handleResponse(OpsGenieHttpResponse response) throws OpsGenieClientException, IOException {
            if (response.getStatusCode() < 200 || response.getStatusCode() > 299) {
                String contentType = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
                if (contentType != null && contentType.toLowerCase().startsWith("application/json")) {
                    RestFailureResult result = MAPPER.readValue(response.getContentAsString(), RestFailureResult.class);
                    result.setRawData(response.getContentAsString());
                    result.setStatusCode(response.getStatusCode());
                    int code = result.getCode() != null ? result.getCode() : response.getStatusCode();
                    throw new OpsGenieClientException(result.getMessage(), code);
                } else {
                    throw new IOException(new String(response.getContent(), "UTF-8"));
                }
            }
        }
    }
}
