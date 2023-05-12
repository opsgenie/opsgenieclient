package com.ifountain.opsgenie.client.model;

public abstract class BaseUserRequest<T extends BaseResponse, K extends BaseUserRequest> extends BaseRequest<T, K> {
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

    public K withUsername(String username) {
        this.username = username;
        return (K) this;
    }

    public K withUserId(String userId) {
        this.userId = userId;
        return (K) this;
    }
}
