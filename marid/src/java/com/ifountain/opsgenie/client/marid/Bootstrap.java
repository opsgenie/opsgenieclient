package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.marid.http.HttpProxy;
import com.ifountain.opsgenie.client.script.ScriptManager;
import org.apache.log4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 7/30/12 10:04 AM
 */
public class Bootstrap {
    private final static Object waitLock = new Object();
    protected Logger logger = Logger.getLogger(Bootstrap.class);
    private HttpProxy proxy;
    Properties configuration = new Properties();

    public static void main(String[] args) throws Exception {
        Bootstrap marid = new Bootstrap();
        marid.start();
    }

    public void start() throws Exception {
        try {
            initialize();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    synchronized (waitLock) {
                        waitLock.notifyAll();
                    }
                    logger.warn(getLogPrefix() + "Shutdown hook received, started closing.");
                    Bootstrap.this.close();
                }

            });
            logger.warn(getLogPrefix() + "Started");
            synchronized (waitLock) {
                waitLock.wait();
            }
        } catch (Exception e) {
            logger.warn(getLogPrefix() + "Exception occurred while initializing.", e);
            throw e;
        }
    }

    public void close() {
        stopHttpProxy();
    }

    protected void initialize() throws Exception {
        configureLogger();
        loadConfiguration();
        initializeScripting();
        startHttpProxy();
    }

    private void initializeScripting() throws Exception {
        ScriptManager.getInstance().initialize(getBaseDir() + "/scripts");
        String engineNamesStr = configuration.getProperty("script.engines", "").trim();
        if (engineNamesStr.length() != 0) {
            String[] engineNames = engineNamesStr.split(",");
            for (String engineName : engineNames) {
                String classPropName = "script.engine." + engineName + ".class";
                String className = configuration.getProperty(classPropName);
                if (className == null) {
                    throw new Exception("Script engine should be configured properly. Missing [" + classPropName + "]");
                }
                String extensionsPropName = "script.engine." + engineName + ".extensions";
                String extensionsStr = configuration.getProperty(extensionsPropName);
                if (extensionsStr == null) {
                    throw new Exception("Script engine should be configured properly. Missing [" + extensionsPropName + "]");
                }
                String[] extensions = extensionsStr.split(",");
                ScriptManager.getInstance().registerScriptingLanguage(engineName, className, extensions);
            }
        }
    }

    private void loadConfiguration() throws IOException {
        logger.debug(getLogPrefix() + "Loading configuration.");
        String configurationPath = getConfigurationPath();
        File configFile = new File(configurationPath);
        if (configFile.exists()) {
            FileInputStream in = new FileInputStream(configFile);
            try {
                configuration.load(in);
            } finally {
                in.close();
            }
        } else {
            throw new FileNotFoundException("Configuration file " + configurationPath + " does not exist");
        }
        logger.info(getLogPrefix() + "Configuration loaded.");
    }


    private void startHttpProxy() {
        boolean proxyEnabled = Boolean.parseBoolean(configuration.getProperty("http.proxy.enabled", "false"));
        if (proxyEnabled) {
            int port = Integer.parseInt(configuration.getProperty("http.proxy.port", "11111"));
            String username = configuration.getProperty("http.proxy.username");
            String password = configuration.getProperty("http.proxy.password");
            proxy = new HttpProxy(port, username, password);
            proxy.start();
        }
    }

    private void stopHttpProxy() {
        if (proxy != null) proxy.stop();
    }

    private void configureLogger() {
        File logConfigFile = new File(getLogConfigurationFilePath());
        if (logConfigFile.exists()) {
            Enumeration currentCategories = LogManager.getCurrentLoggers();
            Map<Category, List<Appender>> allAppenders = new HashMap<Category, List<Appender>>();
            while (currentCategories.hasMoreElements()) {
                List<Appender> categoryAppenders = new ArrayList<Appender>();
                Category category = (Category) currentCategories.nextElement();
                Enumeration appenders = category.getAllAppenders();
                while (appenders.hasMoreElements()) {
                    categoryAppenders.add((Appender) appenders.nextElement());
                }
                allAppenders.put(category, categoryAppenders);
            }
            PropertyConfigurator.configure(logConfigFile.getPath());
            for (Map.Entry<Category, List<Appender>> appenders : allAppenders.entrySet()) {
                for (Appender appender : appenders.getValue()) {
                    appenders.getKey().removeAppender(appender);
                }
            }
            logger.info(getLogPrefix() + "Log configuration is loaded.");
        } else {
            logger.warn(getLogPrefix() + "No log configuration file exists.");
        }
    }


    protected String getLogPrefix() {
        return "[Bootstrap]: ";
    }

    private String getLogConfigurationFilePath() {
        return getConfigurationDirectoryPath() + "/log.properties";
    }

    private String getConfigurationPath() {
        return getConfigurationDirectoryPath() + "/marid.conf";
    }

    private String getBaseDir() {
        return System.getProperty("maridhome");
    }

    private String getConfigurationDirectoryPath() {
        return getBaseDir() + "/conf";
    }
}
