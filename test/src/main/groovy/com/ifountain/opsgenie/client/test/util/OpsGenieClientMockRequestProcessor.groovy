package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.Request
import com.ifountain.opsgenie.client.swagger.ApiException;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:11 AM
 */
public class OpsGenieClientMockRequestProcessor {
    private List<Request> executedRequests = new ArrayList<Request>();
    private List<Object> executedRequestsV2 = new ArrayList<>();
    private OpsGenieClientException exception;
    private IOException ioexception;

    protected OpsGenieClientMockRequestProcessor() {
    }

    protected void processRequest(Request addForwardingRequest) throws IOException, OpsGenieClientException {
        executedRequests.add(addForwardingRequest);
        if (exception != null) throw exception;
        if (ioexception != null) throw ioexception;
    }

    protected void processRequestV2(Object request) throws ApiException {
        executedRequestsV2.add(request);
    }

    public void setException(Exception exception) {
        this.exception = new OpsGenieClientException(exception.getMessage(), 1);
    }

    public void setIOException(IOException ioexception) {
        this.ioexception = ioexception;
    }

    public List<Request> getExecutedRequests() {
        return executedRequests;
    }

    public List<Object> getExecutedRequestsV2() {
        return this.executedRequestsV2;
    }

    public void setExecutedRequests(List<Request> executedRequests) {
        this.executedRequests = executedRequests;
    }
}
