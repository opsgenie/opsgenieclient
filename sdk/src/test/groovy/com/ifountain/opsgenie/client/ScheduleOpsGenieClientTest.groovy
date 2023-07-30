package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.*
import com.ifountain.opsgenie.client.model.schedule.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.json.JSONObject
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Instant
import static org.junit.Assert.*

class ScheduleOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    void testAddScheduleSuccessfully() throws Exception {
        String responseStr = getResponseString("AddScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 201, "application/json; charset=utf-8"))

        AddScheduleRequest request = getAddScheduleRequest()

        def response = opsgenieClient.schedule().addSchedule(request)
        assertEquals("4a9alp7-b5d2-4ecb-b82c-e3b5286829cf", response.getScheduleData().getId())
        assertEquals("ScheduleName",response.getScheduleData().getName())
        assertEquals(true,response.getScheduleData().getEnabled())
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/schedules", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertTrue(jsonContent[TestConstants.API.ENABLED] as boolean)

        assertEquals(1, jsonContent[TestConstants.API.ROTATIONS].collect {}.size())
    }

    private AddScheduleRequest getAddScheduleRequest() {
        AddScheduleRequest request = new AddScheduleRequest()
        request.setApiKey("apiKey")
        request.setName("ScheduleName")
        request.setDescription("ScheduleDescription")
        request.setEnabled(true)
        request.setTimeZone(TimeZone.getTimeZone("Europe/Kirov"))
        Date current = Calendar.getInstance().getTime()
        request.setRotations([
                new ScheduleRotation(name: "First Rotation", startDate: current + 1, endDate: current + 2, rotationType: ScheduleRotation.RotationType.hourly, rotationLength: 8,
                        participants: [
                                new ScheduleParticipant(type: "user", username: "user@opsgenie.com"),
                                new ScheduleParticipant(type: "none"),
                                new ScheduleParticipant(type: "user", id: "ac463592-dbd2-4ca3-alp1d-48fhf5j5c871")
                        ],
                        timeRestriction: new TimeRestriction(type: "weekday-and-time-of-day", restrictions: [
                                new Restriction(startDay: DAY.monday, startHour: 8, startMinute: 0, endDay: DAY.tuesday, endHour: 18, endMinute: 30),
                                new Restriction(startDay: DAY.wednesday, startHour: 8, startMinute: 0, endDay: DAY.thursday, endHour: 18, endMinute: 30)
                        ])
                )
        ])
        request.setOwnerTeam(new DataWithName(name: "ops_team"))
        request
    }

