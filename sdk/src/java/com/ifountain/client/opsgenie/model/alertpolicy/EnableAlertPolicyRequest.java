package com.ifountain.client.opsgenie.model.alertpolicy;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.ClientValidationException;
import com.ifountain.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to enable/disable  alert policy.
 *
 * @version 11/26/12 4:17 PM
 * @see com.ifountain.client.opsgenie.IAlertPolicyOpsGenieClient#enableAlertPolicy(EnableAlertPolicyRequest)
 */
public class EnableAlertPolicyRequest extends BaseRequest<EnableAlertPolicyResponse> {
    private String id;
    private String name;
    private Boolean enabled = true;
    /**
     * Rest api uri of enable/disable  alert policy operation.
     */
    @Override
    public String getEndPoint() {
        if(enabled == null || enabled){
            return "/v1/json/alert/policy/enable";
        }
        else{
            return "/v1/json/alert/policy/disable";
        }
    }

    /**
     * State of alert policy.
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the state of  alert policy.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Id of  alert policy.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of  alert policy.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name of  alert policy.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of alert policy.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#serialize()
     */
    public Map<String,Object> serialize() throws ClientValidationException {
        Map<String,Object> json = super.serialize();
        if(getId() != null){
            json.put(ClientConstants.API.ID, getId());
        }
        if(getName() != null){
            json.put(ClientConstants.API.NAME, getName());
        }
        return json;
    }

    @Override
    /**
     * @see com.ifountain.client.model.BaseRequest#createResponse()
     */
    public EnableAlertPolicyResponse createResponse() {
        return new EnableAlertPolicyResponse();
    }
}
