package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.model.beans.DataWithId;

public class BaseResponseWithId extends BaseResponse {
    private DataWithId data;

    public DataWithId getData() {
        return data;
    }

    public void setData(DataWithId data) {
        this.data = data;
    }
}
