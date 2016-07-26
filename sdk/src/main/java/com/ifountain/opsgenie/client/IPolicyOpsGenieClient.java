package com.ifountain.opsgenie.client;

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
     * Enable or disable policy
     *
     * @param enablePolicyRequest  Object to construct request parameters.
     * @see com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest
     * @see com.ifountain.opsgenie.client.model.policy.EnablePolicyResponse
     */
    public EnablePolicyResponse enablePolicy(EnablePolicyRequest enablePolicyRequest) throws ParseException, OpsGenieClientException, IOException;

   
}
