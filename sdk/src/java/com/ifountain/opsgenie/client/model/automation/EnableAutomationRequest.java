package com.ifountain.opsgenie.client.model.automation;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make an acknowledge alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:17 PM
 * @see com.ifountain.opsgenie.client.IAutomationOpsGenieClient#enableAutomation(EnableAutomationRequest)
 */
public class EnableAutomationRequest extends BaseRequest<EnableAutomationResponse> {
    private String id;
    private String name;
    private Boolean enabled = true;
    /**
     * Rest api uri of deleting automation operation.
     */
    @Override
    public String getEndPoint() {
        if(enabled == null || enabled){
            return "/v1/json/automation/enable";
        }
        else{
            return "/v1/json/automation/disable";
        }
    }

    /**
     * State of automation.
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable state of automation.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Id of automation.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of automation.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of automation.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of automation.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#serialize()
     */
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(getId() != null){
            json.put(OpsGenieClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(OpsGenieClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public EnableAutomationResponse createResponse() {
        return new EnableAutomationResponse();
    }
}
