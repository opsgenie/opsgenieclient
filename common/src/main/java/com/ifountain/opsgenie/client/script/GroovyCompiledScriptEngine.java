package com.ifountain.opsgenie.client.script;

import groovy.lang.*;

import java.io.File;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 11:26 AM
 */
public class GroovyCompiledScriptEngine extends AbstractCompiledScriptEngine {
    @Override
    protected void doRun(ScriptBean script, Map<String, Object> bindings) throws Exception {
        GroovyObject scriptObject = (GroovyObject) script.getScriptClass().newInstance();
        for (Map.Entry<String, Object> binding : bindings.entrySet()) {
            if(binding.getValue() != null){
                scriptObject.setProperty(binding.getKey(), binding.getValue());
            }
        }
        scriptObject.invokeMethod("run", new Object[0]);
    }

    @Override
    protected ScriptBean compileScript(File scriptFile) throws Exception {
        GroovyClassLoader scriptClassLoader = new GroovyClassLoader(classLoader);
        scriptClassLoader.addClasspath(scriptsDirectory.getPath());
        Class cls = scriptClassLoader.parseClass(scriptFile);
        if (cls == null) {
            throw new Exception("Cannot load script class " + scriptFile.getPath() + ".");
        }
        ExpandoMetaClass metaClass = getExpandoMetaClass(cls);
        metaClass.initialize();
        return new ScriptBean(scriptFile.getName(), cls, scriptFile.lastModified());
    }

    private ExpandoMetaClass getExpandoMetaClass(Class aClass) {
        MetaClassRegistry registry = GroovySystem.getMetaClassRegistry();
        MetaClass mc = registry.getMetaClass(aClass);
        if (mc instanceof ExpandoMetaClass) {
            return (ExpandoMetaClass) mc;
        } else {
            registry.removeMetaClass(aClass);
            mc = registry.getMetaClass(aClass);
            if (mc instanceof ExpandoMetaClass) {
                return (ExpandoMetaClass) mc;
            } else {
                ExpandoMetaClass emc = new ExpandoMetaClass(aClass, true, true);
                registry.setMetaClass(aClass, emc);
                return emc;
            }
        }
    }
}
