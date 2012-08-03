package com.ifountain.opsgenie.client.script;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 1:21 PM
 */
public abstract class AbstractCompiledScriptEngine implements CompiledScriptEngine {
    protected ClassLoader classLoader;
    protected File scriptsDirectory;
    protected Map<String, ScriptBean> scripts;

    @Override
    public void initialize(ClassLoader classLoader, File scriptsDirectory) {
        this.classLoader = classLoader;
        this.scriptsDirectory = scriptsDirectory;
        scripts = new HashMap<String, ScriptBean>();
    }

    @Override
    public void runScript(File scriptFile, Map<String, Object> bindings) throws Exception {
        ScriptBean script = scripts.get(scriptFile.getName());
        if (script == null || script.getLastModified() < scriptFile.lastModified()) {
            script = compileScript(scriptFile);
            scripts.put(scriptFile.getName(), script);
        }
        doRun(script, bindings);
    }

    protected abstract void doRun(ScriptBean script, Map<String, Object> bindings) throws Exception;

    protected abstract ScriptBean compileScript(File scriptFile) throws Exception;

    protected class ScriptBean {
        private Class scriptClass;
        private String fileName;
        private long lastModified;

        protected ScriptBean(String fileName, Class scriptClass, long lastModified) {
            this.scriptClass = scriptClass;
            this.fileName = fileName;
            this.lastModified = lastModified;
        }

        public Class getScriptClass() {
            return scriptClass;
        }

        public String getFileName() {
            return fileName;
        }

        public long getLastModified() {
            return lastModified;
        }
    }
}

