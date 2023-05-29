package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.account.GetAccountInfoRequest;
import com.ifountain.opsgenie.client.model.account.GetAccountInfoResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Account Client
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
	 * @see com.ifountain.opsgenie.client.IAccountOpsGenieClient#getAccount(GetAccountInfoRequest)
	 */
	@Override
	public GetAccountInfoResponse getAccount(GetAccountInfoRequest getAccountRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (GetAccountInfoResponse) httpClient.doGetRequestV2(getAccountRequest);
	}

}
