package com.ifountain.opsgenie.client.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class DefaultOpsgenieRequestRetryHandler implements OpsgenieRequestRetryHandler {
    private Log log = LogFactory.getLog(OpsGenieHttpClient.class);
    private int maxRetryCount;
    private long waitTime = 500;

    public DefaultOpsgenieRequestRetryHandler() {
        this(5);
    }

    public DefaultOpsgenieRequestRetryHandler(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    @Override
    public boolean retryRequest(HttpRequest request, OpsGenieHttpResponse response, int executionCount) {
        return (response.getStatusCode() >= 500 || response.getStatusCode() == HttpStatus.SC_REQUEST_TIMEOUT) && retry(request, response, null, executionCount);
    }

    @Override
    public boolean retryRequest(IOException e, int executionCount, HttpContext httpContext) {
        HttpRequest request = (HttpRequest)httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
        return !requestIsAborted(request) && retry(request, null, e, executionCount);
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

    private boolean retry(HttpRequest request, OpsGenieHttpResponse response, IOException exception, int executionCount){
        if(executionCount > maxRetryCount){
            return false;
        }
        String uri = null;
        if(request.getRequestLine() != null){
            uri = request.getRequestLine().getUri();
        }
        request.getRequestLine().getUri();
        if(response != null){
            log.info("Retrying request ["+ uri +"] ResponseCode:["+response.getStatusCode()+"]. RetryCount " + executionCount);
        }
        else{
            log.info("Retrying request ["+ uri +"]. Reason:["+exception.getMessage()+"]. RetryCount " + executionCount);
        }
        try {
            Thread.sleep(pauseExp(executionCount));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private long pauseExp(int retryCount){
        return retryCount * waitTime;
    }

}
