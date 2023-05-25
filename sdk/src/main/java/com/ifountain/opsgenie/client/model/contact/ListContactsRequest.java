package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;

import java.util.Objects;

/**
 * Container for the parameters to make a list contacts api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#listContact(ListContactsRequest)
 */
public class ListContactsRequest extends BaseContactRequest<ListContactsResponse, ListContactsRequest> {

    /**
     * Rest api uri of listing contact operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + getUserIdentifier() + "/contacts";
    }

    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (Objects.isNull(getUserIdentifier()))
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.USER_IDENTIFIER);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public ListContactsResponse createResponse() {
        return new ListContactsResponse();
    }

}
