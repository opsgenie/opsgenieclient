package com.ifountain.opsgenie.client.model;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 4:30 PM
 */
public class GetAlertRequest extends BaseRequest {
    private String alertId;
    private String alias;

    @Override
    public String getEndPoint() {
        return "/v1/json/alert";
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
