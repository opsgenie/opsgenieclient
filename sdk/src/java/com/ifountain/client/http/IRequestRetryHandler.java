package com.ifountain.client.http;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;

/**
 * Created by ifountain-qj on 4/4/14.
 */
public interface IRequestRetryHandler extends HttpRequestRetryHandler{
    public boolean retryRequest(HttpRequest request, HttpResponse response, int executionCount);
}
