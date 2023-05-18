package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for the parameters to make a get user api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserRequest extends BaseRequest<GetUserResponse, GetUserRequest> {
    private String identifier;

    private String expand;

    public String getIdentifier() { return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getExpand() { return expand; }

    public void setExpand(String expand) { this.expand = expand; }

    /**
     * check the parameters for validation.
     *
     * @throws OpsGenieClientValidationException when id and username both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
        super.validate();
        if (StringUtils.isEmpty(identifier))
            throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.USERNAME, OpsGenieClientConstants.API.ID);
        if (StringUtils.isNotBlank(expand) && !expand.equalsIgnoreCase(OpsGenieClientConstants.API.CONTACT))
            throw OpsGenieClientValidationException.invalidValues(OpsGenieClientConstants.API.EXPAND);
    }

    public Map<String,Object> getRequestParams(){
        Map<String,Object> params = new HashMap<>();
        if(StringUtils.isNotBlank(expand))
            params.put(OpsGenieClientConstants.API.EXPAND,expand);
        return params;
    }

    /**
     * Rest api uri of getting user operation.
     */
    @Override
    public String getEndPoint() {
        return "/v2/users/" + identifier;
    }


    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public GetUserResponse createResponse() {
        return new GetUserResponse();
    }

    public GetUserRequest withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public GetUserRequest withExpand(String expand) {
        this.expand = expand;
        return this;
    }
}
