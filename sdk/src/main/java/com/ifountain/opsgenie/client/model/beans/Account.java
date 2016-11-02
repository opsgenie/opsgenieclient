package com.ifountain.opsgenie.client.model.beans;

/**
 * Account bean
 */
public class Account extends Bean {
    private Integer userCount;
    private String name;
    private Plan plan;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Account withUserCount(Integer userCount) {
        this.userCount = userCount;
        return this;
    }

    public Account withName(String name) {
        this.name = name;
        return this;
    }

    public Account withPlan(Plan plan) {
        this.plan = plan;
        return this;
    }
}
