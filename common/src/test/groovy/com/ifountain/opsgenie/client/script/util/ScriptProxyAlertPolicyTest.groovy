package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyResponse
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

class ScriptProxyAlertPolicyTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testEnableAlertPolicy() throws Exception {
        _testEnableAlertPolicy(false);
        opsGenieClient.getExecutedRequests().clear()
        _testEnableAlertPolicy(true);
    }


    public void _testEnableAlertPolicy(boolean useConfig) throws Exception {
        def methods = ["enableAutomation", "enableAlertPolicy"]
        methods.each { String methodName ->
            opsGenieClient.getExecutedRequests().clear();
            def params = [id: "policy1Id", name: "policy1", enabled: false];
            if (!useConfig) {
                params.apiKey = "customer1";
            }
            def expectedResponse = new EnableAlertPolicyResponse()
            opsGenieClient.alertPolicy().setEnableAlertPolicyResponse(expectedResponse);
            Map response = proxy."${methodName}"(params)
            assertTrue(response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
            assertEquals(1, opsGenieClient.getExecutedRequests().size())
            def executedRequests = opsGenieClient.getExecutedRequests();
            assertEquals(1, executedRequests.size())
            EnableAlertPolicyRequest request = executedRequests[0] as EnableAlertPolicyRequest;

            assertEquals("policy1Id", request.getId())
            assertEquals("policy1", request.getName())
            assertFalse(request.isEnabled())
            if (useConfig) {
                assertEquals(apiKey, request.getApiKey())
            } else {
                assertEquals("customer1", request.getApiKey())
            }
        }
    }

    @Test
    public void testEnableAlertPolicyReturningException() throws Exception {
        _testReturningException("enableAlertPolicy", [apiKey: "customer1"], new Exception("NotificationPolicyProcessor does not exist."))
    }


    public void _testReturningException(String methodName, Map params, Exception exception) throws Exception {
        opsGenieClient.setException(exception);
        try {
            proxy."${methodName}"(params)
            fail("should throw exception");
        }
        catch (Exception e) {
            assertEquals(exception.getMessage(), e.getMessage())
        }

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        BaseRequest request = executedRequests[0];
        assertEquals("customer1", request.getApiKey())
    }
}
