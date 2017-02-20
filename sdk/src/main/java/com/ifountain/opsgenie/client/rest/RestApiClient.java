package com.ifountain.opsgenie.client.rest;

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RestApiClient {

    private final OpsGenieHttpClient httpClient;

    private final Log logger = LogFactory.getLog(RestApiClient.class);

    private String rootApiKey;

    private String rootUrl;

    public RestApiClient(OpsGenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public RestApiRequest post(String uri) {
        return createRequest(uri, HttpMethod.POST);
    }

    private RestApiRequest createRequest(String uri, HttpMethod method) {
        return new RestApiRequest(method, httpClient, logger, rootUrl).apiKey(rootApiKey).uri(uri);
    }

    public RestApiRequest get(String uri) {
        return createRequest(uri, HttpMethod.GET);
    }

    public RestApiRequest delete(String uri) {
        return createRequest(uri, HttpMethod.DELETE);
    }

    public RestApiRequest patch(String uri) {
        return createRequest(uri, HttpMethod.PATCH);
    }

    public RestApiClient setApiKey(String rootApiKey) {
        this.rootApiKey = rootApiKey;
        return this;
    }

    public RestApiClient setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
        return this;
    }
}
