package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.beans.RenotifyRecipient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
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
	 * @deprecated Use getRecipientList
	 */
	@Deprecated
	@JsonIgnore
	public List<RenotifyRecipient> getRecipients() {
		if (recipients == null)
			return null;
		List<RenotifyRecipient> renotifyRecipientList = new ArrayList<RenotifyRecipient>();
		for (String participantName : recipients) {
			RenotifyRecipient recipient = new RenotifyRecipient();
			recipient.setRecipient(participantName);
			renotifyRecipientList.add(recipient);
		}
		return renotifyRecipientList;
	}

	/**
	 * @deprecated Use setRecipientList
	 */
	@Deprecated
	@JsonIgnore
	public void setRecipients(List<RenotifyRecipient> recipients) {
		if (recipients != null) {
			this.recipients = new ArrayList();
			for (RenotifyRecipient renotifyRecipient : recipients)
				this.recipients.add(renotifyRecipient.getRecipient());
		} else
			this.recipients = null;
	}

	/**
	 * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
	 */
	@Override
	public RenotifyResponse createResponse() {
		return new RenotifyResponse();
	}

	@JsonProperty("recipients")
	public List<String> getRecipientList() {
		return recipients;
	}

	public void setRecipientList(List<String> recipients) {
		this.recipients = recipients;
	}
}
