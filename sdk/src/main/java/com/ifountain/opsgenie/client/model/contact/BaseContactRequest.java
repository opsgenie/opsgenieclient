package com.ifountain.opsgenie.client.model.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifountain.opsgenie.client.model.BaseRequest;
import com.ifountain.opsgenie.client.model.BaseResponse;

/**
 * @author Mehmet Mustafa Demir
 */
public abstract class BaseContactRequest<T extends BaseResponse, K extends BaseContactRequest> extends BaseRequest<T, K> {

    @JsonIgnore
    private String userIdentifier;

    @JsonIgnore
    private String contactId;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getUserIdentifier() { return userIdentifier; }

    public void setUserIdentifier(String userIdentifier) { this.userIdentifier = userIdentifier; }

    public K withContactId(String contactId) {
        this.contactId = contactId;
        return (K) this;
    }

    public K withUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
        return (K) this;
    }
}
