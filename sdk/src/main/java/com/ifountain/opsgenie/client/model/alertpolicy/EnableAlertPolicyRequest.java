package com.ifountain.opsgenie.client.model.alertpolicy;

import com.ifountain.opsgenie.client.model.BaseRequest;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Container for the parameters to enable/disable  alert policy.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertPolicyOpsGenieClient#enableAlertPolicy(EnableAlertPolicyRequest)
 */
public class EnableAlertPolicyRequest extends BaseRequest<EnableAlertPolicyResponse> {
    private String id;
    private String name;
    @JsonIgnore
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
     * Sets enable state of  alert policy.
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

    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    @Override
    public EnableAlertPolicyResponse createResponse() {
        return new EnableAlertPolicyResponse();
    }
}
