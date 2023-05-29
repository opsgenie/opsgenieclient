package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make an enable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#enableContact(EnableContactRequest)
 */
public class EnableContactRequest extends BaseContactRequest<EnableContactResponse, EnableContactRequest> {

    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() + "/contacts/" + getContactId() + "/enable";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getContactId() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.CONTACT_ID);
        if (getUserIdentifier() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
    }

    @Override
    public EnableContactResponse createResponse() {
        return new EnableContactResponse();
    }
}
