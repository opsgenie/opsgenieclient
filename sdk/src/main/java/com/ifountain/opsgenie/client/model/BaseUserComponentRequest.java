package com.ifountain.opsgenie.client.model;

public abstract class BaseUserComponentRequest<T extends BaseResponse> extends BaseRequest<T> {
    private String username;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
