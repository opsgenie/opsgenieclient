package com.ifountain.opsgenie.client.model;

import com.ifountain.opsgenie.client.model.beans.BaseDataObj;

public class BaseResponseWithId extends BaseResponse {
    private BaseDataObj data;

    public BaseDataObj getData() {
        return data;
    }

    public void setData(BaseDataObj data) {
        this.data = data;
    }
}
