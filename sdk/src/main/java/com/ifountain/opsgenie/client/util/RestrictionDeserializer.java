package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.model.beans.Restriction;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestrictionDeserializer extends JsonDeserializer<List<Restriction>> {

    @Override
    public List<Restriction> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        List<Restriction> restrictions = new ArrayList<Restriction>();
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            Restriction restriction = new Restriction();
            JsonUtils.fromJsonWithoutParsing(restriction, jsonParser);
            restrictions.add(restriction);
        } else {
            List<Map<String, String>> restrictionList = new ArrayList<Map<String, String>>();
            JsonUtils.fromJsonWithoutParsing(restrictionList, jsonParser);
            for (Object o : restrictionList) {
                Restriction restriction = new Restriction();
                JsonUtils.fromJsonWithoutParsing(restriction, JsonUtils.toJsonWithoutParsing(o));
                restrictions.add(restriction);
            }

        }
        return restrictions;
    }

}
