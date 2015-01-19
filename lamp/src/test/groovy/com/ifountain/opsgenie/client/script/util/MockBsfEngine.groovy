package com.ifountain.opsgenie.client.script.util

import org.apache.bsf.util.BSFEngineImpl
import com.ifountain.opsgenie.client.script.ScriptManagerTest

public class MockBsfEngine extends BSFEngineImpl{
    @Override
    Object call(Object o, String s, Object[] objects) {
        ScriptManagerTest.addMessage("run1")
        return null
    }

    @Override
    Object eval(String s, int i, int i1, Object o) {
        ScriptManagerTest.addMessage("run2")
        return null
    }
}