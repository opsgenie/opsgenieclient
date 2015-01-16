package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:11 AM
 */
public class OpsGenieClientMockRequestProcessor {
    private List<Request> executedRequests = new ArrayList<Request>();
    private OpsGenieClientException exception;
    private IOException ioexception;

    protected OpsGenieClientMockRequestProcessor() {
    }

    protected void processRequest(Request addForwardingRequest) throws IOException, OpsGenieClientException {
        executedRequests.add(addForwardingRequest);
        if (exception != null) throw exception;
        if (ioexception != null) throw ioexception;
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

    public void setExecutedRequests(List<Request> executedRequests) {
        this.executedRequests = executedRequests;
    }
}
