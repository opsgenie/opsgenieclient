package com.ifountain.opsgenie.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.ConvertFromTimeZone;
import com.ifountain.opsgenie.client.model.ObjectWithTimeZone;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sezgin Kucukkaraaslan
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

    public static void fromMap(Object object, Map map) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(mapper.writeValueAsString(map));
        if (object instanceof ConvertFromTimeZone)
            ((ConvertFromTimeZone) object).setTime();
    }

    public static Map toMap(Object object) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (object instanceof ObjectWithTimeZone && ((ObjectWithTimeZone) object).getObjectTimeZone() != null)
            sdf.setTimeZone(((ObjectWithTimeZone) object).getObjectTimeZone());
        mapper.setDateFormat(sdf);
        return new TreeMap(mapper.convertValue(object, Map.class));
    }

    public static void fromJson(Object object, String json) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(json);
        if (object instanceof ConvertFromTimeZone)
            ((ConvertFromTimeZone) object).setTime();
    }

    public static void fromJsonWithoutParsing(Object object, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readerForUpdating(object).readValue(json);
    }

    public static void fromJson(Object object, JsonParser parser) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(parser);
        if (object instanceof ConvertFromTimeZone)
            ((ConvertFromTimeZone) object).setTime();
    }

    public static void fromJsonWithoutParsing(Object object, JsonParser parser) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        mapper.readerForUpdating(object).readValue(parser);
    }

    public static String toJson(Object object) throws ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        if (object instanceof ObjectWithTimeZone && ((ObjectWithTimeZone) object).getObjectTimeZone() != null)
            sdf.setTimeZone(((ObjectWithTimeZone) object).getObjectTimeZone());
        mapper.setDateFormat(sdf);
        return mapper.writeValueAsString(object);
    }

    public static String toJsonWithoutParsing(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(OpsGenieClientConstants.Common.API_DATE_FORMAT);
        mapper.setDateFormat(sdf);
        return mapper.writeValueAsString(object);
    }
}
