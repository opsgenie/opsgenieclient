package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.beans.Contact;
import com.ifountain.opsgenie.client.model.beans.Contact.Method;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Container for the parameters to make an add contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#addContact(AddContactRequest)
 */
public class AddContactRequest extends BaseContactRequest<AddContactResponse, AddContactRequest> {
    private Method method;
    private String to;

    /**
     * Rest api uri of adding contact operation.
     */
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() + "/contacts";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if(StringUtils.isEmpty(to))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.TO);
        if(Objects.isNull(method))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.METHOD);
        if (StringUtils.isEmpty(getUserIdentifier()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
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

    public AddContactRequest withMethod(Method method) {
        this.method = method;
        return this;
    }

    public AddContactRequest withTo(String to) {
        this.to = to;
        return this;
    }

}
