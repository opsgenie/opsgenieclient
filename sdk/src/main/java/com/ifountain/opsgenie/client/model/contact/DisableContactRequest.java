package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * Container for the parameters to make an disable contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(DisableContactRequest)
 */
public class DisableContactRequest extends BaseContactRequest<DisableContactResponse, DisableContactRequest> {

    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() + "/contacts/" + getContactId() + "/disable";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (StringUtils.isEmpty(getContactId()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.CONTACT_ID);
        if (StringUtils.isEmpty(getUserIdentifier()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
    }

    @Override
    public DisableContactResponse createResponse() {
        return new DisableContactResponse();
    }

}
