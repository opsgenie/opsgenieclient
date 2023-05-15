package com.ifountain.opsgenie.client.model.beans;

import java.util.List;
import java.util.Map;

public class Details extends Bean{
    private Map<String, List<String>> detailData;

    public Map<String, List<String>> getDetailData() {
        return detailData;
    }

    public void setDetailData(Map<String, List<String>> detailData) {
        this.detailData = detailData;
    }

    public Details withDetailData(Map<String, List<String>> detailData) {
        this.detailData = detailData;
        return this;
    }
}
