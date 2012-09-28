package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;
import com.ifountain.opsgenie.client.util.ClientConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/9/12 2:44 PM
 */
public class MaridConfig {
    private  String opsgenieApiUrl;
    private  String customerKey;
    private  String maridKey;
    private  OpsGenieHttpClient opsGenieHttpClient;
    private  IOpsGenieClient opsGenieClient;
    private Properties configuration;

    private static MaridConfig instance = new MaridConfig();
    public static MaridConfig getInstance(){
        return instance;
    }

    public static void destroyInstance(){
        if(instance != null){
            instance.destroy();
        }
        instance = new MaridConfig();
    }

    public void destroy(){
        if(opsGenieClient != null){
            opsGenieClient.close();
            opsGenieClient = null;
        }
        if(opsGenieHttpClient != null){
            opsGenieHttpClient.close();
            opsGenieHttpClient = null;
        }
    }

    public void init(String configurationPath) throws Exception {
        if(configuration != null) throw new Exception("Already initialized");
        configuration = new Properties();
        File configFile = new File(configurationPath);
        if (configFile.exists()) {
            FileInputStream in = new FileInputStream(configFile);
            try {
                configuration.load(in);
            } finally {
                in.close();
            }
        } else {
            throw new FileNotFoundException("trial2 Configuration file " + configurationPath + " does not exist");
        }
        opsgenieApiUrl = configuration.getProperty("opsgenie.api.url", "https://api.opsgenie.com");
        customerKey = configuration.getProperty("customerKey");
        maridKey = configuration.getProperty("maridKey");
        initOpsgenieClient();
    }

    private void initOpsgenieClient(){
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSocketTimeout(getInt("opsgenie.connection.sockettimeout", 30)*1000);
        clientConfiguration.setConnectionTimeout(getInt("opsgenie.connection.timeout", 30)*1000);
        clientConfiguration.setMaxConnections(getInt("opsgenie.connection.maxConnectionCount", 50));
        if(getBoolean("http.proxy.enabled", false)){
            clientConfiguration.setProxyHost(getProperty("http.proxy.host"));
            clientConfiguration.setProxyPort(getInt("http.proxy.port", 0));
            clientConfiguration.setProxyUsername(getProperty("http.proxy.username"));
            clientConfiguration.setProxyPassword(getProperty("http.proxy.password"));
            clientConfiguration.setProxyProtocol(getProperty("http.proxy.protocol"));
        }

        clientConfiguration.setUserAgent(ClientConfiguration.createUserAgentFromManifest(MaridConfig.class));
        if(configuration.getProperty("opsgenie.connection.socketReceiveBufferSizeHint") != null){
            clientConfiguration.setSocketReceiveBufferSizeHint(getInt("opsgenie.connection.socketReceiveBufferSizeHint", -1));
        }
        if(configuration.getProperty("opsgenie.connection.socketSendBufferSizeHint") != null){
            clientConfiguration.setSocketSendBufferSizeHint(getInt("opsgenie.connection.socketSendBufferSizeHint", -1));
        }
        opsGenieHttpClient = new OpsGenieHttpClient(clientConfiguration);
        opsGenieClient = new OpsGenieClient(opsGenieHttpClient);
        opsGenieClient.setRootUri(getOpsgenieApiUrl());
    }

    public String getOpsgenieApiUrl() {
        return opsgenieApiUrl;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public String getMaridKey() {
        return maridKey;
    }

    public OpsGenieHttpClient getOpsGenieHttpClient() {
        return opsGenieHttpClient;
    }

    public IOpsGenieClient getOpsGenieClient() {
        return opsGenieClient;
    }

    public Properties getConfiguration() {
        return configuration;
    }

    public void putAll(Map settings){
        configuration.putAll(settings);
    }

    public String getProperty(String key){
        if(configuration != null){
            return configuration.getProperty(key);
        }
        throw new RuntimeException("Not initialized");
    }

    public void setOpsgenieApiUrl(String opsgenieApiUrl) {
        this.opsgenieApiUrl = opsgenieApiUrl;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public void setMaridKey(String maridKey) {
        this.maridKey = maridKey;
    }

    public void setOpsGenieHttpClient(OpsGenieHttpClient opsGenieHttpClient) {
        this.opsGenieHttpClient = opsGenieHttpClient;
    }

    public void setOpsGenieClient(IOpsGenieClient opsGenieClient) {
        this.opsGenieClient = opsGenieClient;
    }

    public void setConfiguration(Properties configuration) {
        this.configuration = configuration;
    }

    public String getProperty(String key, String defaultValue){
        if(configuration != null){
            return configuration.getProperty(key, defaultValue);
        }
        throw new RuntimeException("Not initialized");
    }
    
    public String getMandatoryProperty(String key){
        String value = getProperty(key);
        if(value == null || value.length() == 0){
            throw new RuntimeException("Missing mandatory config property "+key);
        }
        return value;
    }

    public int getInt(String key, int defaultValue){
        String value = getProperty(key);
        if(value == null || value.length() == 0){
            return defaultValue;
        }
        try{
            return Integer.parseInt(value);
        }catch (Throwable e){
            throw new RuntimeException("Invalid int value ["+value+"] for ["+key+"]");
        }
    }

    public boolean getBoolean(String key, boolean defaultValue){
        String value = getProperty(key);
        if(value == null || value.length() == 0){
            return defaultValue;
        }
        try{
            return Boolean.parseBoolean(value);
        }catch (Throwable e){
            throw new RuntimeException("Invalid boolean value ["+value+"] for ["+key+"]");
        }
    }
}
