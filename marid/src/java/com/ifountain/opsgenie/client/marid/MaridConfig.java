package com.ifountain.opsgenie.client.marid;

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
    private  String opsgenieUrl;
    private  String opsgenieApiUrl;
    private  String customerKey;
    private  String maridKey;
    private  OpsGenieHttpClient opsGenieHttpClient;
    private  OpsGenieClient opsGenieClient;
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
            throw new FileNotFoundException("Configuration file " + configurationPath + " does not exist");
        }
        opsgenieUrl = configuration.getProperty("opsgenie.url", "https://opsgenie.com");
        opsgenieApiUrl = configuration.getProperty("opsgenie.api.url", "https://api.opsgenie.com");
        customerKey = configuration.getProperty("customerKey");
        maridKey = configuration.getProperty("maridKey");
        initOpsgenieClient();
    }

    private void initOpsgenieClient(){
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        int connectionTimeout = Integer.parseInt(configuration.getProperty("opsgenie.connection.timeout", "30"))*1000;
        int socketTimeout = Integer.parseInt(configuration.getProperty("opsgenie.connection.sockettimeout", "30"))*1000;
        int maxConnectionCount = Integer.parseInt(configuration.getProperty("opsgenie.connection.maxConnectionCount", "50"));
        if(configuration.getProperty("opsgenie.connection.socketReceiveBufferSizeHint") != null){
            int socketReceiveBufferSizeHint = Integer.parseInt(configuration.getProperty("opsgenie.connection.socketReceiveBufferSizeHint"));
            clientConfiguration.setSocketReceiveBufferSizeHint(socketReceiveBufferSizeHint);
        }
        if(configuration.getProperty("opsgenie.connection.socketSendBufferSizeHint") != null){
            int socketSendBufferSizeHint = Integer.parseInt(configuration.getProperty("opsgenie.connection.socketSendBufferSizeHint"));
            clientConfiguration.setSocketSendBufferSizeHint(socketSendBufferSizeHint);
        }
        clientConfiguration.setSocketTimeout(socketTimeout);
        clientConfiguration.setConnectionTimeout(connectionTimeout);
        clientConfiguration.setMaxConnections(maxConnectionCount);
        opsGenieHttpClient = new OpsGenieHttpClient(clientConfiguration);
        opsGenieClient = new OpsGenieClient(opsGenieHttpClient);
        opsGenieClient.setRootUri(getOpsgenieApiUrl());
    }

    public String getOpsgenieUrl() {
        return opsgenieUrl;
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

    public OpsGenieClient getOpsGenieClient() {
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

    public void setOpsgenieUrl(String opsgenieUrl) {
        this.opsgenieUrl = opsgenieUrl;
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

    public void setOpsGenieClient(OpsGenieClient opsGenieClient) {
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
