package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * Container for the parameters to make an update contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#updateContact(UpdateContactRequest)
 */
public class UpdateContactRequest extends BaseContactRequest<UpdateContactResponse, UpdateContactRequest> {
    private String to;

    /**
     * Rest api uri of updating contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() +"/contacts/" + getContactId();
    }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (getContactId() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.CONTACT_ID);
        if (getUserIdentifier() == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
        if(StringUtils.isEmpty(to))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.TO);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public UpdateContactResponse createResponse() {
        return new UpdateContactResponse();
    }

    /**
     * to of contact to be updated
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets to of contact to be updated
     */
    public void setTo(String to) {
        this.to = to;
    }

    public UpdateContactRequest withTo(String to) {
        this.to = to;
        return this;
    }


}
