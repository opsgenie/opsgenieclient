package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.swagger.auth.ApiKeyAuth;
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class AlertV2OpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    void testApiKeySetAsExpected() throws Exception {
        // Given
        def client1 = new OpsGenieClient()
        client1.apiKey = "secret-1"

        def client2 = new OpsGenieClient()
        client2.apiKey = "secret-2"

        // When
        def api1 = client1.alertV2()
        def api2 = client2.alertV2()

        // Then
        assertEquals("secret-1", ((ApiKeyAuth) api1.getApiClient().getAuthentication("GenieKey")).apiKey)
        assertEquals("secret-2", ((ApiKeyAuth) api2.getApiClient().getAuthentication("GenieKey")).apiKey)
    }

    @Test
    void testApplyHeaders() throws Exception {
        // Given
        def client = new OpsGenieClient()
        client.apiKey = "secret"

        def headers = new HashMap<String, String>()

        // When
        ((ApiKeyAuth) client.alertV2().apiClient.getAuthentication("GenieKey")).applyToParams([], headers)

        // Then
        assertEquals("GenieKey secret", headers["Authorization"])
    }
}
