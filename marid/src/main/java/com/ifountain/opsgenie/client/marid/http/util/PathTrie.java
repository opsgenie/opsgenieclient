package com.ifountain.opsgenie.client.marid.http.util;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.ifountain.opsgenie.client.marid.http.util.MapBuilder.newMapBuilder;

/**
 * @author kimchy (Shay Banon)
 */
public class PathTrie<T> {

    public static interface Decoder {
        String decode(String value);
    }

    public static final Decoder NO_DECODER = new Decoder() {
        @Override public String decode(String value) {
            return value;
        }
    };

    private final Decoder decoder;
    private final TrieNode<T> root;
    private final char separator;
    private T rootValue;

    public PathTrie() {
        this('/', "*", NO_DECODER);
    }

    public PathTrie(Decoder decoder) {
        this('/', "*", decoder);
    }

    public PathTrie(char separator, String wildcard, Decoder decoder) {
        this.decoder = decoder;
        this.separator = separator;
        root = new TrieNode<T>(new String(new char[]{separator}), null, null, wildcard);
    }

    public class TrieNode<T> {
        private transient String key;
        private transient T value;
        private boolean isWildcard;
        private final String wildcard;

        private transient String namedWildcard;

        private Map<String, TrieNode<T>> children;

        private final TrieNode parent;

        public TrieNode(String key, T value, TrieNode parent, String wildcard) {
            this.key = key.toLowerCase();
            this.wildcard = wildcard;
            this.isWildcard = (key.equals(wildcard));
            this.parent = parent;
            this.value = value;
            this.children = Collections.unmodifiableMap(new HashMap<String, TrieNode<T>>());
            if (isNamedWildcard(key)) {
                namedWildcard = key.substring(key.indexOf('{') + 1, key.indexOf('}'));
            } else {
                namedWildcard = null;
            }
        }

        public void updateKeyWithNamedWildcard(String key) {
            this.key = key;
            namedWildcard = key.substring(key.indexOf('{') + 1, key.indexOf('}'));
        }

        public boolean isWildcard() {
            return isWildcard;
        }

        public synchronized void addChild(TrieNode<T> child) {
            children = newMapBuilder(children).put(child.key, child).immutableMap();
        }

        public TrieNode getChild(String key) {
            return children.get(key);
        }

        public synchronized void insert(String[] path, int index, T value) {
            if (index >= path.length)
                return;

            String token = path[index];
            String key = token.toLowerCase();
            if (isNamedWildcard(token)) {
                key = wildcard;
            }
            TrieNode<T> node = children.get(key);
            if (node == null) {
                if (index == (path.length - 1)) {
                    node = new TrieNode<T>(token, value, this, wildcard);
                } else {
                    node = new TrieNode<T>(token, null, this, wildcard);
                }
                children = newMapBuilder(children).put(key, node).immutableMap();
            } else {
                if (isNamedWildcard(token)) {
                    node.updateKeyWithNamedWildcard(token);
                }

                // in case the target(last) node already exist but without a value
                // than the value should be updated.
                if (index == (path.length - 1)) {
                    assert (node.value == null || node.value == value);
                    if (node.value == null) {
                        node.value = value;
                    }
                }
            }

            node.insert(path, index + 1, value);
        }

        private boolean isNamedWildcard(String key) {
            return key.indexOf('{') != -1 && key.indexOf('}') != -1;
        }

        private String namedWildcard() {
            return namedWildcard;
        }

        private boolean isNamedWildcard() {
            return namedWildcard != null;
        }

        public T retrieve(String[] path, int index, Map<String, String> params) {
            if (index >= path.length)
                return null;

            String token = path[index];
            TrieNode<T> node = children.get(token.toLowerCase());
            boolean usedWildcard = false;
            if (node == null) {
                node = children.get(wildcard);
                if (node == null) {
                    return null;
                } else {
                    usedWildcard = true;
                    if (params != null && node.isNamedWildcard()) {
                        put(params, node.namedWildcard(), token);
                    }
                }
            }

            if (index == (path.length - 1)) {
                return node.value;
            }

            T res = node.retrieve(path, index + 1, params);
            if (res == null && !usedWildcard) {
                node = children.get(wildcard);
                if (node != null) {
                    if (params != null && node.isNamedWildcard()) {
                        put(params, node.namedWildcard(), token);
                    }
                    res = node.retrieve(path, index + 1, params);
                }
            }

            return res;
        }

        private void put(Map<String, String> params, String key, String value) {
            params.put(key, decoder.decode(value));
        }
    }

    public void insert(String path, T value) {
        String[] strings = StringUtils.split(path, separator);
        if (strings.length == 0) {
            rootValue = value;
            return;
        }
        int index = 0;
        // supports initial delimiter.
        if (strings.length > 0 && strings[0].isEmpty()) {
            index = 1;
        }
        root.insert(strings, index, value);
    }

    public T retrieve(String path) {
        return retrieve(path, null);
    }

    public T retrieve(String path, Map<String, String> params) {
        if (path.length() == 0) {
            return rootValue;
        }
        String[] strings = StringUtils.split(path, separator);
        if (strings.length == 0) {
            return rootValue;
        }
        int index = 0;
        // supports initial delimiter.
        if (strings.length > 0 && strings[0].isEmpty()) {
            index = 1;
        }
        return root.retrieve(strings, index, params);
    }
}
