package com.ifountain.opsgenie.client.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LampConfig {
    private static LampConfig instance = new LampConfig();
    public static LampConfig getInstance(){
        return instance;
    }

    public static void destroyInstance(){
        if(instance != null){
            instance.destroy();
        }
        instance = new LampConfig();
    }

    private Properties configuration;

    public void destroy(){
    }

    public void init(String configurationPath){
        File configFile = new File(configurationPath);

        // if config file doesn't exist, look in home dir
        if(!configFile.exists())
            configFile = new File("conf/lamp.conf");

        configuration = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(configFile);
            configuration.load(in);
        } catch (Exception ignored) {
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException ignored) {
            }
        }
    }

    public Properties getConfiguration() {
        return configuration;
    }
}
