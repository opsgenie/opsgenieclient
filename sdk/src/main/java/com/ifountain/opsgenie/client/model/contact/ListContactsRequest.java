package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.model.BaseRequest;
/**
 * Container for the parameters to make a list contacts api call.
 *
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContacts(ListContactsRequest)
 */
public class ListContactsRequest extends BaseRequest<ListContactsResponse> {
	private String username;
	private String userId;
    /**
     * Rest api uri of listing contact operation.
     */
	@Override
	public String getEndPoint() {
		return "/v1/json/user/contact";
	}

	@Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
	public ListContactsResponse createResponse() {
		return new ListContactsResponse();
	}
    /**
     * Username of contact to be listed.
     */
	public String getUsername() {
		return username;
	}
    /**
     * Sets Username of contact to be listed.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * userId of contact to be listed.
     */
	public String getUserId() {
		return userId;
	}
    /**
     * Sets userId of contact to be listed.
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
