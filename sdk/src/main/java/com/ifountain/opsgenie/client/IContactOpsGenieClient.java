package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.contact.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing OpsGenie for contact related operations
 *
 * @author Mehmet Mustafa Demir
 * @version 6/1/12 10:09 AM
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface IContactOpsGenieClient {
    /**
     * Adds a contact at OpsGenie.
     *
     * @param addContactRequest Object to construct request parameters.
     * @return <code>AddContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.contact.AddContactRequest
     * @see com.ifountain.opsgenie.client.model.contact.AddContactResponse
     */
    public AddContactResponse addContact(AddContactRequest addContactRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Updates contact at OpsGenie.
     *
     * @param updateContactRequest Object to construct request parameters.
     * @return <code>UpdateContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.contact.UpdateContactRequest
     * @see com.ifountain.opsgenie.client.model.contact.UpdateContactResponse
     */
    public UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Deletes a contact at OpsGenie.
     *
     * @param deleteContactRequest Object to construct request parameters.
     * @return <code>DeleteContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.contact.DeleteContactRequest
     * @see com.ifountain.opsgenie.client.model.contact.DeleteContactResponse
     */
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * Enable or disable contact
     *
     * @param enableContactRequest  Object to construct request parameters.
     * @return <code>EnableContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest
     * @see com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse
     */
    public EnableContactResponse enableContact(EnableContactRequest enableContactRequest) throws ParseException, OpsGenieClientException, IOException;

    
    /**
     * Get contact details
     *
     * @param getContactRequest Object to construct request parameters.
     * @return <code>GetContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.contact.GetContactRequest
     * @see com.ifountain.opsgenie.client.model.contact.GetContactResponse
     */
    public GetContactResponse getContact(GetContactRequest getContactRequest) throws IOException, OpsGenieClientException, ParseException;

    /**
     * List contact of customer
     *
     * @param listContactRequest Object to construct request parameters.
     * @return <code>ListContactResponse</code> object containing OpsGenie response information.
     * @see com.ifountain.opsgenie.client.model.contact.ListContactsRequest
     * @see com.ifountain.opsgenie.client.model.contact.ListContactsResponse
     */
    public ListContactsResponse listContact(ListContactsRequest listContactsRequest) throws IOException, OpsGenieClientException, ParseException;


}
