package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.contact.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Inner Contact Client
 */
public class InnerContactOpsGenieClient implements IContactOpsGenieClient {
	private JsonOpsgenieHttpClient httpClient;

	/**
	 * Constructs a new contact client to invoke service methods on OpsGenie for
	 * contacts using the specified client and root URI.
	 */
	public InnerContactOpsGenieClient(JsonOpsgenieHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(com.ifountain.opsgenie.client.model.contact.AddContactRequest)
	 */
	@Override
	public AddContactResponse addContact(AddContactRequest addContactRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (AddContactResponse) httpClient.doPostRequestV2(addContactRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#updateContact(com.ifountain.opsgenie.client.model.contact.UpdateContactRequest)
	 */
	@Override
	public UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (UpdateContactResponse) httpClient.doPatchRequestV2(updateContactRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#deleteContact(com.ifountain.opsgenie.client.model.contact.DeleteContactRequest)
	 */
	@Override
	public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (DeleteContactResponse) httpClient.doDeleteRequestV2(deleteContactRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(com.ifountain.opsgenie.client.model.contact.EnableContactRequest)
	 */
	@Override
	public EnableContactResponse enableContact(EnableContactRequest enableContactRequest)
			throws ParseException, OpsGenieClientException, IOException {
		return (EnableContactResponse) httpClient.doPostRequestV2(enableContactRequest);
	}
	
	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(com.ifountain.opsgenie.client.model.contact.DisableContactRequest)
	 */
	@Override
	public DisableContactResponse disableContact(DisableContactRequest disableContactRequest)
			throws ParseException, OpsGenieClientException, IOException {
		return (DisableContactResponse) httpClient.doPostRequestV2(disableContactRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(com.ifountain.opsgenie.client.model.contact.GetContactRequest)
	 */
	@Override
	public GetContactResponse getContact(GetContactRequest getContactRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (GetContactResponse) httpClient.doGetRequestV2(getContactRequest);
	}

	/**
	 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContact(com.ifountain.opsgenie.client.model.contact.ListContactsRequest)
	 */
	@Override
	public ListContactsResponse listContact(ListContactsRequest listContactRequest)
			throws IOException, OpsGenieClientException, ParseException {
		return (ListContactsResponse) httpClient.doGetRequestV2(listContactRequest);
	}

}