    @Test
    void testAddScheduleThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.schedule(), "addSchedule", new AddScheduleRequest())
        String responseStr = getResponseString("AddScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        AddScheduleRequest request = getAddScheduleRequest()

        request.getRotations().get(0).getTimeRestriction().getRestrictions().get(0).setEndHour(25)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for endHour",exception.getMessage())
        }

        request.getRotations().get(0).getTimeRestriction().getRestrictions().get(0).setStartHour(25)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for startHour",exception.getMessage())
        }

        request.getRotations().get(0).getTimeRestriction().getRestrictions().get(0).setEndDay(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("startDay, endDay cannot be empty",exception.getMessage())
        }

        request.getRotations().get(0).getTimeRestriction().getRestrictions().get(0).setStartHour(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("startHour, startMinute, endHour, endMinute cannot be empty",exception.getMessage())
        }

        request.getRotations().get(0).getTimeRestriction().setType("test")
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for timeRestrictionType",exception.getMessage())
        }

        request.getRotations().get(0).getTimeRestriction().setType("time-of-day")
        request.getRotations().get(0).getTimeRestriction().setRestrictions(new ArrayList<Restriction>())
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [restrictions]",exception.getMessage())
        }

        request.getRotations().get(0).getParticipants().get(0).setUsername(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Username or id must be provided.",exception.getMessage())
        }

        request.getRotations().get(0).getParticipants().get(0).setType(ScheduleParticipant.Type.escalation)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("For participant type team/escalation either team/escalation name or id must be provided.",exception.getMessage())
        }

        request.getRotations().get(0).getParticipants().get(0).setType(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [participant type]",exception.getMessage())
        }

        request.getRotations().get(0).setParticipants(new ArrayList<ScheduleParticipant>())
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [participants]",exception.getMessage())
        }

        Date current = Calendar.getInstance().getTime()
        request.getRotations().get(0).setEndDate(current)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Rotation end time should be later than start time.",exception.getMessage())
        }

        request.getRotations().get(0).setStartDate(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [startDate]",exception.getMessage())
        }

        request.getRotations().get(0).setRotationType(null)
        try {
            def response = opsgenieClient.schedule().addSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [rotationType]",exception.getMessage())
        }
    }

    @Test
    void testUpdateScheduleSuccessfully() throws Exception {
        String responseStr = getResponseString("UpdateScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))
        UpdateScheduleRequest request = getUpdateScheduleRequest()

        def response = opsgenieClient.schedule().updateSchedule(request)
        assertEquals("d875e654-9b4e-4219-alp3-0c26936d18de", response.getScheduleData().getId())
        assertEquals("DisabledScheduleName",response.getScheduleData().getName())
        assertEquals(true,response.getScheduleData().getEnabled())
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/schedules/ScheduleName", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertTrue(jsonContent[TestConstants.API.ENABLED] as boolean)

        assertEquals(1, jsonContent[TestConstants.API.ROTATIONS].collect {}.size())

        //update with id as identifier
        request.setIdentifierType(null)
        request.setIdentifier("test_id")
        response = opsgenieClient.schedule().updateSchedule(request)
        assertEquals("d875e654-9b4e-4219-alp3-0c26936d18de", response.getScheduleData().getId())
        assertEquals("DisabledScheduleName",response.getScheduleData().getName())
        assertEquals(true,response.getScheduleData().getEnabled())
        assertEquals(0, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]
        assertEquals(HttpPatch.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/schedules/test_id", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertTrue(jsonContent[TestConstants.API.ENABLED] as boolean)

        assertEquals(1, jsonContent[TestConstants.API.ROTATIONS].collect {}.size())

    }

    private UpdateScheduleRequest getUpdateScheduleRequest() {
        UpdateScheduleRequest request = new UpdateScheduleRequest()
        request.setApiKey("apiKey")
        request.setIdentifier("ScheduleName")
        request.setIdentifierType("name")
        request.setName("DisabledScheduleName")
        request.setDescription("ScheduleDescription")
        request.setEnabled(true)
        request.setTimeZone(TimeZone.getTimeZone("Europe/Kirov"))
        Date current = Calendar.getInstance().getTime()
        request.setRotations([
                new ScheduleRotation(name: "First Rotation", startDate: current + 1, endDate: current + 2, rotationType: ScheduleRotation.RotationType.hourly, rotationLength: 8,
                        participants: [
                                new ScheduleParticipant(type: "user", username: "user@opsgenie.com"),
                                new ScheduleParticipant(type: "none"),
                                new ScheduleParticipant(type: "user", id: "ac463592-dbd2-4ca3-alp1d-48fhf5j5c871")
                        ],
                        timeRestriction: new TimeRestriction(type: "weekday-and-time-of-day", restrictions: [
                                new Restriction(startDay: DAY.monday, startHour: 8, startMinute: 0, endDay: DAY.tuesday, endHour: 18, endMinute: 30),
                                new Restriction(startDay: DAY.wednesday, startHour: 8, startMinute: 0, endDay: DAY.thursday, endHour: 18, endMinute: 30)
                        ])
                )
        ])
        request.setTeam(new DataWithName(name: "ops_team"))
        request
    }

    @Test
    void testUpdateScheduleThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.schedule(), "updateSchedule", new UpdateScheduleRequest())
        String responseStr = getResponseString("UpdateScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))
        UpdateScheduleRequest request = getUpdateScheduleRequest()
        request.setIdentifier(null)
        try {
            def response = opsgenieClient.schedule().updateSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }

        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.schedule().updateSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }

    @Test
    void testDeleteScheduleSuccessfully() throws Exception {
        String responseStr = getResponseString("DeleteScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        DeleteScheduleRequest request = new DeleteScheduleRequest()
        request.setApiKey("customer1")
        request.setIdentifier("schedule1Id")
        request.setIdentifierType("name")

        def response = opsgenieClient.schedule().deleteSchedule(request)
        assertEquals(0, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/schedules/schedule1Id", requestSent.getUrl())

        //delete with id as identifier
        request.setIdentifierType(null)
        request.setIdentifier("test_id")
        response = opsgenieClient.schedule().deleteSchedule(request)
        assertEquals(0, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v2/schedules/test_id", requestSent.getUrl())
    }


    @Test
    void testDeleteUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.schedule(), "deleteSchedule", new DeleteScheduleRequest())

        String responseStr = getResponseString("DeleteScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        DeleteScheduleRequest request = new DeleteScheduleRequest()
        request.setApiKey("customer1")

        try {
            def response = opsgenieClient.schedule().deleteSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }

        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.schedule().deleteSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }

    @Test
    void testGetScheduleSuccessfully() throws Exception {
        String responseStr = getResponseString("GetScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        GetScheduleRequest request = new GetScheduleRequest()
        request.setApiKey("customer1")
        request.setIdentifier("schedule1Id")
        request.setIdentifierType("name")

        def response = opsgenieClient.schedule().getSchedule(request)
        assertEquals(0, response.getTook())
        assertEquals("ScheduleName", response.getSchedule().name)
        assertEquals("ops_team", response.getSchedule().ownerTeam.getName())
        assertEquals("6c7be998-fad9-4491-a9a7-d99alp4c0ae5", response.getSchedule().ownerTeam.getId())
        assertEquals(true,response.getSchedule().isEnabled())
        assertEquals("4a9e4b7c-b5d2-4ecb-b82c-e3b52alp829cf", response.getSchedule().id)
        assertEquals("Europe/Kirov", response.getSchedule().getTimeZone().getID())
        assertEquals(1, response.getSchedule().getRotations().size())


        def rotation = response.getSchedule().rotations.find { it.name ="First Rotation" }

        assertEquals("e1c575c4-7143-482c-8e83-4balp7d582d7", rotation.id)
        assertEquals("First Rotation",rotation.name)
        assertEquals(Instant.parse("2017-02-06T05:00:00.000Z"),rotation.startDate.toInstant())
        assertEquals(Instant.parse("2017-02-23T06:00:00Z"),rotation.endDate.toInstant())
        assertEquals(ScheduleRotation.RotationType.hourly, rotation.rotationType)
        assertEquals(6, rotation.rotationLength)
        assertEquals(2, rotation.getParticipants().size())
        assertEquals(ScheduleParticipant.Type.user,rotation.getParticipants().get(0).getType())
        assertEquals("96414alp-3ea5-41b1-a019-601598627d68",rotation.getParticipants().get(0).getId())
        assertEquals("user@opsgenie.com",rotation.getParticipants().get(0).getUsername())
        assertEquals(ScheduleParticipant.Type.none,rotation.getParticipants().get(1).getType())
        assertEquals("time-of-day",rotation.getTimeRestriction().getType())
        assertEquals(1,rotation.getTimeRestriction().getRestrictions().size())
        assertEquals(8,rotation.getTimeRestriction().getRestrictions().get(0).getStartHour())
        assertEquals(18,rotation.getTimeRestriction().getRestrictions().get(0).getEndHour())
        assertEquals(0,rotation.getTimeRestriction().getRestrictions().get(0).getStartMinute())
        assertEquals(30,rotation.getTimeRestriction().getRestrictions().get(0).getEndMinute())


        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/schedules/schedule1Id", requestSent.getUrl())

        //test with id as identifier
        request.setIdentifier("test_id")
        request.setIdentifierType(null)

        response = opsgenieClient.schedule().getSchedule(request)
        assertEquals(0, response.getTook())
        assertEquals("ScheduleName", response.getSchedule().name)
        assertEquals("ops_team", response.getSchedule().ownerTeam.getName())
        assertEquals("6c7be998-fad9-4491-a9a7-d99alp4c0ae5", response.getSchedule().ownerTeam.getId())
        assertEquals(true,response.getSchedule().isEnabled())
        assertEquals("4a9e4b7c-b5d2-4ecb-b82c-e3b52alp829cf", response.getSchedule().id)
        assertEquals("Europe/Kirov", response.getSchedule().getTimeZone().getID())
        assertEquals(1, response.getSchedule().getRotations().size())


        rotation = response.getSchedule().rotations.find { it.name ="First Rotation" }

        assertEquals("e1c575c4-7143-482c-8e83-4balp7d582d7", rotation.id)
        assertEquals("First Rotation",rotation.name)
        assertEquals(Instant.parse("2017-02-06T05:00:00.000Z"),rotation.startDate.toInstant())
        assertEquals(Instant.parse("2017-02-23T06:00:00Z"),rotation.endDate.toInstant())
        assertEquals(ScheduleRotation.RotationType.hourly, rotation.rotationType)
        assertEquals(6, rotation.rotationLength)
        assertEquals(2, rotation.getParticipants().size())
        assertEquals(ScheduleParticipant.Type.user,rotation.getParticipants().get(0).getType())
        assertEquals("96414alp-3ea5-41b1-a019-601598627d68",rotation.getParticipants().get(0).getId())
        assertEquals("user@opsgenie.com",rotation.getParticipants().get(0).getUsername())
        assertEquals(ScheduleParticipant.Type.none,rotation.getParticipants().get(1).getType())
        assertEquals("time-of-day",rotation.getTimeRestriction().getType())
        assertEquals(1,rotation.getTimeRestriction().getRestrictions().size())
        assertEquals(8,rotation.getTimeRestriction().getRestrictions().get(0).getStartHour())
        assertEquals(18,rotation.getTimeRestriction().getRestrictions().get(0).getEndHour())
        assertEquals(0,rotation.getTimeRestriction().getRestrictions().get(0).getStartMinute())
        assertEquals(30,rotation.getTimeRestriction().getRestrictions().get(0).getEndMinute())


        assertEquals(2, receivedRequests.size())
        requestSent = receivedRequests[1]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/schedules/test_id", requestSent.getUrl())
    }

    @Test
    void testGetUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.schedule(), "getSchedule", new GetScheduleRequest(identifier: "sch1"))
        String responseStr = getResponseString("GetScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        GetScheduleRequest request = new GetScheduleRequest()
        request.setApiKey("customer1")

        try {
            def response = opsgenieClient.schedule().getSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Missing mandatory property [name or id]",exception.getMessage())
        }

        request.setIdentifierType("test")
        try {
            def response = opsgenieClient.schedule().getSchedule(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for identifierType",exception.getMessage())
        }
    }

    @Test
    void testListSchedulesSuccessfully() throws Exception {
        String responseStr = getResponseString("ListScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        ListSchedulesRequest request = new ListSchedulesRequest()
        request.setApiKey("customer1")
        request.setExpand("rotation")


        def response = opsgenieClient.schedule().listSchedules(request)
        assertEquals(0, response.getTook())
        assertEquals("ScheduleName", response.getSchedules().get(0).name)
        assertEquals("ops_team", response.getSchedules().get(0).ownerTeam.getName())
        assertEquals("90098alp-f0e3-41d3-a060-0ea895027630", response.getSchedules().get(0).ownerTeam.getId())
        assertEquals(true,response.getSchedules().get(0).isEnabled())
        assertEquals("d875e654-9b4e-4219-alp3-0c26936d18de", response.getSchedules().get(0).id)
        assertEquals("Europe/Kirov", response.getSchedules().get(0).getTimeZone().getID())
        assertEquals(1, response.getSchedules().get(0).getRotations().size())


        def rotation = response.getSchedules().get(0).rotations.find { it.name ="First Rotation" }

        assertEquals("a47alp93-0541-4aa3-bac6-4084cfa02d20", rotation.id)
        assertEquals("First Rotation",rotation.name)
        assertEquals(Instant.parse("2017-05-14T21:00:00Z"),rotation.startDate.toInstant())
        assertEquals(ScheduleRotation.RotationType.weekly, rotation.rotationType)
        assertEquals(1, rotation.rotationLength)
        assertEquals(2, rotation.getParticipants().size())
        assertEquals(ScheduleParticipant.Type.user,rotation.getParticipants().get(0).getType())
        assertEquals("a9514028-2bca-4510-alpf-4b65f2c33a56",rotation.getParticipants().get(0).getId())
        assertEquals("user@opsgenie.com",rotation.getParticipants().get(0).getUsername())
        assertEquals(ScheduleParticipant.Type.team,rotation.getParticipants().get(1).getType())
        assertEquals("00564944-b42f-4b95-a882-ee9a5alpb9bb",rotation.getParticipants().get(1).getId())
        assertEquals("ops_team",rotation.getParticipants().get(1).getName())

        assertEquals(1,response.getExpandable().size())
        assertEquals("rotation",response.getExpandable().get(0))

        assertEquals(1, receivedRequests.size())
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod())
        assertEquals("/v2/schedules", requestSent.getUrl())
    }

    @Test
    void testListSchedulesThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(opsgenieClient.schedule(), "listSchedules", new ListSchedulesRequest())
        String responseStr = getResponseString("ListScheduleResponse")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 4000, "application/json; charset=utf-8"))

        ListSchedulesRequest request = new ListSchedulesRequest()
        request.setApiKey("customer1")
        request.setExpand("rotation_test")

        try{
            def response = opsgenieClient.schedule().listSchedules(request)
        } catch (OpsGenieClientValidationException exception){
            assertEquals("Invalid Values for expand",exception.getMessage())
        }
    }


    @Test
    public void testWhoIsOnCallSuccessfully() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "id_schedule1");
        jsonContent.put(TestConstants.API.NAME, "schedule1");
        jsonContent.put(TestConstants.API.TYPE, "schedule");
        jsonContent.put(TestConstants.API.PARTICIPANTS, [
                [name: "group1", type: "group", id: "id_group1", participants: [
                        [name: "user3@xyz.com", type: "user", forwarded: false, id: "id_user3"],
                        [name: "user4@xyz.com", type: "user", forwarded: false, id: "id_user4"]
                ]],
                [name: "user1@xyz.com", type: "user", forwarded: false, id: "id_user1"],
                [name: "user2@xyz.com", type: "user", forwarded: true, id: "id_user2", forwardedFrom: [
                        id  : "id_user7",
                        name: "user7@xyz.com",
                        type: "user"
                ]],
                [name: "escalation1", type: "escalation", id: "esc1", participants: [
                        [name: "user5@xyz.com", type: "user", forwarded: false, id: "id_user5", escalationTime: 2, notifyType: "default"],
                        [name: "group2", type: "group", id: "id_group2", escalationTime: 4, notifyType: "default", participants: [
                                [name: "user6@xyz.com", type: "user", forwarded: false, id: "id_user6"]
                        ]],
                        [name: "schedule2", type: "schedule", id: "sched2", escalationTime: 54, notifyType: "next"]
                ]],
                [name: "tim", type: "team", id: "tim1"]
        ]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        WhoIsOnCallRequest request = new WhoIsOnCallRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().whoIsOnCall(request)
        assertEquals(1, response.getTook())
        assertEquals("schedule1", response.getWhoIsOnCall().name)
        assertEquals("id_schedule1", response.getWhoIsOnCall().id)
        assertEquals("schedule", response.getWhoIsOnCall().type)
        assertEquals(5, response.getWhoIsOnCall().participants.size())

        int index = 0;
        jsonContent[TestConstants.API.PARTICIPANTS].each {
            assertEquals(it.name, response.whoIsOnCall.participants[index].name)
            assertEquals(it.type, response.whoIsOnCall.participants[index].type)
            assertEquals(it.id, response.whoIsOnCall.participants[index].id)
            if (it.type == "user") {
                assertEquals(it.forwarded, response.whoIsOnCall.participants[index].forwarded)
            }
            index++;
        }

        WhoIsOnCall forwardedUserData = response.whoIsOnCall.participants.find {
            it.name == "user2@xyz.com"
        }
        assertNotNull(forwardedUserData);

        assertEquals(true, forwardedUserData.forwarded);

        WhoIsOnCall forwardedFromData = forwardedUserData.forwardedFrom;
        assertNotNull(forwardedFromData);

        assertEquals("user7@xyz.com", forwardedFromData.name)
        assertEquals("user", forwardedFromData.type)
        assertEquals("id_user7", forwardedFromData.id)

        WhoIsOnCall groupData = response.whoIsOnCall.participants.find {
            it.name == "group1"
        };
        assertNotNull(groupData);

        assertEquals(2, groupData.participants.size())
        assertEquals("user3@xyz.com", groupData.participants[0].name)
        assertEquals("user4@xyz.com", groupData.participants[1].name)

        WhoIsOnCall escalationData = response.whoIsOnCall.participants.find {
            it.name == "escalation1"
        };
        assertNotNull(escalationData);

        assertEquals(3, escalationData.participants.size())

        assertEquals("user5@xyz.com", escalationData.participants[0].name)
        assertEquals(2, escalationData.participants[0].escalationTime)
        assertEquals("default", escalationData.participants[0].notifyType)

        WhoIsOnCall escGroupData = escalationData.participants.find { it.name == "group2" };
        assertNotNull(escGroupData);

        assertEquals("group2", escGroupData.name)
        assertEquals(4, escGroupData.escalationTime)
        assertEquals("default", escGroupData.notifyType)

        assertEquals(1, escGroupData.participants.size())
        assertEquals("user6@xyz.com", escGroupData.participants[0].name)

        def scheduleData = escalationData.participants.find { it.name == "schedule2" }
        assertNotNull(scheduleData)
        assertEquals(54, scheduleData.escalationTime)
        assertEquals("next", scheduleData.notifyType)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertFalse(requestSent.getParameters().containsKey(TestConstants.API.TIMEZONE))
        assertFalse(requestSent.getParameters().containsKey(TestConstants.API.TIME))
        assertEquals("/v1.1/json/schedule/whoIsOnCall", requestSent.getUrl())
    }

    @Test
    public void testFlatWhoIsOnCallSuccessfully() throws Exception {
        def recipientList = [
                "user1@xyz.com",
                "user2@xyz.com",
                "user3@xyz.com",
                "user4@xyz.com"
        ]
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "id_schedule1");
        jsonContent.put(TestConstants.API.NAME, "schedule1");
        jsonContent.put(TestConstants.API.TYPE, "schedule");
        jsonContent.put(TestConstants.API.RECIPIENTS, recipientList);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        FlatWhoIsOnCallRequest request = new FlatWhoIsOnCallRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().flatWhoIsOnCall(request)
        assertEquals(1, response.getTook())
        assertEquals("schedule1", response.getWhoIsOnCall().name)
        assertEquals("id_schedule1", response.getWhoIsOnCall().id)
        assertEquals("schedule", response.getWhoIsOnCall().type)
        assertEquals(recipientList, response.getWhoIsOnCall().recipients)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertFalse(requestSent.getParameters().containsKey(TestConstants.API.TIMEZONE))
        assertFalse(requestSent.getParameters().containsKey(TestConstants.API.TIME))
        assertEquals("true", requestSent.getParameters()[TestConstants.API.FLAT])
        assertEquals("/v1.1/json/schedule/whoIsOnCall", requestSent.getUrl())
    }

    @Test
    public void testWhoIsOnCallSuccessfullyWithOptionalParams() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.NAME, "schedule1");
        jsonContent.put(TestConstants.API.PARTICIPANTS, [
                [name: "group1", type: "group"],
                [name: "user1@xyz.com", type: "user", forwarded: false],
                [name: "user2@xyz.com", type: "user", forwarded: true],
                [name: "escalation1", type: "escalation"],
                [name: "tim", type: "team"]
        ]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        WhoIsOnCallRequest request = new WhoIsOnCallRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        request.setTime(new Date(System.currentTimeMillis()));
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().whoIsOnCall(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.NAME], response.getWhoIsOnCall().name)
        assertEquals(5, response.getWhoIsOnCall().participants.size())

        int index = 0;
        jsonContent[TestConstants.API.PARTICIPANTS].each {
            assertEquals(it.name, response.whoIsOnCall.participants[index].name)
            assertEquals(it.type, response.whoIsOnCall.participants[index].type)
            if (it.type == "user") {
                assertEquals(it.forwarded, response.whoIsOnCall.participants[index].forwarded)
            }
            index++;
        }


        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(request.getTimeZone())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        //Here there is problem in test.
        //Test uses QueryStringDecoder to decode url. However, although '+' sign is valid
        // and not to be change to space in query part of URI, QueryStringDecoder converts it to space character.
        // Therefore, I have changed this assertion.
        assertEquals(request.getTimeZone().getID().replace("+", " "), requestSent.getParameters()[TestConstants.API.TIMEZONE])
        assertEquals(sdf.format(request.getTime()), requestSent.getParameters()[TestConstants.API.TIME])
        assertEquals("false", requestSent.getParameters()[TestConstants.API.FLAT])
        assertEquals("/v1.1/json/schedule/whoIsOnCall", requestSent.getUrl())
    }

    @Test
    public void testWhoIsOnCallThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "whoIsOnCall", new WhoIsOnCallRequest(id: "oncall"))
    }

    @Test
    public void testListWhoIsOnCallSuccessfully() throws Exception {
        Map oncall1Content = new HashMap();
        oncall1Content.put(TestConstants.API.NAME, "schedule1");
        oncall1Content.put(TestConstants.API.PARTICIPANTS, [
                [name: "group1", type: "group"],
                [name: "user1@xyz.com", type: "user", forwarded: false],
                [name: "user2@xyz.com", type: "user", forwarded: true],
                [name: "escalation1", type: "escalation", participants: [[name: "schedule2", type: "schedule"]]],
                [name: "tim", type: "team"]
        ]);
        Map oncall2Content = new HashMap();
        oncall2Content.put(TestConstants.API.NAME, "schedule2");
        oncall2Content.put(TestConstants.API.PARTICIPANTS, [
                [name: "group2", type: "group"],
        ]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([oncalls: [oncall1Content, oncall2Content]]).getBytes(), 200, "application/json; charset=utf-8"))

        ListWhoIsOnCallRequest request = new ListWhoIsOnCallRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().listWhoIsOnCall(request)

        def whoIsOnCall = response.whoIsOnCallList.find { it.name == "schedule1" }

        assertEquals(5, whoIsOnCall.getParticipants().size())
        def escalationData = whoIsOnCall.getParticipants().find { it.name == "escalation1" }
        assertNotNull(escalationData)
        assertEquals(1, escalationData.participants.size())
        assertEquals("schedule2", escalationData.participants[0].name)

        int index = 0;
        oncall1Content[TestConstants.API.PARTICIPANTS].each {
            assertEquals(it.getAt("name"), whoIsOnCall.participants[index].name)
            assertEquals(it.getAt("type"), whoIsOnCall.participants[index].type)
            if (it.getAt("type") == "user") {
                assertEquals(it.getAt("forwarded"), whoIsOnCall.participants[index].forwarded)
            }
            index++;
        }

        whoIsOnCall = response.whoIsOnCallList.find { it.name == "schedule2" }
        assertEquals(1, whoIsOnCall.participants.size())
        assertEquals("group2", whoIsOnCall.participants[0].name)
        assertEquals("group", whoIsOnCall.participants[0].type)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("false", requestSent.getParameters()[TestConstants.API.FLAT])
        assertEquals("/v1.1/json/schedule/whoIsOnCall", requestSent.getUrl())
    }

    @Test
    public void testFlatListWhoIsOnCallSuccessfully() throws Exception {
        Map oncall1Content = new HashMap();
        def oncall1Recipients = [
                "user1@xyz.com",
                "user2@xyz.com"
        ]
        oncall1Content.put(TestConstants.API.NAME, "schedule1");
        oncall1Content.put(TestConstants.API.RECIPIENTS, oncall1Recipients);

        Map oncall2Content = new HashMap();
        def oncall2Recipients = [
                "user3@xyz.com",
                "user4@xyz.com"
        ]
        oncall2Content.put(TestConstants.API.NAME, "schedule2");
        oncall2Content.put(TestConstants.API.RECIPIENTS, oncall2Recipients);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([oncalls: [oncall1Content, oncall2Content]]).getBytes(), 200, "application/json; charset=utf-8"))

        ListFlatWhoIsOnCallRequest request = new ListFlatWhoIsOnCallRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().listFlatWhoIsOnCall(request)

        def whoIsOnCall = response.whoIsOnCallList.find { it.name == "schedule1" }
        assertEquals(oncall1Recipients, whoIsOnCall.recipients)

        whoIsOnCall = response.whoIsOnCallList.find { it.name == "schedule2" }
        assertEquals(oncall2Recipients, whoIsOnCall.recipients)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("true", requestSent.getParameters()[TestConstants.API.FLAT])
        assertEquals("/v1.1/json/schedule/whoIsOnCall", requestSent.getUrl())
    }

    @Test
    public void testListWhoIsOnCallThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "listWhoIsOnCall", new ListWhoIsOnCallRequest())
    }

    @Test
    public void testAddScheduleOverrideSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"alias\":\"alias1\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddScheduleOverrideRequest request = new AddScheduleOverrideRequest();
        request.setApiKey("customer1");
        request.setAlias("override1");
        request.setSchedule("schedule1");
        request.setStartDate(new Date());
        request.setUser("user1");
        request.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        request.setEndDate(new Date(System.currentTimeMillis() + 100000000l));
        request.setRotationIds(["rotation1"])

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().addScheduleOverride(request)
        assertEquals("alias1", response.getAlias())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/schedule/override", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(request.getTimeZone());

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getAlias(), jsonContent[TestConstants.API.ALIAS])
        assertEquals(sdf.format(request.getStartDate()), jsonContent[TestConstants.API.START_DATE])
        assertEquals(sdf.format(request.getEndDate()), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getUser(), jsonContent[TestConstants.API.USER])
        assertEquals(request.getSchedule(), jsonContent[TestConstants.API.SCHEDULE])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(request.getRotationIds(), jsonContent[TestConstants.API.ROTATION_IDS])
    }

    @Test
    public void testAddScheduleOverrideThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "addScheduleOverride", new AddScheduleOverrideRequest())
    }

    @Test
    public void testUpdateScheduleOverrideSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"alias\":\"alias1\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateScheduleOverrideRequest request = new UpdateScheduleOverrideRequest();
        request.setApiKey("customer1");
        request.setAlias("alias1");
        request.setSchedule("schedule1");
        request.setStartDate(new Date());
        request.setUser("user1");
        request.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        request.setEndDate(new Date(System.currentTimeMillis() + 100000000l));
        request.setRotationIds(["rot1"])

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().updateScheduleOverride(request)
        assertEquals("alias1", response.getAlias())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/schedule/override", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(request.getTimeZone());

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getAlias(), jsonContent[TestConstants.API.ALIAS])
        assertEquals(sdf.format(request.getStartDate()), jsonContent[TestConstants.API.START_DATE])
        assertEquals(sdf.format(request.getEndDate()), jsonContent[TestConstants.API.END_DATE])
        assertEquals(request.getUser(), jsonContent[TestConstants.API.USER])
        assertEquals(request.getSchedule(), jsonContent[TestConstants.API.SCHEDULE])
        assertEquals(request.getTimeZone().getID(), jsonContent[TestConstants.API.TIMEZONE])
        assertEquals(request.getRotationIds(), jsonContent[TestConstants.API.ROTATION_IDS])
    }

    @Test
    public void testUpdateScheduleOverrideThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "updateScheduleOverride", new UpdateScheduleOverrideRequest())
    }

    @Test
    public void testDeleteScheduleOverrideSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteScheduleOverrideRequest request = new DeleteScheduleOverrideRequest();
        request.setAlias("alias1");
        request.setSchedule("schedule1");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().deleteScheduleOverride(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]

        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/schedule/override", requestSent.getUrl())

        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS]);
        assertEquals(request.getSchedule(), requestSent.getParameters()[TestConstants.API.SCHEDULE]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }

    @Test
    public void testDeleteScheduleOverrideThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "deleteScheduleOverride", new DeleteScheduleOverrideRequest())
    }

    @Test
    public void testGetScheduleOverrideSuccessfully() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        def startDate = new Date()
        def endDate = new Date(System.currentTimeMillis() + 1000000)

        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ALIAS, "alias1");
        jsonContent.put(TestConstants.API.START_DATE, sdf.format(startDate));
        jsonContent.put(TestConstants.API.END_DATE, sdf.format(endDate));
        jsonContent.put(TestConstants.API.USER, "user1");
        jsonContent.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        jsonContent.put(TestConstants.API.ROTATION_IDS, ["rot1"]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetScheduleOverrideRequest request = new GetScheduleOverrideRequest();
        request.setAlias("alias1");
        request.setSchedule("schedule1");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().getScheduleOverride(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.ALIAS], response.getScheduleOverride().alias)
        assertEquals(jsonContent[TestConstants.API.ROTATION_IDS], response.getScheduleOverride().rotationIds)
        assertEquals(jsonContent[TestConstants.API.USER], response.getScheduleOverride().user)
        assertEquals(sdf.format(startDate), sdf.format(response.getScheduleOverride().startDate))
        assertEquals(sdf.format(endDate), sdf.format(response.getScheduleOverride().endDate))
        assertEquals(sdf.getTimeZone(), response.getScheduleOverride().timeZone)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getAlias(), requestSent.getParameters()[TestConstants.API.ALIAS]);
        assertEquals(request.getSchedule(), requestSent.getParameters()[TestConstants.API.SCHEDULE]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/schedule/override", requestSent.getUrl())
    }

    @Test
    public void testGetScheduleOverrideThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "getScheduleOverride", new GetScheduleOverrideRequest())
    }

    @Test
    public void testListScheduleOverridesSuccessfully() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.Common.API_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));

        def startDate1 = new Date()
        def endDate1 = new Date(System.currentTimeMillis() + 1000000)
        def startDate2 = new Date(System.currentTimeMillis() + 2000000)
        def endDate2 = new Date(System.currentTimeMillis() + 3000000)

        Map jsonContent1 = new HashMap();
        jsonContent1.put(TestConstants.API.START_DATE, sdf.format(startDate1));
        jsonContent1.put(TestConstants.API.END_DATE, sdf.format(endDate1));
        jsonContent1.put(TestConstants.API.USER, "user1");
        jsonContent1.put(TestConstants.API.ALIAS, "alias1");
        jsonContent1.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        Map jsonContent2 = new HashMap();
        jsonContent2.put(TestConstants.API.START_DATE, sdf.format(startDate2));
        jsonContent2.put(TestConstants.API.END_DATE, sdf.format(endDate2));
        jsonContent2.put(TestConstants.API.USER, "user2");
        jsonContent2.put(TestConstants.API.ALIAS, "alias2");
        jsonContent2.put(TestConstants.API.TIMEZONE, sdf.getTimeZone().getID());
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson([took: 1, overrides: [jsonContent1, jsonContent2]]).getBytes(), 200, "application/json; charset=utf-8"))

        ListScheduleOverridesRequest request = new ListScheduleOverridesRequest();
        request.setSchedule("schedule1");
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.schedule().listScheduleOverrides(request)
        assertEquals(1, response.getTook())
        def overrides = response.getOverrides();
        assertEquals(2, overrides.size())
        def override = overrides.find { it.alias == jsonContent1[TestConstants.API.ALIAS] }
        assertEquals(jsonContent1[TestConstants.API.USER], override.user)
        assertEquals(sdf.format(startDate1), sdf.format(override.startDate))
        assertEquals(sdf.format(endDate1), sdf.format(override.endDate))
        assertEquals(TimeZone.getTimeZone("GMT-5"), override.timeZone)

        override = overrides.find { it.alias == jsonContent2[TestConstants.API.ALIAS] }
        assertEquals(jsonContent2[TestConstants.API.USER], override.user)
        assertEquals(sdf.format(startDate2), sdf.format(override.startDate))
        assertEquals(sdf.format(endDate2), sdf.format(override.endDate))
        assertEquals(TimeZone.getTimeZone("GMT-3"), override.timeZone)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getSchedule(), requestSent.getParameters()[TestConstants.API.SCHEDULE]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/schedule/override", requestSent.getUrl())
    }

    @Test
    public void testListScheduleOverridesThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.schedule(), "listScheduleOverrides", new ListScheduleOverridesRequest())
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/ScheduleOpsGenieClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }
}

