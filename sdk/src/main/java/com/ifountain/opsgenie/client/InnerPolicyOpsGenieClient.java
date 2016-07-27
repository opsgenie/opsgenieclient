package com.ifountain.opsgenie.client;

import java.io.IOException;
import java.text.ParseException;

import com.ifountain.opsgenie.client.model.policy.*;

/**
 * Inner Policy Client
 */
public class InnerPolicyOpsGenieClient implements IPolicyOpsGenieClient{
    private JsonOpsgenieHttpClient httpClient;
    /**
     * Constructs a new integration client to invoke service methods on OpsGenie for integrations using the specified client and root URI.
     */
    public InnerPolicyOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @see com.ifountain.opsgenie.client.IPolicyOpsGenieClient#enablePolicy(com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest)
     */
    @Override
    public EnablePolicyResponse enablePolicy(EnablePolicyRequest enablePolicyRequest) throws IOException, OpsGenieClientException, ParseException {
        return (EnablePolicyResponse) httpClient.doPostRequest(enablePolicyRequest);
    }
    /**
     * @see com.ifountain.opsgenie.client.IPolicyOpsGenieClient#disablePolicy(com.ifountain.opsgenie.client.model.policy.DisablePolicyRequest)
     */
    @Override
    public DisablePolicyResponse disablePolicy(DisablePolicyRequest disablePolicyRequest) throws IOException, OpsGenieClientException, ParseException {
        return (DisablePolicyResponse) httpClient.doPostRequest(disablePolicyRequest);
    }

}
