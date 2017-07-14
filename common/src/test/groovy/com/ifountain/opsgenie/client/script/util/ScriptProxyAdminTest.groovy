package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.beans.Heartbeat
import com.ifountain.opsgenie.client.model.customer.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test

import java.text.SimpleDateFormat

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyAdminTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testHearbeat() throws Exception {
        _testHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testHearbeat(true)
    }

    public void _testHearbeat(boolean useConfig) throws Exception {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat(123);
        opsGenieClient.setHeartbeatResponse(response);

        def params = [name: "source1"]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.heartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        HeartbeatRequest request = executedRequests[0] as HeartbeatRequest;

        assertEquals("source1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(123, responseMap.heartbeat)
    }

    @Test
    public void testHeartbeatReturningException() throws Exception {
        _testeReturningException("heartbeat", [apiKey: "customer1", name: "source1"], new Exception("Exception"))
    }

    @Test
    public void testDeleteHearbeat() throws Exception {
        _testDeleteHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteHearbeat(true)
    }

    public void _testDeleteHearbeat(boolean useConfig) throws Exception {
        DeleteHeartbeatResponse response = new DeleteHeartbeatResponse();
        response.setSuccess(true)
        opsGenieClient.setDeleteHeartbeatResponse(response);

        def params = [name: "source1"]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.deleteHeartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteHeartbeatRequest request = executedRequests[0] as DeleteHeartbeatRequest;

        assertEquals("source1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertTrue(responseMap.success)
    }

    @Test
    public void testEnableHearbeat() throws Exception {
        _testEnableHearbeat(false, false)
        opsGenieClient.getExecutedRequests().clear()
        _testEnableHearbeat(false, true)
        opsGenieClient.getExecutedRequests().clear()
        _testEnableHearbeat(true, false)
        opsGenieClient.getExecutedRequests().clear()
        _testEnableHearbeat(true, true)
    }

    public void _testEnableHearbeat(boolean enable, boolean useConfig) throws Exception {
        EnableHeartbeatResponse response = new EnableHeartbeatResponse();
        response.setSuccess(true)
        opsGenieClient.setEnableHeartbeatResponse(response);

        def params = [name: "source1", enable: enable]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.enableHeartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        EnableHeartbeatRequest request = executedRequests[0] as EnableHeartbeatRequest;

        assertEquals("source1", request.getName())
        assertEquals(enable, request.enable)
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertTrue(responseMap.success)
    }

    @Test
    public void testAddHearbeat() throws Exception {
        _testAddHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testAddHearbeat(true)
    }

    public void _testAddHearbeat(boolean useConfig) throws Exception {
        AddHeartbeatResponse response = new AddHeartbeatResponse();
        response.setName("source1")
        opsGenieClient.setAddHeartbeatResponse(response);

        def params = [name: "source1", enabled: false, interval: 5, intervalUnit: "days", description: "description1"]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.addHeartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddHeartbeatRequest request = executedRequests[0] as AddHeartbeatRequest;

        assertEquals("source1", request.getName())
        assertEquals(false, request.enabled)
        assertEquals(5, request.interval)
        assertEquals(Heartbeat.IntervalUnit.days, request.intervalUnit)
        assertEquals("description1", request.description)
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertTrue(responseMap.success)
    }

    @Test
    public void testUpdateHearbeat() throws Exception {
        _testUpdateHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateHearbeat(true)
    }

    public void _testUpdateHearbeat(boolean useConfig) throws Exception {
        UpdateHeartbeatResponse response = new UpdateHeartbeatResponse();
        response.setName("source1")
        opsGenieClient.setUpdateHeartbeatResponse(response);

        def params = [name: "source1", enabled: false, interval: 5, intervalUnit: "days", description: "description1"]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.updateHeartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateHeartbeatRequest request = executedRequests[0] as UpdateHeartbeatRequest;

        assertEquals("source1", request.getName())
        assertEquals(false, request.enabled)
        assertEquals(5, request.interval)
        assertEquals(Heartbeat.IntervalUnit.days, request.intervalUnit)
        assertEquals("description1", request.description)
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertTrue(responseMap.success)
    }


    @Test
    public void testAddHeartbeatReturningException() throws Exception {
        _testeReturningException("addHeartbeat", [apiKey: "customer1", name: "source1"], new Exception("Exception"))
    }

    @Test
    public void testUpdateHeartbeatReturningException() throws Exception {
        _testeReturningException("updateHeartbeat", [apiKey: "customer1", name: "source1"], new Exception("Exception"))
    }

    @Test
    public void testEnableHeartbeatReturningException() throws Exception {
        _testeReturningException("enableHeartbeat", [apiKey: "customer1", name: "source1", enable: true], new Exception("Exception"))
    }

    @Test
    public void testDeleteHeartbeatReturningException() throws Exception {
        _testeReturningException("deleteHeartbeat", [apiKey: "customer1", name: "source1"], new Exception("Exception"))
    }

    @Test
    public void testGetHearbeat() throws Exception {
        _testGetHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testGetHearbeat(true)
    }

    public void _testGetHearbeat(boolean useConfig) throws Exception {
        GetHeartbeatResponse response = new GetHeartbeatResponse();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        response.setHeartbeat(new Heartbeat(name: "source1", lastPingTime: date, expired: false))
        opsGenieClient.setGetHeartbeatResponse(response);

        def params = [name: "source1"]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        Map responseMap = proxy.getHeartbeat(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetHeartbeatRequest request = executedRequests[0] as GetHeartbeatRequest;

        assertEquals("source1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
        assertEquals(response.getHeartbeat().getName(), responseMap[TestConstants.API.NAME])
        assertEquals(sdf.format(response.getHeartbeat().getLastPingTime()), sdf.format(date))
        assertEquals(response.getHeartbeat().getLastPingTime().getTime(), (date.getTime()))
        assertEquals(response.getHeartbeat().isExpired(), responseMap[TestConstants.API.EXPIRED])
    }

    @Test
    public void testGetHeartbeatReturningException() throws Exception {
        _testeReturningException("getHeartbeat", [apiKey: "customer1", name: "source1"], new Exception("Exception"))
    }

    @Test
    public void testListHearbeat() throws Exception {
        _testListHearbeat(false)
        opsGenieClient.getExecutedRequests().clear()
        _testListHearbeat(true)
    }

    public void _testListHearbeat(boolean useConfig) throws Exception {
        ListHeartbeatsResponse response = new ListHeartbeatsResponse();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        response.setHeartbeats([new Heartbeat(name: "source1", lastPingTime: date, expired: false)])
        opsGenieClient.setListHeartbeatsResponse(response);

        def params = [:]
        if (!useConfig) {
            params.apiKey = "customer1";
            params.user = "someuser";
        }
        List<Map> responseMap = proxy.listHeartbeats(params)

        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListHeartbeatsRequest request = executedRequests[0] as ListHeartbeatsRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }

        assertEquals(response.getHeartbeats()[0].getName(), responseMap[0][TestConstants.API.NAME])
        assertEquals(sdf.format(response.getHeartbeats()[0].getLastPingTime()), sdf.format(date))
        assertEquals(response.getHeartbeats()[0].getLastPingTime().getTime(), (date.getTime()))
        assertEquals(response.getHeartbeats()[0].isExpired(), responseMap[0][TestConstants.API.EXPIRED])
    }

    @Test
    public void testListHeartbeatReturningException() throws Exception {
        _testeReturningException("listHeartbeats", [apiKey: "customer1"], new Exception("Exception"))
    }


    public void _testeReturningException(String methodName, Map params, Exception exception) throws Exception {
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
