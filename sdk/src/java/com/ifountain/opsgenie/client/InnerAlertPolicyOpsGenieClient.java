package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Alert Policy Client
 */
public class InnerAlertPolicyOpsGenieClient implements IAlertPolicyOpsGenieClient {
    private JsonOpgenieHttpClient httpClient;
    /**
     * Constructs a new alert client to invoke service methods on OpsGenie for alerts using the specified client and root URI.
     */
    public InnerAlertPolicyOpsGenieClient(JsonOpgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @see IAlertPolicyOpsGenieClient#enableAlertPolicy(com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest)
     */
    @Override
    public EnableAlertPolicyResponse enableAlertPolicy(EnableAlertPolicyRequest enableAlertPolicyRequest) throws IOException, OpsGenieClientException, ParseException {
        return (EnableAlertPolicyResponse) httpClient.doPostRequest(enableAlertPolicyRequest);
    }

}
