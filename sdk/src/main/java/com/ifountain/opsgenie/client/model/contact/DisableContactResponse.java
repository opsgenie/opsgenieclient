package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseResponseWithId;

/**
 * Represents OpsGenie service response for disable contact request.
 *
 * @author Mehmet Mustafa Demir
 * @see com.ifountain.opsgenie.client.IContactOpsGenieClient#disableContact(DisableContactRequest)
 */
public class DisableContactResponse extends BaseResponseWithId {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
