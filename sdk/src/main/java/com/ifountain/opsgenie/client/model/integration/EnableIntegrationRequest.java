package com.ifountain.opsgenie.client.model.integration;

import com.ifountain.opsgenie.client.model.BaseRequest;


/**
 * Container for the parameters to make an enable/disable integration api call.
 *
 * @see com.ifountain.opsgenie.client.IIntegrationOpsGenieClient#enableIntegration(com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest)
 */
public class EnableIntegrationRequest extends BaseRequest<EnableIntegrationResponse> {
    private String name;
    private Boolean enabled = true;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * Rest api uri of enable/disable integration operation.
     */
    @Override
    public String getEndPoint() {
        if (enabled == null || enabled) {
            return "/v1/json/integration/enable";
        } else {
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
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public EnableIntegrationResponse createResponse() {
        return new EnableIntegrationResponse();
    }

}
