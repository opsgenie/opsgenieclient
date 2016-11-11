package com.ifountain.opsgenie.client.model.contact;

import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.BaseUserRequest;

/**
 * @author Mehmet Mustafa Demir
 */
public abstract class BaseContactRequestWithId<T extends BaseResponse, K extends BaseContactRequestWithId> extends BaseUserRequest<T, K> {
    private String id;

    /**
     * Id of contact to be deleted.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id of contact to be deleted.
     */
    public void setId(String id) {
        this.id = id;
    }

    public K withId(String id) {
        this.id = id;
        return (K) this;
    }
}
