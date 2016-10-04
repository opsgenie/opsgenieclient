package com.ifountain.opsgenie.client.model.contact;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseUserComponentRequest;

/**
 * Container for the parameters to make a get contact api call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#getContact(GetContactRequest)
 */
public class GetContactRequest extends BaseUserComponentRequest<GetContactResponse> {
    private String id;

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
        if (id == null)
            throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.ID);
        super.validate();
    }

    @Override
    public GetContactResponse createResponse() {
        return new GetContactResponse();
    }

    /**
     * Id of contact to be queried.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of contact to be queried.
     */
    public void setId(String id) {
        this.id = id;
    }


}
