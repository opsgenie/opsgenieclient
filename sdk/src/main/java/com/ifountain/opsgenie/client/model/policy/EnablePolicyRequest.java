package com.ifountain.opsgenie.client.model.policy;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.Map;

/**
 * Container for the parameters to make an enable/disable policy api call.
 *
 * @see com.ifountain.opsgenie.client.IPolicyOpsGenieClient#enablePolicy(com.ifountain.opsgenie.client.model.policy.EnablePolicyRequest)
 */
public class EnablePolicyRequest extends BaseRequest<EnablePolicyResponse> {
    private String name;
    private Boolean enabled = true;


    /**
     * Rest api uri of enable/disable policy operation.
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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Name of policy
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of policy
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public EnablePolicyResponse createResponse() {
        return new EnablePolicyResponse();
    }

    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map json = super.serialize();
        if(getName() != null)
        	json.put(OpsGenieClientConstants.API.NAME, getName());
        return json;
    }
}
