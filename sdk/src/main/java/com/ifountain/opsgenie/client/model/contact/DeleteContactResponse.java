package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseResponse;
/**
 * Represents OpsGenie service response for delete contact request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#deleteContact(DeleteContactRequest)
 */
public class DeleteContactResponse extends BaseResponse {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
