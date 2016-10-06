package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.account.GetAccountInfoRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountInfoResponse;

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
     * @param getAccountInfoRequest() Object to construct request parameters.
     * @return <code>GetAccountInfoResponse</code> object containing OpsGenie response information.
     * @see GetAccountInfoRequest
     * @see GetAccountInfoResponse
     */
    GetAccountInfoResponse getAccount(GetAccountInfoRequest getAccountInfoRequest) throws IOException, OpsGenieClientException, ParseException;

}
