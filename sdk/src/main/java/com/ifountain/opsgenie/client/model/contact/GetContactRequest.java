package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

/**
 * Container for the parameters to make a get contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactRequest extends BaseContactRequestWithId<GetContactResponse, GetContactRequest> {

    /**
     * Rest api uri of getting contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/contact";
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null.
     */

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getId() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
    }

    @Override
    public GetContactResponse createResponse() {
        return new GetContactResponse();
    }

}
