package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * Container for the parameters to make a get contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactRequest extends BaseContactRequest<GetContactResponse, GetContactRequest> {

    /**
     * Rest api uri of getting contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() + "/contacts/" + getContactId();
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null.
     */

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (StringUtils.isEmpty(getContactId()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.CONTACT_ID);
        if (StringUtils.isEmpty(getUserIdentifier()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
    }

    @Override
    public GetContactResponse createResponse() {
        return new GetContactResponse();
    }

}
