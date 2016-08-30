package com.ifountain.opsgenie.client.model.customer;


import javax.xml.bind.ValidationException;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to make a get heartbeat monitor api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#getHeartbeat(com.ifountain.opsgenie.client.model.customer.GetHeartbeatRequest)
 */
public class GetHeartbeatRequest extends BaseRequest<GetHeartbeatResponse> {
    private String name;
    
    /**
     * check the parameters for validation.
     * It will be overridden by necessary Requests.
     * @throws ValidationException when heartbeat name is null!
     */
    @Override
    public void validate() throws OpsGenieClientValidationException {
    	super.validate();
    	if(name == null)
    		throw OpsGenieClientValidationException.missingMandatoryProperty(OpsGenieClientConstants.API.NAME);
    }

    /**
     * Rest api uri of get heartbeat monitor operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/heartbeat";
    }

    /**
     * Name of heartbeat monitor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of heartbeat monitor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @deprecated Use getName
     */
    public String getSource() {
        return getName();
    }

    /**
     * @deprecated Use setSource
     */
    public void setSource(String source) {
        setName(source);
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public GetHeartbeatResponse createResponse() {
        return new GetHeartbeatResponse();
    }
}
