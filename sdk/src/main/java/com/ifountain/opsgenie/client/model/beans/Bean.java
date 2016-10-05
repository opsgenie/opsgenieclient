package com.ifountain.opsgenie.client.model.beans;

import com.ifountain.opsgenie.client.util.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.text.ParseException;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Bean implements IBean {

    @Override
    public Map toMap() {
        try {
            return JsonUtils.toMap(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void fromMap(Map map) throws ParseException {
        try {
            JsonUtils.fromMap(this, map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParseException(e.getMessage(), -1);
        }
    }


    public String toString() {
        return toMap().toString();
    }

}
