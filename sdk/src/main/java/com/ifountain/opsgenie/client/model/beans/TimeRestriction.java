package com.ifountain.opsgenie.client.model.beans;
import java.util.List;

public class TimeRestriction extends Bean{
    private String type;
    private List<Restriction> restrictions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }
}
