package com.ifountain.opsgenie.client.model.account;

import java.text.ParseException;
import java.util.Map;

import com.ifountain.opsgenie.client.model.BaseResponse;
/**
 * Represents OpsGenie service response for get account request.
 *
 * @see com.ifountain.opsgenie.client.IAccountOpsGenieClient#getAccount(GetAccountInfoRequest)
 */
import com.ifountain.opsgenie.client.model.beans.Account;
public class GetAccountInfoResponse extends BaseResponse {
	private Account account;
	
	@Override
	public void deserialize(Map data) throws ParseException {
		super.deserialize(data);
		account = new Account();
		account.fromMap(data);
	}
    /**
     * Details of account
     * @see Account
     */
	public Account getAccount() {
		return account;
	}
    /**
     * Sets details of account
     * @see Account
     */
	public void setAccount(Account account) {
		this.account = account;
	}
}