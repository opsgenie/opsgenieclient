package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.util.Strings;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Container for the parameters to remove details from alert api call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#removeDetails(RemoveDetailsRequest)
 */
public class RemoveDetailsRequest extends AddNoteRequest {

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

    @Override
    public String getEndPoint() {
        return "/v1/json/alert/details";
    }

    @Override
    public RemoveDetailsResponse createResponse() {
        return new RemoveDetailsResponse();
    }

}
