package com.ifountain.opsgenie.client.rest;

import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;

import java.io.IOException;

interface ResponseHandler {
    void handleResponse(OpsGenieHttpResponse response) throws OpsGenieClientException, IOException;
}
