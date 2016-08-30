package com.ifountain.opsgenie.client.model.user.forward;

import javax.xml.bind.ValidationException;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get forwarding api call.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#getForwarding(GetForwardingRequest)
 */
public class GetForwardingRequest extends BaseRequest<GetForwardingResponse> {
    private String id;
    private String alias;

    
    /**
     * check the parameters for validation.
     * It will be overridden by necessary Requests.
     * @throws ValidationException when alias and id both null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
    	super.validate();
    	if(id == null && alias == null)
    		throw OpsGenieClientValidationException.missingMultipleMandatoryProperty(OpsGenieClientConstants.API.ALIAS,OpsGenieClientConstants.API.ID);
    }
    
    /**
     * Rest api uri of get forwarding operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/user/forward";
    }

    /**
     * Id of forwarding setting to be returned.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of forwarding setting to be returned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A user defined identifier for the forwarding.
     * Provides ability to assign a known identifier and later use this identifier to get forwarding details.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a user defined identifier for the forwarding.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetForwardingResponse createResponse() {
        return new GetForwardingResponse();
    }
}
