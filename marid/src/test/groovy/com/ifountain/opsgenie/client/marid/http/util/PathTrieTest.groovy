package com.ifountain.opsgenie.client.marid.http.util

import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 2/20/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
class PathTrieTest {

    @Test
    public void testPath() {
        PathTrie<String> trie = new PathTrie<String>();
        trie.insert("/a/b/c", "walla");
        trie.insert("a/d/g", "kuku");
        trie.insert("x/b/c", "lala");
        trie.insert("a/x/*", "one");
        trie.insert("a/b/*", "two");
        trie.insert("*/*/x", "three");
        trie.insert("{index}/insert/{docId}", "bingo");

        assertEquals(trie.retrieve("a/b/c"), "walla");
        assertEquals(trie.retrieve("a/d/g"), "kuku");
        assertEquals(trie.retrieve("x/b/c"), "lala");
        assertEquals(trie.retrieve("a/x/b"), "one");
        assertEquals(trie.retrieve("a/b/d"), "two");

        assertNull(trie.retrieve("a/b"));
        assertNull(trie.retrieve("a/b/c/d"));
        assertEquals(trie.retrieve("g/t/x"), "three");

        Map<String, String> params = new HashMap<String, String>();
        assertEquals(trie.retrieve("index1/insert/12", params), "bingo");
        assertEquals(params.size(), 2);
        assertEquals(params.get("index"), "index1");
        assertEquals(params.get("docId"), "12");
    }

    @Test
    public void testEmptyPath() {
        PathTrie<String> trie = new PathTrie<String>();
        trie.insert("/", "walla");
        assertEquals(trie.retrieve(""), "walla");
    }

    @Test
    public void testCaseInsensitivity() {
        PathTrie<String> trie = new PathTrie<String>();
        trie.insert("/AnAction", "walla");
        assertEquals("walla", trie.retrieve("anAction"));

        trie.insert("/{index}/AnAction2/{docId}", "walla");
        Map<String, String> params = new HashMap<String, String>();
        assertEquals(trie.retrieve("Index1/AnAction2/T12", params), "walla");
        assertEquals(params.size(), 2);
        assertEquals(params.get("index"), "Index1");
        assertEquals(params.get("docId"), "T12");
    }

    @Test
    public void testDifferentNamesOnDifferentPath() {
        PathTrie<String> trie = new PathTrie<String>();
        trie.insert("/a/{type}", "test1");
        trie.insert("/b/{name}", "test2");

        Map<String, String> params = new HashMap<String, String>();
        assertEquals(trie.retrieve("/a/test", params), "test1");
        assertEquals(params.get("type"), "test");

        params.clear();
        assertEquals(trie.retrieve("/b/testX", params), "test2");
        assertEquals(params.get("name"), "testX");
    }
}
