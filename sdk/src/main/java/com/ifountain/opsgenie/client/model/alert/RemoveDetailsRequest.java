package com.ifountain.opsgenie.client.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Container for the parameters to remove details from alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#removeDetails(RemoveDetailsRequest)
 */
public class RemoveDetailsRequest extends BaseAlertRequestWithParameters<RemoveDetailsResponse, RemoveDetailsRequest> {

    private List<String> keys;

    /**
     * Keys to be deleted in String mode
     */
    @JsonProperty("keys")
    public String getKeysString() {
        if (getKeys() != null)
            return Strings.join(getKeys(), ",");
        return null;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public RemoveDetailsRequest withKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    @Override
    public String getEndPoint() {
        return "/v1/json/alert/details";
    }

    @Override
    public RemoveDetailsResponse createResponse() {
        return new RemoveDetailsResponse();
    }

}
