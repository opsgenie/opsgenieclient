package com.ifountain.opsgenie.client.model.account;

import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a get account api call.
 *
 * @see com.ifountain.opsgenie.client.IAccountOpsGenieClient#getAccount(GetAccountInfoRequest)
 */
public class GetAccountInfoRequest extends BaseRequest<GetAccountInfoResponse> {
    /**
     * Rest api uri of getting account operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/account/info";
	}

	@Override
	public GetAccountInfoResponse createResponse() {
		return new GetAccountInfoResponse();
	}
}
