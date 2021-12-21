package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse;
import com.ifountain.opsgenie.client.marid.alert.PubnubAlertActionListener;
import com.ifountain.opsgenie.client.marid.alert.PubnubChannelParameters;
import com.ifountain.opsgenie.client.marid.http.HttpController;
import com.ifountain.opsgenie.client.marid.http.HttpProxy;
import com.ifountain.opsgenie.client.marid.http.HttpProxyConfig;
import com.ifountain.opsgenie.client.marid.http.HttpServer;
import com.ifountain.opsgenie.client.script.AsyncScriptManager;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.ifountain.opsgenie.client.util.ManifestUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.*;
import java.util.jar.Manifest;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 7/30/12 10:04 AM
 */
public class Bootstrap {
    private final static Object waitLock = new Object();
    public static final String MARID_SCRIPTS_DIR_SYSTEM_PROPERTY = "marid.scripts.dir";
    public static final String MARID_CONF_DIR_SYSTEM_PROPERTY = "marid.conf.dir";
    public static final String CONF_PATH_SYSTEM_PROPERTY = "marid.conf.path";
    public static final String LOG_PATH_SYSTEM_PROPERTY = "marid.log.conf.path";
    protected Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    private HttpProxy proxy;
    private HttpServer httpServer;
    private HttpServer httpsServer;

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
                    logger.warn(getLogPrefix() + "Shutdown hook received, stopping.");
                    Bootstrap.this.close();
                    logger.warn(getLogPrefix() + "Stopped");
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
        stopHttpServers();
        stopHttpProxy();
        destroyAlertActionExecutor();
        destroyScripting();
        MaridConfig.destroyInstance();
    }

    protected void initialize() throws Exception {
        configureLogger();
        printVersion();
        loadConfiguration();
        getMaridSettings();
        initializeScripting();
        initializeAlertActionExecutor();
        startHttpProxy();
        startHttpServers();
    }

    private void getMaridSettings() throws Exception {
        logger.debug(getLogPrefix() + "Getting Marid settings from OpsGenie server.");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(OpsGenieClientConstants.API.API_KEY, MaridConfig.getInstance().getApiKey());
        try {
            OpsGenieHttpResponse response = MaridConfig.getInstance().getOpsGenieHttpClient().get(MaridConfig.getInstance().getOpsgenieApiUrl() + "/v1/json/marid/settings", parameters);
            if (response.getStatusCode() == HttpStatus.SC_OK) {
                Map maridSettings = JsonUtils.parse(response.getContent());
                MaridConfig.getInstance().putAll(maridSettings);
                logger.info(getLogPrefix() + "Marid settings are successfully loaded from server.");
            } else {
                String responseContent = new String(response.getContent());
                logger.error(getLogPrefix() + "Could not get Marid settings from OpsGenie server. Response: " + responseContent);
                throw new Exception("Could not get Marid settings from OpsGenie server. Response: " + responseContent);
            }
        } catch (Throwable e) {
            logger.warn(getLogPrefix() + "Will start in webhook mode. Reason:"+e.getMessage());
        }
    }

    private void initializeScripting() throws Exception {
        logger.warn(getLogPrefix()+"Initializing Scripting");
        ScriptManager.getInstance().initialize(getScriptsConfigurationFilePath());
        String engineNamesStr = MaridConfig.getInstance().getProperty("script.engines", "").trim();
        if (engineNamesStr.length() != 0) {
            String[] engineNames = engineNamesStr.split(",");
            for (String engineName : engineNames) {
                String classPropName = "script.engine." + engineName + ".class";
                String className = MaridConfig.getInstance().getMandatoryProperty(classPropName);
                String extensionsPropName = "script.engine." + engineName + ".extensions";
                String extensionsStr = MaridConfig.getInstance().getMandatoryProperty(extensionsPropName);
                String[] extensions = extensionsStr.split(",");
                ScriptManager.getInstance().registerScriptingLanguage(engineName, className, extensions);
            }
        }

        logger.warn(getLogPrefix()+"Initializing Asyncronus Scripting");
        long shutdownWaitTime = MaridConfig.getInstance().getLong("async.script.shutdown.wait.time", AsyncScriptManager.DEFAULT_SHUTDOWN_WAIT_TIME);
        int scriptExecutorThreadCount = MaridConfig.getInstance().getInt("async.script.executor.thread.count", AsyncScriptManager.DEFAULT_SCRIPT_EXECUTOR_THREAD_COUNT);
        int scriptExecutorQueueCount = MaridConfig.getInstance().getInt("async.script.executor.queue.count", AsyncScriptManager.DEFAULT_SCRIPT_EXECUTOR_QUEUE_COUNT);
        AsyncScriptManager.getInstance().setShutdownWaitTime(shutdownWaitTime) ;
        AsyncScriptManager.getInstance().initialize(scriptExecutorThreadCount, scriptExecutorQueueCount);
    }

    private void initializeAlertActionExecutor() {
        boolean executeAlertActions = MaridConfig.getInstance().getBoolean("execute.alert.actions", false);
        if (executeAlertActions) {
            logger.warn(getLogPrefix()+"Initializing PubnubAlertActionListener");
            PubnubChannelParameters params = new PubnubChannelParameters();
            params.setChannel(MaridConfig.getInstance().getProperty("pubnub.channel", ""));
            params.setSubscribeKey(MaridConfig.getInstance().getProperty("pubnub.subscribekey", ""));
            params.setCipherKey(MaridConfig.getInstance().getApiKey());
            params.setSslOn(MaridConfig.getInstance().getBoolean("pubnub.ssl.enabled", true));
            boolean isProxyEnabled = MaridConfig.getInstance().getBoolean("http.proxy.enabled", false);
            if(isProxyEnabled){
                params.setProxyEnabled(true);
                params.setProxyHost(MaridConfig.getInstance().getProperty("http.proxy.host"));
                params.setProxyPort(MaridConfig.getInstance().getInt("http.proxy.port", 12222));
                params.setProxyProtocol(MaridConfig.getInstance().getProperty("http.proxy.protocol"));
                params.setProxyUsername(MaridConfig.getInstance().getProperty("http.proxy.username"));
                params.setProxyPassword(MaridConfig.getInstance().getProperty("http.proxy.password"));
            }
            PubnubAlertActionListener.getInstance().initialize(params);
        }
    }

    private void printVersion(){
        Manifest maridManifest = ManifestUtils.loadManifest(Bootstrap.class);
        String version = maridManifest.getMainAttributes().getValue("Implementation-Version");
        if(version == null){
            version = "UNKNOWN";
        }
        logger.warn(getLogPrefix()+"Marid Version:"+ version);
    }
    private void loadConfiguration() throws Exception {
        logger.debug(getLogPrefix() + "Loading configuration.");
        String configurationPath = getConfigurationPath();
        MaridConfig.getInstance().init(configurationPath);
        logger.info(getLogPrefix() + "Configuration loaded.");
    }

    private void startHttpProxy() {
        boolean proxyEnabled = MaridConfig.getInstance().getBoolean("http.proxy.server.enabled", false);
        if (proxyEnabled) {
            logger.warn(getLogPrefix()+"Starting proxy server");
            int port = MaridConfig.getInstance().getInt("http.proxy.server.port", 11111);
            String host = MaridConfig.getInstance().getProperty("http.proxy.server.host", "127.0.0.1");
            String username = MaridConfig.getInstance().getProperty("http.proxy.server.username");
            String password = MaridConfig.getInstance().getProperty("http.proxy.server.password");
            HttpProxyConfig httpProxyConfig = new HttpProxyConfig(port);
            httpProxyConfig.setHost(host);
            httpProxyConfig.setUsername(username);
            httpProxyConfig.setPassword(password);
            httpProxyConfig.setClientProxyConfiguration(MaridConfig.getInstance().getClientConfiguration().getClientProxyConfiguration());
            proxy = new HttpProxy(httpProxyConfig);
            proxy.start();
        }
    }
    private HttpServer createHttpServer(boolean isHttps) throws Exception {
        HttpServer httpServer = null;
        String prefix = isHttps?"https":"http";
        boolean httpServerEnabled = MaridConfig.getInstance().getBoolean(prefix+".server.enabled", false);
        if (httpServerEnabled) {
            String host = MaridConfig.getInstance().getProperty(prefix+".server.host", "127.0.0.1");
            int port =  MaridConfig.getInstance().getInt(prefix+".server.port", isHttps?8443:8080);

            int maxContentLength = MaridConfig.getInstance().getInt(prefix+".server.maxContentLength", 2000000);
            int threadPoolSize = MaridConfig.getInstance().getInt(prefix+".server.threadpool.size", 100);
            int idleConnectionTimeout = MaridConfig.getInstance().getInt(prefix+".server.idle.connection.timeout", 60);
            if(isHttps){
                String keystore = MaridConfig.getInstance().getProperty(prefix+".server.keystore", getConfigurationDirectoryPath()+"/.keystore");
                String keyPassword = MaridConfig.getInstance().getProperty(prefix+".server.keyPassword", "123456");
                httpServer = new HttpServer(host, port, keystore, keyPassword);
            }
            else{
                httpServer = new HttpServer(host, port);
            }
            httpServer.setMaxContentLength(maxContentLength);
            httpServer.setThreadPoolSize(threadPoolSize);
            httpServer.setIdleConnectionTimeout(idleConnectionTimeout);
        }
        return httpServer;
    }

    private void stopHttpProxy() {
        if (proxy != null){
            logger.warn(getLogPrefix()+"Stopping proxy server");
            proxy.stop();
        }
    }
    private void stopHttpServers() {
        if (httpServer != null){
            logger.warn(getLogPrefix()+"Stopping http server");
            httpServer.close();
        }
        if (httpsServer != null){
            logger.warn(getLogPrefix()+"Stopping https server");
            httpsServer.close();
        }
        HttpController.destroyInstance();
    }
    private void startHttpServers() throws Exception {
        httpServer = createHttpServer(false);
        httpsServer = createHttpServer(true);
        if(httpServer != null || httpsServer != null){
            HttpController.registerActions();
        }
        if(httpServer != null){
            logger.warn(getLogPrefix()+"Starting http server");
            httpServer.open();
        }
        if(httpsServer != null){
            logger.warn(getLogPrefix()+"Starting https server");
            httpsServer.open();
        }
    }

    private void destroyAlertActionExecutor() {
        logger.warn(getLogPrefix()+"Destroying PubnubAlertActionListener");
        PubnubAlertActionListener.destroyInstance();
    }

    private void destroyScripting() {
        logger.warn(getLogPrefix()+"Destroying Scripting");
        ScriptManager.destroyInstance();
        logger.warn(getLogPrefix()+"Destroying Asyncrous Scripting");
        AsyncScriptManager.destroyInstance();
    }

    private void configureLogger() {
        File logConfigFile  = new File("conf/log.properties");
    }


    protected String getLogPrefix() {
        return "[Bootstrap]: ";
    }

    private String getLogConfigurationFilePath() {
        String logPath = System.getProperty(LOG_PATH_SYSTEM_PROPERTY);
        if(logPath != null)
            return logPath;

        return new File(getConfigurationDirectoryPath(), "log.properties").getPath();
    }

    private String getScriptsConfigurationFilePath() {
        return System.getProperty(MARID_SCRIPTS_DIR_SYSTEM_PROPERTY);
    }

    private String getConfigurationPath() {
        String confPath = System.getProperty(CONF_PATH_SYSTEM_PROPERTY);
        if(confPath != null)
            return confPath;

        return new File(getConfigurationDirectoryPath(), "marid.conf").getPath();
    }


    private String getConfigurationDirectoryPath() {
        return System.getProperty(MARID_CONF_DIR_SYSTEM_PROPERTY);
    }
}
