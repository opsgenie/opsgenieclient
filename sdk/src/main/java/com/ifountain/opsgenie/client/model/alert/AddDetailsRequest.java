package com.ifountain.opsgenie.client.model.alert;

import java.util.Map;


public class AddDetailsRequest extends BaseAlertRequestWithSource<AddDetailsResponse> {
    private Map<String, Object> details;
    private String user;
    private String note;
	@Override
	public String getEndPoint() {
        return "/v1/json/alert/details";
	}


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

	public Map<String, Object> getDetails() {
		return details;
	}


	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}


	@Override
	public AddDetailsResponse createResponse() {
		return new AddDetailsResponse();
	}

}
