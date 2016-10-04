package com.ifountain.opsgenie.client.model.beans;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

public class BeanDeserializer extends JsonDeserializer<Bean> {

    public Bean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        System.out.println("Geldi!!");
        System.out.println("Parser=" + jsonParser);
        System.out.println("DeserializationContext=" + deserializationContext);
        //System.out.println("DeserializationContext="+deserializationContext.get);
        return null;
    }

}
