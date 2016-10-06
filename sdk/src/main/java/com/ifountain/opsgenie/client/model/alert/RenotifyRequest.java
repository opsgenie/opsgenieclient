package com.ifountain.opsgenie.client.model.alert;

import java.util.List;

/**
 * Container for the parameters to make an renotify alert api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#renotify(RenotifyRequest)
 */
public class RenotifyRequest extends AddNoteRequest {
	private List<String> recipients;

	/**
	 * Rest api uri of renotify alert operation.
	 */
	@Override
	public String getEndPoint() {
		return "/v1/json/alert/renotify";
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	@Override
	public RenotifyResponse createResponse() {
		return new RenotifyResponse();
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
}
