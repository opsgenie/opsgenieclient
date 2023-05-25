package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make a delete contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#deleteContact(DeleteContactRequest)
 */
public class DeleteContactRequest extends BaseContactRequest<DeleteContactResponse, DeleteContactRequest> {

    /**
     * Rest api uri of deleting contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() +"/contacts/" + getContactId();
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getContactId() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.CONTACT_ID);
        if (getUserIdentifier() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteContactResponse createResponse() {
        return new DeleteContactResponse();
    }

}
