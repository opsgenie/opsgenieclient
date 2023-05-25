package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for add contact request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactResponse extends BaseResponseWithId {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
