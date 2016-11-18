package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.model.alert.AddDetailsRequest
import com.ifountain.opsgenie.client.model.alert.AddDetailsResponse
import com.ifountain.opsgenie.client.model.alert.AddNoteRequest
import com.ifountain.opsgenie.client.model.alert.AddNoteResponse
import com.ifountain.opsgenie.client.model.alert.RemoveDetailsRequest
import com.ifountain.opsgenie.client.model.alert.RemoveDetailsResponse
import com.ifountain.opsgenie.client.model.alert.SnoozeRequest
import com.ifountain.opsgenie.client.model.alert.SnoozeResponse
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

    @Test
    public void testSnooze() throws Exception {
        opsGenieClient.alert().setSnoozeResponse(new SnoozeResponse());
        Map response = proxy.snooze(["id":"aydi", "endDate": new Date(2016, 11, 16), "timezone" : "GMT+3"])

        assertTrue(response.success)

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        SnoozeRequest request = executedRequests[0] as SnoozeRequest;

        assertEquals("aydi", request.getId())
        assertEquals(new Date(2016, 11, 16), request.getEndDate())
        assertEquals(TimeZone.getTimeZone("GMT+3"), request.getTimeZone())
    }

    @Test
    public void testAddDetails() throws Exception {
        opsGenieClient.alert().setAddDetailsResponse(new AddDetailsResponse());
        Map response = proxy.addDetails(["id": "aydi", "details": ["k1" : "v1", "k2" : "v2", "k3" : "v3"]])

        assertTrue(response.success)

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddDetailsRequest request = executedRequests[0] as AddDetailsRequest;

        assertEquals("aydi", request.getId())
        assertEquals(["k1" : "v1", "k2" : "v2", "k3" : "v3"], request.getDetails())
    }

    @Test
    public void testRemoveDetails() throws Exception {
        opsGenieClient.alert().setRemoveDetailsResponse(new RemoveDetailsResponse());
        Map response = proxy.removeDetails(["id" : "aydi", "keys" : ["key1", "key2", "key3"]])

        assertTrue(response.success)

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RemoveDetailsRequest request = executedRequests[0] as RemoveDetailsRequest;

        assertEquals("aydi", request.getId())
        assertEquals(["key1", "key2", "key3"], request.getKeys())
    }

    @Test
    public void testUnAcknowledge() throws Exception {
        opsGenieClient.alert().setUnAcknowledgeResponse(new UnAcknowledgeResponse());
        Map response = proxy.unAcknowledge(["id" : "aydi"]);

        assertTrue(response.success);

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UnAcknowledgeRequest request = executedRequests[0] as UnAcknowledgeRequest;

        assertEquals("aydi", request.getId());
    }

    @Test
    public void testEscalateToNext() throws Exception {
        opsGenieClient.alert().setEscalateToNextResponse(new EscalateToNextResponse());
        Map response = proxy.unAcknowledge(["id" : "aydi", "escalationId" : "eskaleysinAydi"]);

        assertTrue(response.success);

        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EscalateToNextRequest request = executedRequests[0] as EscalateToNextRequest;

        assertEquals("aydi", request.getId());
        assertEquals("eskaleysinAydi", request.getEscalationId());
    }
}
