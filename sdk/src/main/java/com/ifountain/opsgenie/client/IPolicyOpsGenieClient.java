package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.policy.DisablePolicyRequest;
import com.ifountain.opsgenie.client.model.policy.DisablePolicyResponse;
import com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest;
import com.ifountain.opsgenie.client.model.policy.EnablePolicyResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for policy related operations
 *
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IPolicyOpsGenieClient {
    /**
     * Enable policy
     *
     * @param enablePolicyRequest  Object to construct request parameters.
     * @see com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest
     * @see com.ifountain.opsgenie.client.model.policy.EnablePolicyResponse
     */
    public EnablePolicyResponse enablePolicy(EnablePolicyRequest enablePolicyRequest) throws ParseException, OpsGenieClientException, IOException;

    /**
     * disable policy
     *
     * @param disablePolicyRequest  Object to construct request parameters.
     * @see com.ifountain.opsgenie.client.model.policy.DisablePolicyRequest
     * @see com.ifountain.opsgenie.client.model.policy.DisablePolicyResponse
     */
    public DisablePolicyResponse disablePolicy(DisablePolicyRequest disablePolicyRequest) throws ParseException, OpsGenieClientException, IOException;

   
}
