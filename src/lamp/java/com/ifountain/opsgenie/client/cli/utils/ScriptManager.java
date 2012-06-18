package com.ifountain.opsgenie.client.cli.utils;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class ScriptManager {
    private static ScriptManager scriptManager = new ScriptManager();
    public static ScriptManager getInstance() {
        return scriptManager;
    }
    public static void destroyInstance() {
        scriptManager = new ScriptManager();
    }

    private File scriptsDir;
    private ScriptManager() {}
    public void initialize(String scriptsDirPath){
        scriptsDir = new File(scriptsDirPath);
    }

    public void registerScriptingLanguage(String name, String engineClass, String[] extensions){
        BSFManager.registerScriptingEngine(name, engineClass, extensions);
    }
    
    public void execute(String scriptFilePath, Map<String, Object> parameters) throws Exception {
        File scriptFile = new File(scriptsDir, scriptFilePath);
        if(!scriptFile.exists()){
            throw new Exception("Script ["+scriptFilePath+"] does not exist");
        }
        String lang = BSFManager.getLangFromFilename(scriptFile.getName());
        try{
            BSFManager manager = new BSFManager();
            Logger logger = Logger.getLogger("script." + scriptFilePath);
            manager.declareBean(OpsGenieClientConstants.ScriptBindings.LOGGER, logger, logger.getClass());
            for(Map.Entry<String, Object> paramEntry:parameters.entrySet()){
                if(paramEntry.getValue() != null){
                    manager.declareBean(paramEntry.getKey(), paramEntry.getValue(), paramEntry.getValue().getClass());
                }
            }
            FileReader fin = new FileReader(scriptFile);
            String scriptText = IOUtils.getStringFromReader(fin);
            fin.close();
            manager.exec(lang, scriptFilePath, 0, 0, scriptText);
        }
        catch (Throwable e){
            throw new Exception("Exception occurred while executing script ["+scriptFilePath+"]. Reason:"+e.toString(), e);
        }

    }
}
