package com.ifountain.opsgenie.client.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/20/12
 * Time: 1:34 PM
 */
public class JSON {

    public static Map parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(json, Map.class);
        if(result == null){
            result = new HashMap();
        }
        return result;
    }

    public static Map parse(byte[] json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(json, Map.class);
        if(result == null){
            result = new HashMap();
        }
        return result;
    }

    public static String toJson(Map jsonContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonContent);
    }

    public static Map toMap(Object object)  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object, Map.class);
    }
}
