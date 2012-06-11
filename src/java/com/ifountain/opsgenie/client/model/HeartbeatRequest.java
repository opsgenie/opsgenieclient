package com.ifountain.opsgenie.client.model;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 3:06 PM
 */
public class HeartbeatRequest extends BaseRequest {
    @Override
    public String getEndPoint() {
        return "/v1/json/customer/heartbeat";
    }
}
