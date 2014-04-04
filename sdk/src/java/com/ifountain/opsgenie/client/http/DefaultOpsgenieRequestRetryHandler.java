package com.ifountain.opsgenie.client.http;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class DefaultOpsgenieRequestRetryHandler implements OpsgenieRequestRetryHandler {
    private int maxRetryCount;

    public DefaultOpsgenieRequestRetryHandler() {
        this(5);
    }

    public DefaultOpsgenieRequestRetryHandler(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    @Override
    public boolean retryRequest(OpsGenieHttpResponse response, int executionCount) {
        if(executionCount > maxRetryCount){
            return false;
        }
        if(response.getStatusCode() >= 500 || response.getStatusCode() == HttpStatus.SC_REQUEST_TIMEOUT){
            return true;
        }
        return false;
    }

    @Override
    public boolean retryRequest(IOException e, int executionCount, HttpContext httpContext) {
        if(executionCount > maxRetryCount){
            return false;
        }
        HttpRequest request = (HttpRequest)httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
        if(requestIsAborted(request)){
            return false;
        }
        return true;
    }

    /**
     * @since 4.2
     */
    protected boolean requestIsAborted(final HttpRequest request) {
        HttpRequest req = request;
        if (request instanceof RequestWrapper) { // does not forward request to original
            req = ((RequestWrapper) request).getOriginal();
        }
        return (req instanceof HttpUriRequest && ((HttpUriRequest)req).isAborted());
    }
}
