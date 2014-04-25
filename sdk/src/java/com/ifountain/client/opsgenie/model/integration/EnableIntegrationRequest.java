package com.ifountain.client.opsgenie.model.integration;

import com.ifountain.client.OpsGenieClientConstants;
import com.ifountain.client.opsgenie.model.BaseGetRequest;
import java.util.Map;

/**
 * Container for the parameters to make an enable/disable integration api call.
 *
 * @see com.ifountain.client.opsgenie.IIntegrationOpsGenieClient#enableIntegration(com.ifountain.client.opsgenie.model.integration.EnableIntegrationRequest)
 */
public class EnableIntegrationRequest extends BaseGetRequest<EnableIntegrationResponse> {
    private String name;
    private Boolean enabled = true;


    /**
     * Rest api uri of enable/disable integration operation.
     */
    @Override
    public String getEndPoint() {
        if(enabled == null || enabled){
            return "/v1/json/integration/enable";
        }
        else{
            return "/v1/json/integration/disable";
        }

    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Name of integration
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of integration
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public EnableIntegrationResponse createResponse() {
        return new EnableIntegrationResponse();
    }

    @Override
    public void _serialize(Map data) {
        if(getName() != null){
            data.put(OpsGenieClientConstants.API.NAME, getName());
        }
    }
}
