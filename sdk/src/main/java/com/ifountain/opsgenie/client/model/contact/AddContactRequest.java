package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;
import com.ifountain.opsgenie.client.model.beans.Contact;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;

/**
 * Container for the parameters to make an add contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactRequest extends BaseUserComponentRequest<AddContactResponse> {
    private Method method;
    private String to;

    /**
     * Rest api uri of adding contact operation.
     */
    public String getEndPoint() {
        return "/v1/json/user/contact";
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public AddContactResponse createResponse() {
        return new AddContactResponse();
    }

    public Contact.Method getMethod() {
        return method;
    }

    public void setMethod(Contact.Method method) {
        this.method = method;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
