package com.ifountain.opsgenie.client.marid;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/9/12 2:44 PM
 */
public class OpsGenie {
    private static String url;
    private static String customerKey;

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
}
