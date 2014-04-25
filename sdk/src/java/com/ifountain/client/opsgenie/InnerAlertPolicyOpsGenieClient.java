package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyResponse;

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
     * @see IAlertPolicyOpsGenieClient#enableAlertPolicy(com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyRequest)
     */
    @Override
    public EnableAlertPolicyResponse enableAlertPolicy(EnableAlertPolicyRequest enableAlertPolicyRequest) throws IOException, ClientException, ParseException {
        return (EnableAlertPolicyResponse) httpClient.doPostRequest(enableAlertPolicyRequest);
    }

}
