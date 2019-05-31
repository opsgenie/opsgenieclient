package com.ifountain.opsgenie.client.model.alertpolicy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseRequest;

/**
 * Container for the parameters to enable/disable  alert policy.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertPolicyOpsGenieClient#enableAlertPolicy(EnableAlertPolicyRequest)
 */
public class EnableAlertPolicyRequest extends BaseRequest<EnableAlertPolicyResponse, EnableAlertPolicyRequest> {
    private String id;
    private String name;
    @JsonIgnore
    private Boolean enabled = true;

    /**
     * Rest api uri of enable/disable  alert policy operation.
     */
    @Override
    public String getEndPoint() {
        if (enabled == null || enabled) {
            return "/v1/json/alert/policy/enable";
        } else {
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


    public EnableAlertPolicyRequest withId(String id) {
        this.id = id;
        return this;
    }

    public EnableAlertPolicyRequest withName(String name) {
        this.name = name;
        return this;
    }

    public EnableAlertPolicyRequest withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }


}
