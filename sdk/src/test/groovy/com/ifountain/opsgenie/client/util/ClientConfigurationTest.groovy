package com.ifountain.opsgenie.client.util


import org.junit.Test
import static org.junit.Assert.*

class ClientConfigurationTest {
    @Test
    public void testCreateUserAgent() {
        String prodName = "sdk"
        String version = "v1"
        String userAgent = ClientConfiguration.createUserAgent(prodName, version);
        String expectedAgent = "${prodName}/${version} (${System.getProperty("os.name")} ${System.getProperty("os.version")}" +
                "; ${System.getProperty("java.vm.name")} ${System.getProperty("java.vm.version")}; ${System.getProperty("user.language")}"
        if (System.getProperty("user.region") != null) {
            expectedAgent += "; ${System.getProperty("user.region")}";
        }
        expectedAgent += ")"
        assertEquals(expectedAgent, userAgent)
    }
}
