package com.ifountain.opsgenie.client.marid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tuba Ozturk
 * @version 19.10.15 09:20
 */
public class MemoryStore {
    private static Map<String, Object>  memoryStore = new HashMap<String, Object>();

    public static void store (String key, Object value){
        memoryStore.put(key, value);
    }

    public static Object lookup (String key){
        return memoryStore.get(key);
    }

    public static void remove (String key){
        memoryStore.remove(key);
    }

    public static void reset (){
        memoryStore = new HashMap<String, Object>();
    }
}
