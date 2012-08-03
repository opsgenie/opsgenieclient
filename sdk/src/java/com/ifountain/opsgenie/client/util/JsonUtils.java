package com.ifountain.opsgenie.client.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version  5/31/12 11:35 AM
 */
public class JsonUtils {
    public static Map parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    public static Map parse(byte[] json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    public static String toJson(Map jsonContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonContent);
    }

    public static byte[] toJsonAsBytes(Map jsonContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(jsonContent);
    }
}
