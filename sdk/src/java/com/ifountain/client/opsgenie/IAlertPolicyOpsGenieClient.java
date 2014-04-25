package com.ifountain.client.opsgenie;

import com.ifountain.client.ClientException;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyResponse;

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
     * @see com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyRequest
     * @see com.ifountain.client.opsgenie.model.alertpolicy.EnableAlertPolicyResponse
     */
    public EnableAlertPolicyResponse enableAlertPolicy(EnableAlertPolicyRequest enableAlertPolicyRequest) throws IOException, ClientException, ParseException;

}
