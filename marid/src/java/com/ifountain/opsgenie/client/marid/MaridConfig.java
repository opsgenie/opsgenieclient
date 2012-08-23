package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;

import java.util.Properties;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/9/12 2:44 PM
 */
public class MaridConfig {
    private static String opsgenieUrl;
    private static String opsgenieApiUrl;
    private static String customerKey;
    private static String maridKey;
    private static OpsGenieHttpClient opsGenieHttpClient;
    private static OpsGenieClient opsGenieClient;
    private static Properties config;
    public static void reset(){
        opsgenieUrl = null;
        config = null;
        opsgenieApiUrl = null;
        customerKey = null;
        maridKey = null;
        opsGenieHttpClient = null;
        opsGenieClient = null;
    }

    public static Properties getConfig() {
        return config;
    }

    public static void setConfig(Properties config) {
        MaridConfig.config = config;
    }

    public static String getOpsgenieApiUrl() {
        return opsgenieApiUrl;
    }

    public static void setOpsgenieApiUrl(String opsgenieApiUrl) {
        MaridConfig.opsgenieApiUrl = opsgenieApiUrl;
    }

    public static String getMaridKey() {
        return maridKey;
    }

    public static void setMaridKey(String maridKey) {
        MaridConfig.maridKey = maridKey;
    }

    public static String getOpsgenieUrl() {
        return opsgenieUrl;
    }

    public static void setOpsgenieUrl(String opsgenieUrl) {
        MaridConfig.opsgenieUrl = opsgenieUrl;
    }

    public static String getCustomerKey() {
        return customerKey;
    }

    public static void setCustomerKey(String customerKey) {
        MaridConfig.customerKey = customerKey;
    }

    public static OpsGenieHttpClient getOpsGenieHttpClient() {
        return opsGenieHttpClient;
    }

    public static void setOpsGenieHttpClient(OpsGenieHttpClient opsGenieHttpClient) {
        MaridConfig.opsGenieHttpClient = opsGenieHttpClient;
    }

    public static OpsGenieClient getOpsGenieClient() {
        return opsGenieClient;
    }

    public static void setOpsGenieClient(OpsGenieClient opsGenieClient) {
        MaridConfig.opsGenieClient = opsGenieClient;
    }
}
