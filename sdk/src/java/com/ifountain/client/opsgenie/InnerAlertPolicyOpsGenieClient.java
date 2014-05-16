package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.JsonHttpClient;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Alert Policy Client
 *
 * @author Halit Okumus
 * @version 04.03.2014 09:40
 * @see com.ifountain.client.opsgenie.IAlertOpsGenieClient
 */
public class InnerAlertPolicyOpsGenieClient implements IAlertPolicyOpsGenieClient {
    private JsonHttpClient httpClient;
    /**
     * Constructs a new alertpolicy client to invoke service methods on OpsGenie for alert policies using the specified client and root URI.
     */
    public InnerAlertPolicyOpsGenieClient(JsonHttpClient httpClient) {
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
