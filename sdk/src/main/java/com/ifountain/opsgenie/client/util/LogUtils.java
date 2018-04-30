package com.ifountain.opsgenie.client.util;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry;

/**
 * @author bkama
 * @version 30/04/2018 11:38
 */
public class LogUtils {

    private static List<String> HIDDEN_KEYS = Arrays.asList(
            OpsGenieClientConstants.API.API_KEY,
            OpsGenieClientConstants.API.GENIE_KEY,
            OpsGenieClientConstants.API.CUSTOMER_KEY);

    public static String getInsensitiveLogMessage(Map<String, Object> params) {
        Map<String, Object> secureMap = new HashMap<String, Object>();
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                if (!HIDDEN_KEYS.contains(entry.getKey())) {
                    secureMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return secureMap.toString();
    }
}

