package com.ifountain.opsgenie.client.http;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;

/**
 * Created by ifountain-qj on 4/4/14.
 */
public interface OpsgenieRequestRetryHandler extends HttpRequestRetryHandler{
    public boolean retryRequest(HttpRequest request, OpsGenieHttpResponse response, int executionCount);
}
