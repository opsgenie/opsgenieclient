package com.ifountain.opsgenie.client.util

import com.ifountain.opsgenie.client.OpsGenieClientConstants
import org.apache.commons.lang3.RandomStringUtils
import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * @author bkama
 * @version 30/04/2018 15:18
 */
class LogUtilsTest {

    @Test
    public void getInsensitiveLogMessage() {
        Map<String, Map> params = new HashMap<>();
        params.put(OpsGenieClientConstants.API.API_KEY, RandomStringUtils.randomAlphabetic(10));
        params.put(OpsGenieClientConstants.API.ACTION, RandomStringUtils.randomAlphabetic(10));
        params.put(OpsGenieClientConstants.API.DESCRIPTION, RandomStringUtils.randomAlphabetic(10));
        params.put(OpsGenieClientConstants.API.MESSAGE, RandomStringUtils.randomAlphabetic(10));
        params.put(OpsGenieClientConstants.API.GENIE_KEY, RandomStringUtils.randomAlphabetic(10));
        params.put(OpsGenieClientConstants.API.CUSTOMER_KEY, RandomStringUtils.randomAlphabetic(10));

        String secureLogStr = "{" + OpsGenieClientConstants.API.ACTION + "=" + params.get(OpsGenieClientConstants.API.ACTION) +
                ", " + OpsGenieClientConstants.API.DESCRIPTION + "=" + params.get(OpsGenieClientConstants.API.DESCRIPTION) +
                ", " + OpsGenieClientConstants.API.MESSAGE + "=" + params.get(OpsGenieClientConstants.API.MESSAGE) +
                "}";

        assertEquals(LogUtils.getInsensitiveLogMessage(params), secureLogStr);
    }
}
