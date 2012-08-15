package com.ifountain.opsgenie.client.marid;

import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/9/12 2:44 PM
 */
public class OpsGenie {
    private static String url;
    private static String customerKey;
    private static OpsGenieHttpClient httpClient;
    private static OpsGenieClient opsGenieClient;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        OpsGenie.url = url;
    }

    public static String getCustomerKey() {
        return customerKey;
    }

    public static void setCustomerKey(String customerKey) {
        OpsGenie.customerKey = customerKey;
    }

    public static OpsGenieHttpClient getHttpClient() {
        return httpClient;
    }

    public static void setHttpClient(OpsGenieHttpClient httpClient) {
        OpsGenie.httpClient = httpClient;
    }

    public static OpsGenieClient getOpsGenieClient() {
        return opsGenieClient;
    }

    public static void setOpsGenieClient(OpsGenieClient opsGenieClient) {
        OpsGenie.opsGenieClient = opsGenieClient;
    }
}
