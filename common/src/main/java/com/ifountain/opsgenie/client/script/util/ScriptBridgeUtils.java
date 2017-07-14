package com.ifountain.opsgenie.client.script.util;

import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.*;

public class ScriptBridgeUtils {
    public static String getAsString(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        return String.valueOf(result);
    }
    public static TimeZone getAsTimeZone(Map params, String propName){
        if(params.containsKey(propName)){
            Object timezoneObj = params.get(propName);
            if(timezoneObj instanceof TimeZone){
                return (TimeZone) timezoneObj;
            }
            else{
                return TimeZone.getTimeZone(String.valueOf(timezoneObj));
            }
        }
        return null;
    }
    public static Date getAsDate(Map params, String propName){
        if(params.containsKey(propName)){
            Object dateObj = params.get(propName);
            if(dateObj instanceof Date){
                return (Date) dateObj;
            }
            else{
                return new Date(getAsLong(params, propName));
            }
        }
        return null;
    }

    public static DateTime getAsDateTime(Map params, String propName){
        if(params.containsKey(propName)){
            Object dateObj = params.get(propName);
            if(dateObj instanceof Date){
                return new DateTime(dateObj);
            }
            else{
                return new DateTime(getAsLong(params, propName));
            }
        }
        return null;
    }

    public static Long getAsLong(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        if(result instanceof Number) return ((Number)result).longValue();
        return Long.parseLong(String.valueOf(result));
    }
    public static Boolean getAsBoolean(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        if(result instanceof Boolean) return ((Boolean)result);
        return new Boolean(String.valueOf(result));
    }
    public static Integer getAsInt(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        if(result instanceof Number) return ((Number)result).intValue();
        return Integer.parseInt(String.valueOf(result));
    }

    public static List<String> getAsStringList(Map params, String propName){
        List items = getAsList(params, propName);
        if(items == null) return null;
        List<String> elements = new ArrayList<String>();
        for(Object listItem:items){
            if(listItem == null){
                elements.add(null);
            }
            else{
                elements.add(String.valueOf(listItem));
            }
        }
        return elements;
    }

    public static List getAsList(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        List elements = new ArrayList();
        if(result.getClass().isArray()){
            int length = Array.getLength(result);
            for(int i=0; i < length; i++){
                elements.add(Array.get(result, i));
            }
        }
        else if(result instanceof Collection){
            elements.addAll((Collection<? extends String>) result);
        }
        else{
            elements.add(result);
        }
        return elements;
    }
    public static Map<String, String> getAsMap(Map params, String propName) throws Exception {
        Object result = params.get(propName);
        if(result == null) return null;
        if(result instanceof Map){
            return (Map<String, String>) result;
        }
        else{
            throw new Exception("["+propName+"] paramater should be hash");
        }
    }
}
