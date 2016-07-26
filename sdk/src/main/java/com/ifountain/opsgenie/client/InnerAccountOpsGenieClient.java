package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.account.GetAccountRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Contact Client
 */
public class InnerAccountOpsGenieClient implements IAccountOpsGenieClient {
	private JsonOpsgenieHttpClient httpClient;

	/**
	 * Constructs a new account client to invoke service methods on OpsGenie for
	 * accounts using the specified client and root URI.
	 */
	public InnerAccountOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	/**
	 * @see com.ifountain.opsgenie.client.IAccountOpsGenieClient#getAccount(com.ifountain.opsgenie.client.model.account.GetAccountRequest)
	 */
	@Override
	public GetAccountResponse getAccount(GetAccountRequest getAccountRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (GetAccountResponse) httpClient.doGetRequest(getAccountRequest);
	}

}
