package com.ifountain.opsgenie.client.model.alert;

import java.util.Map;

/**
 * Container for the parameters to make an add detail request call.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IAlertOpsGenieClient#addDetails(AddDetailsRequest)
 */
public class AddDetailsRequest extends BaseAlertRequestWithNoteAndUser<AddDetailsResponse, AddDetailsRequest> {
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

    public AddDetailsRequest withDetails(Map<String, Object> details) {
        this.details = details;
        return this;
    }

    @Override
    public AddDetailsResponse createResponse() {
        return new AddDetailsResponse();
    }

}
