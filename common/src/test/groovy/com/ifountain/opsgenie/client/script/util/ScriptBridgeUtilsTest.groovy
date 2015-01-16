package com.ifountain.opsgenie.client.script.util

import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 8/24/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
class ScriptBridgeUtilsTest {
    @Test
    public void testGetAsString() {
        def params = [prop1: "prop1Value", prop2: null, prop3: ["collection"], prop4: 5]
        params.each { name, value ->
            if (value == null) {
                assertNull(ScriptBridgeUtils.getAsString(params, name))
            } else {
                assertEquals(String.valueOf(value), ScriptBridgeUtils.getAsString(params, name))
            }
        }
    }

    @Test
    public void testGetAsBoolean() {
        def params = [prop1: "True", prop2: null, prop3: false]
        assertTrue(ScriptBridgeUtils.getAsBoolean(params, "prop1"))
        assertNull(ScriptBridgeUtils.getAsBoolean(params, "prop2"))
        assertFalse(ScriptBridgeUtils.getAsBoolean(params, "prop3"))
    }

    @Test
    public void testGetAsList() {
        def params = [prop1: "prop1Value", prop2: null, prop3: ["collection"], prop4: ["array"] as String[]]
        assertEquals([params.prop1], ScriptBridgeUtils.getAsList(params, "prop1"))
        assertNull(ScriptBridgeUtils.getAsList(params, "prop2"))
        assertEquals(params.prop3, ScriptBridgeUtils.getAsList(params, "prop3"))
        assertEquals(Arrays.asList(params.prop4), ScriptBridgeUtils.getAsList(params, "prop4"))
    }

    @Test
    public void testGetAsMap() {
        def params = [prop1: [subProp1: "subPropVal"], prop2: null, prop3: "collection"]
        assertEquals(params.prop1, ScriptBridgeUtils.getAsMap(params, "prop1"))
        assertNull(ScriptBridgeUtils.getAsMap(params, "prop2"))

        try {
            ScriptBridgeUtils.getAsMap(params, "prop3")
            fail("invalid has prop");
        }
        catch (Throwable t) {
            assertEquals("[prop3] paramater should be hash", t.getMessage())
        }
    }
}
