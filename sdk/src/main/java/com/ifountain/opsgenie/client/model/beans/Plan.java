package com.ifountain.opsgenie.client.model.beans;

public class Plan extends Bean {
    private Boolean isYearly;
    private Integer maxUserCount;
    private String name;

    public Boolean getIsYearly() {
        return isYearly;
    }

    public void setIsYearly(Boolean isYearly) {
        this.isYearly = isYearly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
    }
    
    public Plan withIsYearly(Boolean isYearly) {
        this.isYearly = isYearly;
        return this;
    }

    public Plan withMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public Plan withName(String name) {
        this.name = name;
        return this;
    }

}
