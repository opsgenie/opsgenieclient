package com.ifountain.opsgenie.client.model.account;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.Account;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 * Represents OpsGenie service response for get account request.
 *
 * @see com.ifountain.opsgenie.client.IAccountOpsGenieClient#getAccount(GetAccountInfoRequest)
 */

public class GetAccountInfoResponse extends BaseResponse {
    @JsonUnwrapped
    private Account account;

    /**
     * Details of account
     *
     * @see Account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets details of account
     *
     * @see Account
     */
	public void setAccount(Account account) {
		this.account = account;
	}
}