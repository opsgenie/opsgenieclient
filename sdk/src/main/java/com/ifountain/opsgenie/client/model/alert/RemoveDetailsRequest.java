package com.ifountain.opsgenie.client.model.alert;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.ifountain.opsgenie.client.util.Strings;

public class RemoveDetailsRequest extends BaseAlertRequestWithSource<RemoveDetailsResponse> {

    private List<String> keys;
    private String user;
    private String note;
    /**
     * The user who is performing the add note operation.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who is performing the add note operation.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Additional alert note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional alert note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Keys to be deleted in String mode
     */
	@JsonProperty("keys")
    public String getKeysString() {
	    if(getKeys() != null)
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
