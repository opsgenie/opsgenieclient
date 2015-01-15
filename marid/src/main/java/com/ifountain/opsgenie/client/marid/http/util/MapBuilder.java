package com.ifountain.opsgenie.client.marid.http.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kimchy (Shay Banon)
 */
public class MapBuilder<K, V> {

    public static <K, V> MapBuilder<K, V> newMapBuilder() {
        return new MapBuilder<K, V>();
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(Map<K, V> map) {
        return new MapBuilder<K, V>().putAll(map);
    }

    private Map<K, V> map = newHashMap();

    public MapBuilder() {
        this.map = newHashMap();
    }

    private Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.map.putAll(map);
        return this;
    }

    public MapBuilder<K, V> put(K key, V value) {
        this.map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> remove(K key) {
        this.map.remove(key);
        return this;
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public Map<K, V> map() {
        return this.map;
    }

    public Map<K, V> immutableMap() {
        return Collections.unmodifiableMap(new HashMap<K, V>(map));
    }
}
