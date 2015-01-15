package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for alert policy related operations
 *
 * @see OpsGenieClient
 */
public interface IAlertPolicyOpsGenieClient {
    /**
     * Enable/disable alert policy at OpsGenie.
     *
     * @param enableAlertPolicyRequest Object to construct request parameters.
     * @return <code>EnableAlertPolicyResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest
     * @see com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse
     */
    public EnableAlertPolicyResponse enableAlertPolicy(EnableAlertPolicyRequest enableAlertPolicyRequest) throws IOException, OpsGenieClientException, ParseException;

}
