package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.model.beans.Restriction;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class RestrictionsSeriliazer extends JsonSerializer<List<Restriction>> {

    @Override
    public void serialize(List<Restriction> restrictions, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        if (restrictions != null) {
            ObjectMapper mapper = new ObjectMapper();
            if (restrictions.size() == 1 && (restrictions.get(0).getEndDay() == null
                    && restrictions.get(0).getStartDay() == null)) {
                jsonGenerator.writeRawValue(mapper.writeValueAsString(restrictions.get(0)));
            } else {
                jsonGenerator.writeRawValue(mapper.writeValueAsString(restrictions));
            }
        }
    }

}
