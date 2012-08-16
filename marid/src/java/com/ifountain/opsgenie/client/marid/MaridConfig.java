package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;

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
    public static void reset(){
        opsgenieUrl = null;
        opsgenieApiUrl = null;
        customerKey = null;
        maridKey = null;
        opsGenieHttpClient = null;
        opsGenieClient = null;
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
