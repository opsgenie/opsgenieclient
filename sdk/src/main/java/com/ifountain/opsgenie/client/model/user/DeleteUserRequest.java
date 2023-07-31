package com.ifountain.opsgenie.client.model.user;


import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Container for the parameters to make a delete user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#deleteUser(DeleteUserRequest)
 */
public class DeleteUserRequest extends BaseRequest<DeleteUserResponse, DeleteUserRequest> {
    private String identifier;

    public String getIdentifier() { return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    /**
     * Rest api uri of deleting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + identifier;
    }

    /**
     * check the parameters for validation. It will be overridden by necessary
     * Requests.
     *
     * @throws OpsGenieClientValidationException when id is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (StringUtils.isEmpty(identifier))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ID, OpsGenieClientConstants.API.USERNAME);
    }

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public DeleteUserResponse createResponse() {
        return new DeleteUserResponse();
    }

    public DeleteUserRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
}
