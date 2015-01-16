package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationResponse
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationResponse
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

class ScriptProxyIntegrationTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testEnableIntegration() throws Exception {
        _testEnableIntegration(false);
        opsGenieClient.getExecutedRequests().clear()
        _testEnableIntegration(true);

    }

    private void _testEnableIntegration(boolean useConfig) throws Exception {
        def params = [id: "id1", name: "name1", enabled: false];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new EnableIntegrationResponse()
        opsGenieClient.integration().setEnableIntegrationResponse(expectedResponse);
        Map response = proxy.enableIntegration(params)
        assertTrue(response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0];

        assertEquals(params.id, request.getId())
        assertEquals(params.name, request.getName())
        assertFalse(request.isEnabled())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testEnableIntegrationReturningException() throws Exception {
        def expectedExpection = new Exception(" does not exist.")
        opsGenieClient.setException(expectedExpection)

        def params = [id: "integration1"]
        try {
            proxy.enableIntegration(params)
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals(expectedExpection.getMessage(), e.getMessage())
        }

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableIntegrationRequest request = executedRequests[0];
        assertEquals(params.id, request.getId())
    }

    @Test
    public void testSendToIntegration() {
        def httpParams = ["param1": "par1"]
        def contentParams = ["conte1": "cont1"]
        def expectedResponse = new SendToIntegrationResponse()
        opsGenieClient.integration().setSendToIntegrationResponse(expectedResponse)
        Map response = proxy.sendToIntegration("/v1/json/zabbix", contentParams, httpParams)
        assertTrue(response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        SendToIntegrationRequest request = executedRequests[0];
        assertEquals("/v1/json/zabbix", request.getEndPoint())
        assertEquals(contentParams, request.getContentParameters())
        assertEquals(httpParams, request.getHttpParameters())
    }
}
