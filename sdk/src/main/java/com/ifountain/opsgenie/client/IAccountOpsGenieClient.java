package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.account.GetAccountRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for integration related operations
 *
 * @version 6/1/12 10:09 AM
 * @see OpsGenieClient
 */
public interface IAccountOpsGenieClient {
	/**
     * Get Account details
     *
     * @param getAccountRequest Object to construct request parameters.
     * @return <code>GetAccountResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.account.GetAccountRequest
     * @see com.ifountain.opsgenie.client.model.account.GetAccountResponse
     */
    public GetAccountResponse getAccount(GetAccountRequest getAccountRequest) throws IOException, OpsGenieClientException, ParseException;

}
