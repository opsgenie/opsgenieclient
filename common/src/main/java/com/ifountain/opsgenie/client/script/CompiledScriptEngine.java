package com.ifountain.opsgenie.client.script;

import java.io.File;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 11:08 AM
 */
public interface CompiledScriptEngine {
    public void initialize(ClassLoader classLoader, File scriptsDirectory);
    public void runScript(File scriptFile, Map<String, Object> bindings) throws Exception;
}
