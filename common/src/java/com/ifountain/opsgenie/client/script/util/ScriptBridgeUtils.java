package com.ifountain.opsgenie.client.script.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 6/18/12
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptBridgeUtils {
    public static String getAsString(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        return String.valueOf(result);
    }

    public static List<String> getAsList(Map params, String propName){
        Object result = params.get(propName);
        if(result == null) return null;
        List<String> elements = new ArrayList<String>();
        if(result.getClass().isArray()){
            int length = Array.getLength(result);
            for(int i=0; i < length; i++){
                elements.add(String.valueOf(Array.get(result, i)));
            }
        }
        if(result instanceof Collection){
            for(Object collElement:(Collection)result){
                if(collElement == null){
                    elements.add(null);
                }
                else{
                    elements.add(String.valueOf(collElement));
                }
            }
        }
        else{
            elements.add(String.valueOf(result));
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
