package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

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

    public static void fromMap(Object object, Map map) throws JsonGenerationException, JsonMappingException, JsonProcessingException, IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(mapper.writeValueAsString(map));
        if (object instanceof ConvertFromTimeZone)
            ((ConvertFromTimeZone) object).setTime();
    }

    public static Map toMap(Object object) throws JsonGenerationException, JsonMappingException, JsonProcessingException, IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (object instanceof ObjectWithTimeZone && ((ObjectWithTimeZone) object).getObjectTimeZone() != null)
            sdf.setTimeZone(((ObjectWithTimeZone) object).getObjectTimeZone());
        mapper.setDateFormat(sdf);
        return new TreeMap(mapper.convertValue(object, Map.class));
    }

    public static void fromJson(Object object, String json) throws JsonGenerationException, JsonMappingException, JsonProcessingException, IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(json);
        if (object instanceof ConvertFromTimeZone)
            ((ConvertFromTimeZone) object).setTime();
    }

    public static String toJson(Object object) throws ParseException, JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (object instanceof ObjectWithTimeZone && ((ObjectWithTimeZone) object).getObjectTimeZone() != null)
            sdf.setTimeZone(((ObjectWithTimeZone) object).getObjectTimeZone());
        mapper.setDateFormat(sdf);
        return mapper.writeValueAsString(object);
    }
}
