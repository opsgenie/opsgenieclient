package com.ifountain.opsgenie.client.model.customer;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.OpsGenieClientValidationException;
import com.ifountain.opsgenie.client.model.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Container for the parameters to make a set heartbeat configuration api call.
 *
 * @see com.ifountain.opsgenie.client.IOpsGenieClient#setHeartbeatConfig(com.ifountain.opsgenie.client.model.customer.SetHeartbeatConfigRequest)
 */
public class SetHeartbeatConfigRequest extends BaseRequest<SetHeartbeatConfigResponse> {
    private Boolean enabled;
    private String message;
    private List<String> recipients;


    /**
     * Enable state
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enable state
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /*
        * message of heartbeat alert
         */
    public String getMessage() {
        return message;
    }

    /*
    * Sets message of heartbeat alert
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
    * Recipients  of heartbeat alert
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /*
    * Sets recipients  of heartbeat alert
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }


    @Override
    public Map serialize() throws OpsGenieClientValidationException {
        Map data = super.serialize();
        if(message != null){
            data.put(OpsGenieClientConstants.API.MESSAGE, message);
        }
        if(enabled != null){
            data.put(OpsGenieClientConstants.API.ENABLED, enabled);
        }
        if(recipients != null){
            data.put(OpsGenieClientConstants.API.RECIPIENTS, recipients);
        }
        return data;
    }

    /**
     * Rest api uri of set heartbeat configuration operation.
     */
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat/config";
    }

    @Override
    /**
     * @see com.ifountain.opsgenie.client.model.BaseRequest#createResponse()
     */
    public SetHeartbeatConfigResponse createResponse() {
        return new SetHeartbeatConfigResponse();
    }
}
