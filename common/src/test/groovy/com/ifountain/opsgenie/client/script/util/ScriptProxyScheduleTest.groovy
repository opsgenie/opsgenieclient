package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest
import com.ifountain.opsgenie.client.model.beans.*
import com.ifountain.opsgenie.client.model.schedule.*
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyScheduleTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testAddSchedule() throws Exception {
        _testAddSchedule(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddSchedule(true);

    }

    public void _testAddSchedule(boolean useConfig) throws Exception {
        Date dateobject = new Date();
        Map params = new HashMap();
        params.put(TestConstants.API.NAME, "schedule1");
        params.put(TestConstants.API.TIMEZONE, "Etc/GMT+7");
        params.put(TestConstants.API.ENABLED, false);
        params.put(TestConstants.API.ROTATIONS, [
                [
                        startDate   : "2013-01-24 22:00", rotationType: "daily", rotationLength: 7,
                        participants: ["group1"],
                        restrictions: [
                                [startDay: "monday", startHour: 0, startMinute: 0, endHour: 24, endMinute: 30, endDay: "sunday"],
                        ]
                ],
                [
                        startDate   : dateobject, rotationType: "weekly",
                        participants: ["group3"],
                ]
        ]);
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddScheduleResponse(id: "schedule1Id")
        opsGenieClient.schedule().setAddScheduleResponse(expectedResponse);
        Map response = proxy.addSchedule(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddScheduleRequest request = executedRequests[0] as AddScheduleRequest;

        assertEquals("schedule1", request.getName())
        assertEquals(TimeZone.getTimeZone("Etc/GMT+7").getID(), request.getTimeZone().getID())
        assertFalse(request.isEnabled())
        assertEquals(2, request.getRotations().size())

        def scheduleRule = request.getRotations().find { !it.startDate.equals(dateobject) }
        assertEquals(ScheduleRotation.RotationType.daily, scheduleRule.getRotationType())
        assertEquals(1, scheduleRule.getParticipants().size())
        assertEquals("group1", scheduleRule.getParticipants()[0].participant)
        assertEquals(1, scheduleRule.getRestrictions().size())
        assertEquals(ScheduleRotationRestriction.DAY.monday, scheduleRule.getRestrictions()[0].getStartDay())
        assertEquals(ScheduleRotationRestriction.DAY.sunday, scheduleRule.getRestrictions()[0].getEndDay())
        assertEquals(0, scheduleRule.getRestrictions()[0].getStartHour())
        assertEquals(0, scheduleRule.getRestrictions()[0].getStartMin())
        assertEquals(24, scheduleRule.getRestrictions()[0].getEndHour())
        assertEquals(30, scheduleRule.getRestrictions()[0].getEndMin())

        scheduleRule = request.getRotations().find { it.startDate.equals(dateobject) }
        assertEquals(ScheduleRotation.RotationType.weekly, scheduleRule.getRotationType())
        assertEquals(1, scheduleRule.getParticipants().size())
        assertEquals("group3", scheduleRule.getParticipants()[0].participant)
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddScheduleReturningException() throws Exception {
        _testeReturningException("addSchedule", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testUpdateSchedule() throws Exception {
        _testUpdateSchedule(false);
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateSchedule(true);

    }

    public void _testUpdateSchedule(boolean useConfig) throws Exception {
        Date dateobject = new Date();
        Map params = new HashMap();
        params.put(TestConstants.API.ID, "schedule1Id");
        params.put(TestConstants.API.NAME, "schedule1");
        params.put(TestConstants.API.TIMEZONE, TimeZone.getTimeZone("Etc/GMT+7"));
        params.put(TestConstants.API.ENABLED, false);
        params.put(TestConstants.API.ROTATIONS, [
                [
                        startDate   : "2013-01-24 22:00", rotationType: "daily", rotationLength: 7,
                        participants: ["group1"],
                        restrictions: [
                                [startDay: "monday", startHour: 0, startMinute: 0, endHour: 24, endMinute: 30, endDay: "sunday"],
                        ]
                ],
                [
                        startDate   : dateobject, rotationType: "weekly",
                        participants: ["group3"],
                ]
        ]);
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new UpdateScheduleResponse(id: "schedule1Id")
        opsGenieClient.schedule().setUpdateScheduleResponse(expectedResponse);
        Map response = proxy.updateSchedule(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateScheduleRequest request = executedRequests[0] as UpdateScheduleRequest;

        assertEquals("schedule1Id", request.getId())
        assertEquals("schedule1", request.getName())
        assertEquals(TimeZone.getTimeZone("Etc/GMT+7").getID(), request.getTimeZone().getID())
        assertFalse(request.isEnabled())
        assertEquals(2, request.getRotations().size())

        def scheduleRule = request.getRotations().find { !it.startDate.equals(dateobject) }
        assertEquals(ScheduleRotation.RotationType.daily, scheduleRule.getRotationType())
        assertEquals(1, scheduleRule.getParticipants().size())
        assertEquals("group1", scheduleRule.getParticipants()[0].participant)
        assertEquals(1, scheduleRule.getRestrictions().size())
        assertEquals(ScheduleRotationRestriction.DAY.monday, scheduleRule.getRestrictions()[0].getStartDay())
        assertEquals(ScheduleRotationRestriction.DAY.sunday, scheduleRule.getRestrictions()[0].getEndDay())
        assertEquals(0, scheduleRule.getRestrictions()[0].getStartHour())
        assertEquals(0, scheduleRule.getRestrictions()[0].getStartMin())
        assertEquals(24, scheduleRule.getRestrictions()[0].getEndHour())
        assertEquals(30, scheduleRule.getRestrictions()[0].getEndMin())

        scheduleRule = request.getRotations().find { it.startDate.equals(dateobject) }
        assertEquals(ScheduleRotation.RotationType.weekly, scheduleRule.getRotationType())
        assertEquals(1, scheduleRule.getParticipants().size())
        assertEquals("group3", scheduleRule.getParticipants()[0].participant)
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testUpdateScheduleReturningException() throws Exception {
        _testeReturningException("updateSchedule", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testDeleteSchedule() throws Exception {
        _testDeleteSchedule(false);
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteSchedule(true);

    }

    public void _testDeleteSchedule(boolean useConfig) throws Exception {
        def params = [id: "schedule1Id", name: "schedule1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteScheduleResponse()
        opsGenieClient.schedule().setDeleteScheduleResponse(expectedResponse);
        Map response = proxy.deleteSchedule(params)
        assertEquals(true, response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteScheduleRequest request = executedRequests[0] as DeleteScheduleRequest;

        assertEquals("schedule1Id", request.getId())
        assertEquals("schedule1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testDeleteScheduleReturningException() throws Exception {
        _testeReturningException("deleteSchedule", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testGetSchedule() throws Exception {
        _testGetSchedule(false);
        opsGenieClient.getExecutedRequests().clear()
        _testGetSchedule(true);

    }

    public void _testGetSchedule(boolean useConfig) throws Exception {
        def params = [id: "schedule1Id", name: "schedule1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new GetScheduleResponse()
        expectedResponse.setSchedule(new Schedule(id: "schedule1"))
        opsGenieClient.schedule().setGetScheduleResponse(expectedResponse);
        Map response = proxy.getSchedule(params)
        assertEquals("schedule1", response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetScheduleRequest request = executedRequests[0] as GetScheduleRequest;

        assertEquals("schedule1Id", request.getId())
        assertEquals("schedule1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testGetScheduleReturningException() throws Exception {
        _testeReturningException("getSchedule", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testWhoIsOnCall() throws Exception {
        _testWhoIsOnCall(false);
        opsGenieClient.getExecutedRequests().clear()
        _testWhoIsOnCall(true);

    }

    public void _testWhoIsOnCall(boolean useConfig) throws Exception {
        def params = [id      : "schedule1Id", name: "schedule1", time: new Date(),
                      timezone: TimeZone.getTimeZone("Etc/GMT+2")];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new WhoIsOnCallResponse()
        expectedResponse.setWhoIsOnCall(new WhoIsOnCall(name: "group1"))
        opsGenieClient.schedule().setWhoIsOnCallResponse(expectedResponse);
        Map response = proxy.whoIsOnCall(params)
        assertEquals("group1", response[TestConstants.API.NAME])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        WhoIsOnCallRequest request = executedRequests[0] as WhoIsOnCallRequest;

        assertEquals("schedule1Id", request.getId())
        assertEquals("schedule1", request.getName())
        assertEquals(params.time, request.getTime())
        assertEquals(params.timezone, request.getTimeZone())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testWhoIsOnCallReturningException() throws Exception {
        _testeReturningException("whoIsOnCall", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testFlatWhoIsOnCall() throws Exception {
        _testFlatWhoIsOnCall(false);
        opsGenieClient.getExecutedRequests().clear()
        _testFlatWhoIsOnCall(true);

    }

    public void _testFlatWhoIsOnCall(boolean useConfig) throws Exception {
        def params = [id      : "schedule1Id", name: "schedule1", time: new Date(),
                      timezone: TimeZone.getTimeZone("Etc/GMT+2")];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new FlatWhoIsOnCallResponse()
        expectedResponse.setWhoIsOnCall(new FlatWhoIsOnCall(name: "schedule1", recipients: ["usr1@xyz.com"]))
        opsGenieClient.schedule().setFlatWhoIsOnCallResponse(expectedResponse);
        Map response = proxy.flatWhoIsOnCall(params)
        assertEquals("schedule1", response[TestConstants.API.NAME])
        assertEquals(["usr1@xyz.com"], response[TestConstants.API.RECIPIENTS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        FlatWhoIsOnCallRequest request = executedRequests[0] as FlatWhoIsOnCallRequest;

        assertEquals("schedule1Id", request.getId())
        assertEquals("schedule1", request.getName())
        assertEquals(params.time, request.getTime())
        assertEquals(params.timezone, request.getTimeZone())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testFlatWhoIsOnCallReturningException() throws Exception {
        _testeReturningException("flatWhoIsOnCall", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testListWhoIsOnCall() throws Exception {
        _testListWhoIsOnCall(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListWhoIsOnCall(true);

    }

    public void _testListWhoIsOnCall(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListWhoIsOnCallResponse()
        expectedResponse.setWhoIsOnCallList([new WhoIsOnCall(name: "group1")])
        opsGenieClient.schedule().setListWhoIsOnCallResponse(expectedResponse);
        List<Map> response = proxy.listWhoIsOnCall(params)
        assertEquals(1, response.size())
        def whoIsOnCall = response[0];
        assertEquals("group1", whoIsOnCall[TestConstants.API.NAME])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListWhoIsOnCallRequest request = executedRequests[0] as ListWhoIsOnCallRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListWhoIsOnCallReturningException() throws Exception {
        _testeReturningException("listWhoIsOnCall", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testListFlatWhoIsOnCall() throws Exception {
        _testListFlatWhoIsOnCall(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListFlatWhoIsOnCall(true);

    }

    public void _testListFlatWhoIsOnCall(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListFlatWhoIsOnCallResponse()
        expectedResponse.setWhoIsOnCallList([new FlatWhoIsOnCall(name: "schedule1", recipients: ["usr1@xyz.com", "usr2@xyz.com"])])
        opsGenieClient.schedule().setListFlatWhoIsOnCallResponse(expectedResponse);
        List<Map> response = proxy.listFlatWhoIsOnCall(params)
        assertEquals(1, response.size())
        def whoIsOnCall = response[0];
        assertEquals("schedule1", whoIsOnCall[TestConstants.API.NAME])
        assertEquals(["usr1@xyz.com", "usr2@xyz.com"], whoIsOnCall[TestConstants.API.RECIPIENTS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListFlatWhoIsOnCallRequest request = executedRequests[0] as ListFlatWhoIsOnCallRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListFlatWhoIsOnCallReturningException() throws Exception {
        _testeReturningException("listFlatWhoIsOnCall", [apiKey: "customer1"], new Exception("Schedule does not exist."))
    }

    @Test
    public void testListSchedule() throws Exception {
        _testListSchedule(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListSchedule(true);

    }

    public void _testListSchedule(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListSchedulesResponse()
        expectedResponse.setSchedules([new Schedule(id: "schedule1")])
        opsGenieClient.schedule().setListSchedulesResponse(expectedResponse);
        List<Map> response = proxy.listSchedules(params)
        assertEquals(1, response.size())
        assertEquals("schedule1", response[0][TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListSchedulesRequest request = executedRequests[0] as ListSchedulesRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListScheduleReturningException() throws Exception {
        _testeReturningException("listSchedules", [apiKey: "customer1"], new Exception("Schedule does not exist."))
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
