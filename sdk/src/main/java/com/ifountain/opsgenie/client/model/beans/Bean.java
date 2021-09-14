package com.ifountain.opsgenie.client.model.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ifountain.opsgenie.client.util.JsonUtils;


import java.text.ParseException;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Bean implements IBean {

    @Override
    @Deprecated
    public Map toMap() {
        try {
            return JsonUtils.toMap(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public void fromMap(Map map) throws ParseException {
        try {
            JsonUtils.fromMap(this, map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), -1);
        }
    }


    public String toString() {
        try {
            return JsonUtils.toJsonWithoutParsing(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
