package com.ifountain.opsgenie.client.marid;

import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.client.opsgenie.OpsGenieClient;
import com.ifountain.client.http.HttpClient;
import com.ifountain.client.util.ClientConfiguration;
import com.ifountain.client.util.ClientProxyConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/9/12 2:44 PM
 */
public class MaridConfig {
    private  String opsgenieApiUrl;
    private  String apiKey;
    private  String maridKey;
    private HttpClient httpClient;
    private  IOpsGenieClient opsGenieClient;
    private Map<String, String> lowercasedConfiguration;
    private Properties configuration;
    private ClientConfiguration clientConfiguration;

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
        if(httpClient != null){
            httpClient.close();
            httpClient = null;
        }
    }

    public void init(String configurationPath) throws Exception {
        if(configuration != null) throw new Exception("Already initialized");
        Properties configuration = new Properties();
        File configFile = new File(configurationPath);
        if (configFile.exists()) {
            FileInputStream in = new FileInputStream(configFile);
            try {
                configuration.load(in);
                setConfiguration(configuration);
            } finally {
                in.close();
            }
        } else {
            throw new FileNotFoundException("Configuration file " + configurationPath + " does not exist");
        }
        opsgenieApiUrl = configuration.getProperty("opsgenie.api.url", "https://api.opsgenie.com");
        apiKey = configuration.getProperty("apiKey");
        if(apiKey == null){
            apiKey = configuration.getProperty("customerKey");
        }
        maridKey = configuration.getProperty("maridKey");
        initOpsgenieClient();
    }

    private void initOpsgenieClient(){
        clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSocketTimeout(getInt("opsgenie.connection.sockettimeout", 30)*1000);
        clientConfiguration.setConnectionTimeout(getInt("opsgenie.connection.timeout", 30)*1000);
        clientConfiguration.setMaxConnections(getInt("opsgenie.connection.maxConnectionCount", 50));
        if(getBoolean("http.proxy.enabled", false)){
            ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(getProperty("http.proxy.host"), getInt("http.proxy.port", 0));
            clientProxyConfiguration.setProxyUsername(getProperty("http.proxy.username"));
            clientProxyConfiguration.setProxyPassword(getProperty("http.proxy.password"));
            clientProxyConfiguration.setProxyProtocol(getProperty("http.proxy.protocol"));
            String authMethod = getProperty("http.proxy.authMethod", ClientProxyConfiguration.AuthType.NT.name());
            ClientProxyConfiguration.AuthType authTypeEnum;
            try{
                authTypeEnum = ClientProxyConfiguration.AuthType.valueOf(authMethod);
            }catch (Throwable t){
                throw new RuntimeException("Invalid authMethod ["+authMethod+"]");
            }
            clientProxyConfiguration.setAuthType(authTypeEnum);
            clientConfiguration.setClientProxyConfiguration(clientProxyConfiguration);
        }

        clientConfiguration.setUserAgent(ClientConfiguration.createUserAgentFromManifest(MaridConfig.class));
        if(configuration.getProperty("opsgenie.connection.socketReceiveBufferSizeHint") != null){
            clientConfiguration.setSocketReceiveBufferSizeHint(getInt("opsgenie.connection.socketReceiveBufferSizeHint", -1));
        }
        if(configuration.getProperty("opsgenie.connection.socketSendBufferSizeHint") != null){
            clientConfiguration.setSocketSendBufferSizeHint(getInt("opsgenie.connection.socketSendBufferSizeHint", -1));
        }
        httpClient = new HttpClient(clientConfiguration);
        opsGenieClient = new OpsGenieClient(httpClient);
        opsGenieClient.setRootUri(getOpsgenieApiUrl());
    }

    public Map<String, String> getLowercasedConfiguration() {
        if(lowercasedConfiguration == null){
            throw new RuntimeException("Not initialized");
        }
        return lowercasedConfiguration;
    }

    public ClientConfiguration getClientConfiguration() {
        return clientConfiguration;
    }

    public String getOpsgenieApiUrl() {
        return opsgenieApiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getMaridKey() {
        return maridKey;
    }

    public HttpClient getHttpClient() {
        return httpClient;
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

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setMaridKey(String maridKey) {
        this.maridKey = maridKey;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setOpsGenieClient(IOpsGenieClient opsGenieClient) {
        this.opsGenieClient = opsGenieClient;
    }

    public void setConfiguration(Properties configuration) {
        this.configuration = configuration;
        lowercasedConfiguration = new HashMap<String, String>();
        for(Map.Entry entry:configuration.entrySet()){
            lowercasedConfiguration.put(String.valueOf(entry.getKey()).toLowerCase(), String.valueOf(entry.getValue()));
        }
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
