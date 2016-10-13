package com.ifountain.opsgenie.client.model.alert;

import java.util.Map;

/**
 * Container for the parameters to make an add detail call.
 *
 * @author Sezgin Kucukkaraaslan
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addDetails(AddDetailsRequest)
 */
public class AddDetailsRequest extends AddNoteRequest {
    private Map<String, Object> details;

    @Override
	public String getEndPoint() {
        return "/v1/json/alert/details";
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
