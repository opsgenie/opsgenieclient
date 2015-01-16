package com.ifountain.opsgenie.client.script.util


import com.ifountain.opsgenie.client.model.alert.AddNoteRequest
import com.ifountain.opsgenie.client.model.alert.AddNoteResponse
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test
import static  org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyTest  {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;
    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test public void testApiKeyFromConfFile() throws Exception {
        opsGenieClient.alert().setAddNoteResponse(new AddNoteResponse());
        Map response = proxy.addNote([alias:"alias1", alertId:"alertId1", note: "mynote"])
        assertTrue(response.success)

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddNoteRequest request = executedRequests[0] as AddNoteRequest;

        assertEquals(apiKey, request.getApiKey())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
        //option overriding

        response = proxy.addNote([apiKey:"requestApiKey", user: "requestUser", alias:"alias1", alertId:"alertId1", note: "mynote"])
        assertTrue(response.success)

        executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(2, executedRequests.size())
        request = executedRequests[1] as AddNoteRequest;

        assertEquals("requestApiKey", request.getApiKey())
        assertEquals("requestUser", request.getUser())
        assertEquals("alias1", request.getAlias())
        assertEquals("alertId1", request.getAlertId());
        assertEquals("mynote", request.getNote());
    }
}
