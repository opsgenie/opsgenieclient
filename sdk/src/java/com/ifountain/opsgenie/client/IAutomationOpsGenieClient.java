package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.InputStreamAttachRequest;
import com.ifountain.opsgenie.client.model.alert.*;
import com.ifountain.opsgenie.client.model.automation.EnableAutomationRequest;
import com.ifountain.opsgenie.client.model.automation.EnableAutomationResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for automation related operations
 *
 * @see OpsGenieClient
 */
public interface IAutomationOpsGenieClient {
    /**
     * Enable/disable automation at OpsGenie.
     *
     * @param enableAutomationRequest Object to construct request parameters.
     * @return <code>EnableAutomationResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.automation.EnableAutomationRequest
     * @see com.ifountain.opsgenie.client.model.automation.EnableAutomationResponse
     */
    public EnableAutomationResponse enableAutomation(EnableAutomationRequest enableAutomationRequest) throws IOException, OpsGenieClientException, ParseException;

}
