package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Heartbeat
import com.ifountain.opsgenie.client.model.customer.*
import com.ifountain.opsgenie.client.test.util.JSON
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
class OpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void testHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"heartbeat\":123, \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        HeartbeatRequest request = new HeartbeatRequest();
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.heartbeat(request)
        assertEquals(123, response.getHeartbeat())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/heartbeat/send", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])

        //test with source
        request = new HeartbeatRequest();
        request.setName("source1")
        request.setApiKey("customer1")

        response = OpsGenieClientTestCase.opsgenieClient.heartbeat(request)
        assertEquals(123, response.getHeartbeat())
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/heartbeat/send", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("source1", jsonContent[TestConstants.API.NAME])
    }

    @Test
    public void testHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "heartbeat", new HeartbeatRequest())
    }

    @Test
    public void testGetHeartbeatSuccessfully() throws Exception {
        def data = [name        : "source1",
                    lastPingTime: new Date(123123),
                    enabled     : false,
                    expired     : false,
                    interval    : 14,
                    intervalUnit: "hours",
                    description : "description1"]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(
                JsonUtils.toJson([data: data]).getBytes(), 200, "application/json; charset=utf-8"))

        GetHeartbeatRequest request = new GetHeartbeatRequest();
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.getHeartbeat(request)

        def heartbeat = response.getHeartbeat()
        assertEquals("source1", heartbeat.getName())
        assertEquals(new Date(123123), heartbeat.getLastPingTime())
        assertFalse(heartbeat.isExpired())
        assertEquals(14, heartbeat.getInterval())
        assertEquals(Heartbeat.IntervalUnit.hours, heartbeat.getIntervalUnit())
        assertEquals("description1", heartbeat.getDescription())
        assertFalse(heartbeat.isEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1", requestSent.getUrl())

        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))
    }

    @Test
    public void testGetHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        testThrowsExceptionWithNewRestApiIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "getHeartbeat", new GetHeartbeatRequest(name: "source1"))
    }


    @Test
    public void testDeleteHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([success: true]).getBytes(), 200, "application/json; charset=utf-8"))

        DeleteHeartbeatRequest request = new DeleteHeartbeatRequest();
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.deleteHeartbeat(request)
        assertTrue(response.isSuccess())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1", requestSent.getUrl())

        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))
    }

    @Test
    public void testDeleteHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        testThrowsExceptionWithNewRestApiIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "deleteHeartbeat", new DeleteHeartbeatRequest(name: "source1"))
    }

    @Test
    public void testEnableHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{ }".getBytes(), 200, "application/json; charset=utf-8"))

        EnableHeartbeatRequest request = new EnableHeartbeatRequest();
        request.setEnable(true)
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.enableHeartbeat(request)
        assertTrue(response.isSuccess())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1/enable", requestSent.getUrl())

        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))

        //test disable
        request = new EnableHeartbeatRequest();
        request.setEnable(false)
        request.setName("source1")
        request.setApiKey("customer1")

        response = OpsGenieClientTestCase.opsgenieClient.enableHeartbeat(request)
        assertTrue(response.isSuccess())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1/disable", requestSent.getUrl())

        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))
    }

    @Test
    public void testPingHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{ \"data\": { \"message\" : \"PONG\" }}".getBytes(), 202, "application/json; charset=utf-8"))

        HeartbeatPingRequest request = new HeartbeatPingRequest();
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.pingHeartbeat(request)
        assertTrue(response.isSuccess())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1/ping", requestSent.getUrl())

        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))
    }

    @Test
    public void testEnableHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        testThrowsExceptionWithNewRestApiIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "enableHeartbeat", new EnableHeartbeatRequest(name: "source1"))
    }

    @Test
    public void testAddHeartbeatSuccessfully() throws Exception {
        def data = [name        : "source1",
                    enabled     : false,
                    interval    : 14,
                    intervalUnit: "hours",
                    description : "description1"]

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([data: data]).getBytes(), 201, "application/json; charset=utf-8"))

        AddHeartbeatRequest request = new AddHeartbeatRequest();
        request.setEnabled(true)
        request.setInterval(10)
        request.setIntervalUnit(Heartbeat.IntervalUnit.days)
        request.setDescription("description1")
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.addHeartbeat(request)
        assertTrue(response.isSuccess())
        assertEquals("source1", response.name)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/", requestSent.getUrl())

        def jsonContent = JSON.parse(requestSent.getContent())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("source1", jsonContent[TestConstants.API.NAME])
        assertEquals(10, jsonContent[TestConstants.API.INTERVAL])
        assertEquals(Heartbeat.IntervalUnit.days.name(), jsonContent[TestConstants.API.INTERVAL_UNIT])
        assertEquals("description1", jsonContent[TestConstants.API.DESCRIPTION])
        assertEquals(true, jsonContent[TestConstants.API.ENABLED])
    }

    @Test
    public void testAddHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        testThrowsExceptionWithNewRestApiIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "addHeartbeat", new AddHeartbeatRequest(name: "source1"))

    }


    @Test
    public void testUpdateHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([data: [name: "source1"]]).getBytes(), 200, "application/json; charset=utf-8"))

        UpdateHeartbeatRequest request = new UpdateHeartbeatRequest();
        request.setEnabled(true)
        request.setInterval(10)
        request.setIntervalUnit(Heartbeat.IntervalUnit.days)
        request.setDescription("description1")
        request.setName("source1")
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.updateHeartbeat(request)
        assertTrue(response.isSuccess())
        assertEquals("source1", response.name)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/heartbeats/source1", requestSent.getUrl())

        def jsonContent = JSON.parse(requestSent.getContent())
        assertEquals("GenieKey customer1", requestSent.getHeader("Authorization"))
        assertEquals(10, jsonContent[TestConstants.API.INTERVAL])
        assertEquals(Heartbeat.IntervalUnit.days.name(), jsonContent[TestConstants.API.INTERVAL_UNIT])
        assertEquals("description1", jsonContent[TestConstants.API.DESCRIPTION])
        assertEquals(true, jsonContent[TestConstants.API.ENABLED])
    }

    @Test
    public void testUpdateHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        testThrowsExceptionWithNewRestApiIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "updateHeartbeat", new UpdateHeartbeatRequest(name: "source1"))
    }

    @Test
    public void testListHeartbeatSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(("{\"took\":1, \"heartbeats\":[" +
                "{\"name\":\"source1\", \"lastHeartbeat\":123123, \"expired\":false,  \"enabled\":false, \"interval\":14, \"intervalUnit\":\"hours\", \"description\":\"description1\", \"status\":\"Active\"}," +
                "{\"name\":\"source2\", \"lastHeartbeat\":567, \"expired\":true,  \"enabled\":true, \"interval\":10, \"intervalUnit\":\"minutes\", \"description\":\"description2\", \"status\":\"Expired\"}" +
                "]}").getBytes(), 200, "application/json; charset=utf-8"))

        ListHeartbeatsRequest request = new ListHeartbeatsRequest();
        request.setApiKey("customer1")

        def response = OpsGenieClientTestCase.opsgenieClient.listHeartbeats(request)
        assertEquals(2, response.getHeartbeats().size())

        def heartbeat = response.getHeartbeats()[0]
        assertEquals("source1", heartbeat.getName())
        assertEquals(new Date(123123), heartbeat.getLastPingTime())
        assertFalse(heartbeat.isExpired())
        assertEquals(14, heartbeat.getInterval())
        assertEquals(Heartbeat.IntervalUnit.hours, heartbeat.getIntervalUnit())
        assertEquals("description1", heartbeat.getDescription())
        assertFalse(heartbeat.isEnabled())

        heartbeat = response.getHeartbeats()[1]
        assertEquals("source2", heartbeat.getName())
        assertEquals(new Date(567), heartbeat.getLastPingTime())
        assertTrue(heartbeat.isExpired())
        assertEquals(10, heartbeat.getInterval())
        assertEquals(Heartbeat.IntervalUnit.minutes, heartbeat.getIntervalUnit())
        assertEquals("description2", heartbeat.getDescription())
        assertTrue(heartbeat.isEnabled())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/heartbeat", requestSent.getUrl())

        def jsonContent = requestSent.getParameters()
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
    }

    @Test
    public void testListHeartbeatThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient, "listHeartbeats", new ListHeartbeatsRequest())
    }
}
