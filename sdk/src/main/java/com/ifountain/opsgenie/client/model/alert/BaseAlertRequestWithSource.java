package com.ifountain.opsgenie.client.model.alert;

import com.ifountain.opsgenie.client.model.BaseResponse;

public abstract class BaseAlertRequestWithSource<T extends BaseResponse, K extends BaseAlertRequestWithSource> extends BaseAlertRequestWithId<T, K> {
    private String source;

    /**
     * The source of action.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of action.
     */
    public void setSource(String source) {
        this.source = source;
    }


    public K withSource(String source) {
        this.source = source;
        return (K) this;
    }
}
