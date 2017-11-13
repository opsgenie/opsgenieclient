package com.ifountain.opsgenie.client.script.util

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
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

    @Test
    public void testGetAsDateTime() {
        DateTimeZone.setDefault(DateTimeZone.forID("Europe/Moscow"))
        def dates = []
        def params = [date: new Date("11/24/2016 11:45")]
        dates.add(ScriptBridgeUtils.getAsDateTime(params, "date"))
        params = [date: "2016-11-24 11:45"]
        dates.add(ScriptBridgeUtils.getAsDateTime(params, "date"))
        params = [date: "2016-11-24T11:45:00.000+03:00"]
        dates.add(ScriptBridgeUtils.getAsDateTime(params, "date"))
        params = [date: 1479977100000]
        dates.add(ScriptBridgeUtils.getAsDateTime(params, "date"))
        for(int i=1 ;i<dates.size();i++){
            assertEquals(dates.get(i).toString(), dates.get(i-1).toString())
        }
    }
}
