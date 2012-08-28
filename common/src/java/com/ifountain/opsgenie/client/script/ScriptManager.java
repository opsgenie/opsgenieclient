package com.ifountain.opsgenie.client.script;

import groovy.lang.GroovyClassLoader;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 10:34 AM
 */
public class ScriptManager {

    private static ScriptManager scriptManager = new ScriptManager();

    public static ScriptManager getInstance() {
        return scriptManager;
    }

    public static void destroyInstance() {
        scriptManager = new ScriptManager();
    }

    private File scriptsDir;
    private GroovyClassLoader classLoader;
    private Map<String, CompiledScriptEngine> compiledScriptEngines;
    private boolean initialized = false;

    private ScriptManager() {
    }

    public void initialize(String scriptsDirPath) {
        scriptsDir = new File(scriptsDirPath);
        compiledScriptEngines = new HashMap<String, CompiledScriptEngine>();
        classLoader = new GroovyClassLoader();
        classLoader.addClasspath(scriptsDir.getPath());

        GroovyCompiledScriptEngine groovyScriptEngine = new GroovyCompiledScriptEngine();
        groovyScriptEngine.initialize(classLoader, scriptsDir);
        compiledScriptEngines.put("groovy", groovyScriptEngine);
        initialized = true;
    }

    public void runScript(String scriptPath, Map<String, Object> bindings) throws Exception {
        File scriptFile = new File(scriptsDir, scriptPath);
        if (!scriptFile.exists()) {
            throw new Exception("Script [" + scriptPath + "] does not exist.");
        }
        Logger logger = Logger.getLogger("script." + scriptPath);
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER, logger);
        String lang = BSFManager.getLangFromFilename(scriptFile.getName());
        FileReader fin = null;
        try {
            if (compiledScriptEngines.containsKey(lang)) {
                CompiledScriptEngine scriptEngine = compiledScriptEngines.get(lang);
                scriptEngine.runScript(scriptFile, bindings);
            } else {
                BSFManager bsfManager = createBsfManager();
                for (Map.Entry<String, Object> paramEntry : bindings.entrySet()) {
                    if (paramEntry.getValue() != null) {
                        bsfManager.declareBean(paramEntry.getKey(), paramEntry.getValue(), paramEntry.getValue().getClass());
                    }
                }
                fin = new FileReader(scriptFile);
                String scriptText = IOUtils.getStringFromReader(fin);
                bsfManager.exec(lang, scriptPath, 0, 0, scriptText);
            }
        } catch (Exception e) {
            Throwable cause = e;
            if (e instanceof BSFException && ((BSFException) e).getTargetException() != null) {
                cause = ((BSFException) e).getTargetException();
            }
            Exception ex = new Exception("Exception occurred while executing script [" + scriptPath + "]. Reason: " + cause.getMessage(), cause);
            logger.warn(ex.getMessage(), cause);
            throw ex;
        } finally {
            if (fin != null) fin.close();
        }
    }

    private BSFManager createBsfManager() {
        BSFManager manager = new BSFManager();
        manager.setClassLoader(classLoader);
        return manager;
    }

    public void registerScriptingLanguage(String lang, String className, String[] extensions) {
        BSFManager.registerScriptingEngine(lang, className, extensions);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public File getScriptsDirectory() {
        return scriptsDir;
    }
}
