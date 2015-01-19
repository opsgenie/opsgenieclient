package com.ifountain.opsgenie.client.test.util;

import com.ifountain.opsgenie.client.IAlertPolicyOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientException;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse

import java.text.ParseException;

public class InnerAlertPolicyOpsGenieClientMock implements IAlertPolicyOpsGenieClient {
    private OpsGenieClientMockRequestProcessor requestProcessor;
    private EnableAlertPolicyResponse enableAlertPolicyResponse;
    public InnerAlertPolicyOpsGenieClientMock(OpsGenieClientMockRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    @Override
    public EnableAlertPolicyResponse enableAlertPolicy(EnableAlertPolicyRequest enableAlertPolicyRequest) throws IOException, OpsGenieClientException, ParseException {
        requestProcessor.processRequest(enableAlertPolicyRequest);
        return enableAlertPolicyResponse;
    }

    public void setEnableAlertPolicyResponse(EnableAlertPolicyResponse enableAlertPolicyResponse) {
        this.enableAlertPolicyResponse = enableAlertPolicyResponse;
    }
}
