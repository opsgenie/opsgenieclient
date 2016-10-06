package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.*
import com.ifountain.opsgenie.client.model.notification_rule.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by MEHMET MUSTAFA DEMİR
 * Date: 7/29/16
 * Time: 11:49 AM
 */
class NotificationRuleOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void testAddNotificationRuleSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNotificationRuleRequest request = new AddNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");
        request.setName("notificationRule1")
        request.setActionType(NotificationRule.ActionType.NEW_ALERT);
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        Condition condition1 = new Condition();
        condition1.setNot(false);
        condition1.setField(Condition.Field.MESSAGE);
        condition1.setOperation(Condition.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        Condition condition2 = new Condition();
        condition2.setNot(true);
        condition2.setField(Condition.Field.EXTRA_PROPERTIES);
        condition2.setOperation(Condition.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1, condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);
        request.setApplyOrder(2);

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])
        request.setSchedules(["schedule1", "schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRule(request);
        assertEquals("notificationRule1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("user1", jsonContent[TestConstants.API.USERNAME])
        assertEquals("notificationRule1", jsonContent[TestConstants.API.NAME])
        assertEquals("New Alert", jsonContent[TestConstants.API.ACTION_TYPE])
        assertEquals("Match All Conditions", jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false, con1[TestConstants.API.NOT])
        assertEquals("message", con1[TestConstants.API.FIELD])
        assertEquals("Contains", con1[TestConstants.API.OPERATION])
        assertEquals("night", con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true, con2[TestConstants.API.NOT])
        assertEquals("extraProperties", con2[TestConstants.API.FIELD])
        assertEquals("Contains Key", con2[TestConstants.API.OPERATION])
        assertEquals("prop1", con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores, ["15 mins", "Just Before"]);
        assertEquals(request.getApplyOrder(), jsonContent[TestConstants.API.APPLY_ORDER])

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);

        assertEquals(request.getSchedules(), jsonContent[TestConstants.API.SCHEDULES])
    }

    @Test
    public void testAddNotificationRuleSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNotificationRuleRequest request = new AddNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setName("notificationRule1")
        request.setActionType(NotificationRule.ActionType.NEW_ALERT);
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        Condition condition1 = new Condition();
        condition1.setNot(false);
        condition1.setField(Condition.Field.MESSAGE);
        condition1.setOperation(Condition.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        Condition condition2 = new Condition();
        condition2.setNot(true);
        condition2.setField(Condition.Field.EXTRA_PROPERTIES);
        condition2.setOperation(Condition.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1, condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);
        request.setApplyOrder(2);

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])
        request.setSchedules(["schedule1", "schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRule(request);
        assertEquals("notificationRule1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("customer1", jsonContent[TestConstants.API.API_KEY])
        assertEquals("user1", jsonContent[TestConstants.API.USER_ID])
        assertEquals("notificationRule1", jsonContent[TestConstants.API.NAME])
        assertEquals("New Alert", jsonContent[TestConstants.API.ACTION_TYPE])
        assertEquals("Match All Conditions", jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false, con1[TestConstants.API.NOT])
        assertEquals("message", con1[TestConstants.API.FIELD])
        assertEquals("Contains", con1[TestConstants.API.OPERATION])
        assertEquals("night", con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true, con2[TestConstants.API.NOT])
        assertEquals("extraProperties", con2[TestConstants.API.FIELD])
        assertEquals("Contains Key", con2[TestConstants.API.OPERATION])
        assertEquals("prop1", con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores, ["15 mins", "Just Before"]);
        assertEquals(request.getApplyOrder(), jsonContent[TestConstants.API.APPLY_ORDER])

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);

        assertEquals(request.getSchedules(), jsonContent[TestConstants.API.SCHEDULES])
    }

    @Test
    public void testUpdateNotificationRuleSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateNotificationRuleRequest request = new UpdateNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");
        request.setId("notificationRuleId");

        request.setName("notificationRule1")
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        Condition condition1 = new Condition();
        condition1.setNot(false);
        condition1.setField(Condition.Field.MESSAGE);
        condition1.setOperation(Condition.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        Condition condition2 = new Condition();
        condition2.setNot(true);
        condition2.setField(Condition.Field.EXTRA_PROPERTIES);
        condition2.setOperation(Condition.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1, condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])
        request.setSchedules(["schedule1", "schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().updateNotificationRule(request);
        assertEquals("notificationRule1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getConditionMatchType().value(), jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false, con1[TestConstants.API.NOT])
        assertEquals(Condition.Field.MESSAGE.value(), con1[TestConstants.API.FIELD])
        assertEquals(Condition.Operation.CONTAINS.value(), con1[TestConstants.API.OPERATION])
        assertEquals("night", con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true, con2[TestConstants.API.NOT])
        assertEquals(Condition.Field.EXTRA_PROPERTIES.value(), con2[TestConstants.API.FIELD])
        assertEquals(Condition.Operation.CONTAINS_KEY.value(), con2[TestConstants.API.OPERATION])
        assertEquals("prop1", con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores, ["15 mins", "Just Before"]);

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);

        assertEquals(request.getSchedules(), jsonContent[TestConstants.API.SCHEDULES])
    }

    @Test
    public void testUpdateNotificationRuleSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateNotificationRuleRequest request = new UpdateNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setId("notificationRuleId");

        request.setName("notificationRule1")
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        Condition condition1 = new Condition();
        condition1.setNot(false);
        condition1.setField(Condition.Field.MESSAGE);
        condition1.setOperation(Condition.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        Condition condition2 = new Condition();
        condition2.setNot(true);
        condition2.setField(Condition.Field.EXTRA_PROPERTIES);
        condition2.setOperation(Condition.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1, condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])
        request.setSchedules(["schedule1", "schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().updateNotificationRule(request);
        assertEquals("notificationRule1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getConditionMatchType().value(), jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false, con1[TestConstants.API.NOT])
        assertEquals(Condition.Field.MESSAGE.value(), con1[TestConstants.API.FIELD])
        assertEquals(Condition.Operation.CONTAINS.value(), con1[TestConstants.API.OPERATION])
        assertEquals("night", con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true, con2[TestConstants.API.NOT])
        assertEquals(Condition.Field.EXTRA_PROPERTIES.value(), con2[TestConstants.API.FIELD])
        assertEquals(Condition.Operation.CONTAINS_KEY.value(), con2[TestConstants.API.OPERATION])
        assertEquals("prop1", con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores, ["15 mins", "Just Before"]);

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);

        assertEquals(request.getSchedules(), jsonContent[TestConstants.API.SCHEDULES])
    }

    @Test
    public void testDeleteNotificationRuleSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DeleteNotificationRuleRequest request = new DeleteNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("contact1Id");
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().deleteNotificationRule(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME])
    }

    @Test
    public void testDeleteNotificationRuleSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DeleteNotificationRuleRequest request = new DeleteNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("contact1Id");
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().deleteNotificationRule(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())

        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID])
    }

    @Test
    public void testEnableNotificationRuleWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableNotificationRuleRequest request = new EnableNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().enableNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
    }

    @Test
    public void testEnableNotificationRuleWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableNotificationRuleRequest request = new EnableNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().enableNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
    }

    @Test
    public void testDisableNotificationRuleWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableNotificationRuleRequest request = new DisableNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().disableNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
    }

    @Test
    public void testDisableNotificationRuleWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableNotificationRuleRequest request = new DisableNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().disableNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
    }

    @Test
    public void testChangeNotificationRuleOrderWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        ChangeNotificationRuleOrderRequest request = new ChangeNotificationRuleOrderRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUsername("user1")
        request.setApplyOrder(1);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().changeNotificationRuleOrder(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/changeOrder", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getApplyOrder(), jsonContent[TestConstants.API.APPLY_ORDER])
    }

    @Test
    public void testChangeNotificationRuleOrderWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        ChangeNotificationRuleOrderRequest request = new ChangeNotificationRuleOrderRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUserId("user1")
        request.setApplyOrder(1);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().changeNotificationRuleOrder(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/changeOrder", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getApplyOrder(), jsonContent[TestConstants.API.APPLY_ORDER])
    }

    @Test
    public void testRepeatNotificationRuleWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        RepeatNotificationRuleRequest request = new RepeatNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUsername("user1")
        request.setEnabled(true);
        request.setLoopAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().repeatNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/repeat", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getLoopAfter(), jsonContent[TestConstants.API.LOOP_AFTER])
        assertEquals(request.getEnabled(), jsonContent[TestConstants.API.ENABLED])
    }

    @Test
    public void testRepeatNotificationRuleWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        RepeatNotificationRuleRequest request = new RepeatNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId("notificationRule1")
        request.setUserId("user1")
        request.setEnabled(true);
        request.setLoopAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().repeatNotificationRule(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/repeat", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getLoopAfter(), jsonContent[TestConstants.API.LOOP_AFTER])
        assertEquals(request.getEnabled(), jsonContent[TestConstants.API.ENABLED])
    }

    @Test
    public void testGetNotificationRuleSuccessfullyWithUserName() throws Exception {
        def condition1 = [
                not          : false,
                field        : "message",
                expectedValue: "new",
                operation    : "Contains"
        ]
        def condition2 = [
                not          : true,
                field        : "actions",
                expectedValue: "ping",
                operation    : "Contains"
        ]
        def step1 = [
                method   : "voice",
                to       : "1-9999999999",
                id       : "6419cef5-ca1f-4842-9ddc-518952c60c2b",
                sendAfter: 0,
                enabled  : true
        ]

        def jsonResponse = [
                actionType        : "New Alert",
                name              : "New Alert",
                restrictions      : [
                        [endHour: 1, startDay: "MONDAY", startHour: 2, endDay: "TUESDAY", startMinute: 3, endMinute: 4]
                ],
                id                : "67c4855a-4c1b-4cbf-9be2-87eb212760e9",
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 0
        ]
        jsonResponse.put("conditions", [condition1, condition2])
        jsonResponse.put("steps", [step1])


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonResponse).getBytes(), 200, "application/json; charset=utf-8"))

        GetNotificationRuleRequest request = new GetNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId(jsonResponse[TestConstants.API.ID]);
        request.setUsername("user1");


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().getNotificationRule(request)

        assertEquals(jsonResponse[TestConstants.API.ACTION_TYPE], response.getNotificationRule().getActionType().value())
        assertEquals(jsonResponse[TestConstants.API.NAME], response.getNotificationRule().getName())


        assertEquals(jsonResponse[TestConstants.API.SCHEDULES], response.getNotificationRule().getSchedules())
        assertEquals(jsonResponse[TestConstants.API.NAME], response.getNotificationRule().getName())
        assertEquals(1, response.getNotificationRule().getRestrictions().size());

        Restriction restriction = response.getNotificationRule().getRestrictions().get(0);
        assertEquals(1, restriction.getEndHour());
        assertEquals("monday", restriction.getStartDay().name());
        assertEquals(2, restriction.getStartHour());
        assertEquals("tuesday", restriction.getEndDay().name());
        assertEquals(3, restriction.getStartMinute());
        assertEquals(4, restriction.getEndMinute());

        assertEquals(jsonResponse[TestConstants.API.ID], response.getNotificationRule().getId());
        assertEquals(2, response.getNotificationRule().getConditions().size());
        Condition condition = response.getNotificationRule().getConditions().find {
            it.expectedValue == condition1.expectedValue
        }
        assertEquals(condition1.field, condition.getField().value())
        assertEquals(condition1.expectedValue, condition.getExpectedValue())
        assertEquals(condition1.operation, condition.getOperation().value())
        assertEquals(condition1.not, condition.getNot())

        condition = response.getNotificationRule().getConditions().find { it.expectedValue == condition2.expectedValue }
        assertEquals(condition2.field, condition.getField().value())
        assertEquals(condition2.expectedValue, condition.getExpectedValue())
        assertEquals(condition2.operation, condition.getOperation().value())
        assertEquals(condition2.not, condition.getNot())


        assertEquals(1, response.getNotificationRule().getSteps().size());
        NotificationRuleStep step = response.getNotificationRule().getSteps().get(0);
        assertEquals(step1.method, step.getMethod().value())
        assertEquals(step1.to, step.getTo())
        assertEquals(step1.id, step.getId())
        assertEquals(step1.sendAfter, step.getSendAfter())
        assertEquals(step1.enabled, step.getEnabled())


        assertEquals(jsonResponse[TestConstants.API.ID], response.getNotificationRule().getId())
        assertEquals(jsonResponse[TestConstants.API.ENABLED], response.getNotificationRule().getEnabled())
        assertEquals(jsonResponse[TestConstants.API.CONDITION_MATCH_TYPE], response.getNotificationRule().getConditionMatchType().value())
        assertEquals(jsonResponse[TestConstants.API.LOOP_AFTER], response.getNotificationRule().getLoopAfter())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())

    }

    @Test
    public void testGetNotificationRuleSuccessfullyWithUserId() throws Exception {
        def condition1 = [
                not          : false,
                field        : "message",
                expectedValue: "new",
                operation    : "Contains"
        ]
        def condition2 = [
                not          : true,
                field        : "actions",
                expectedValue: "ping",
                operation    : "Contains"
        ]
        def step1 = [
                method   : "voice",
                to       : "1-9999999999",
                id       : "6419cef5-ca1f-4842-9ddc-518952c60c2b",
                sendAfter: 0,
                enabled  : true
        ]

        def jsonResponse = [
                actionType        : "New Alert",
                name              : "New Alert",
                restrictions      : [
                        [endHour: 1, startDay: "MONDAY", startHour: 2, endDay: "TUESDAY", startMinute: 3, endMinute: 4]
                ],
                id                : "67c4855a-4c1b-4cbf-9be2-87eb212760e9",
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 0
        ]
        jsonResponse.put("conditions", [condition1, condition2])
        jsonResponse.put("steps", [step1])


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonResponse).getBytes(), 200, "application/json; charset=utf-8"))

        GetNotificationRuleRequest request = new GetNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setId(jsonResponse[TestConstants.API.ID]);
        request.setUserId("user1");


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().getNotificationRule(request)

        assertEquals(jsonResponse[TestConstants.API.ACTION_TYPE], response.getNotificationRule().getActionType().value())
        assertEquals(jsonResponse[TestConstants.API.NAME], response.getNotificationRule().getName())


        assertEquals(jsonResponse[TestConstants.API.SCHEDULES], response.getNotificationRule().getSchedules())
        assertEquals(jsonResponse[TestConstants.API.NAME], response.getNotificationRule().getName())
        assertEquals(1, response.getNotificationRule().getRestrictions().size());

        Restriction restriction = response.getNotificationRule().getRestrictions().get(0);
        assertEquals(1, restriction.getEndHour());
        assertEquals("monday", restriction.getStartDay().name());
        assertEquals(2, restriction.getStartHour());
        assertEquals("tuesday", restriction.getEndDay().name());
        assertEquals(3, restriction.getStartMinute());
        assertEquals(4, restriction.getEndMinute());

        assertEquals(jsonResponse[TestConstants.API.ID], response.getNotificationRule().getId());
        assertEquals(2, response.getNotificationRule().getConditions().size());
        Condition condition = response.getNotificationRule().getConditions().find {
            it.expectedValue == condition1.expectedValue
        }
        assertEquals(condition1.field, condition.getField().value())
        assertEquals(condition1.expectedValue, condition.getExpectedValue())
        assertEquals(condition1.operation, condition.getOperation().value())
        assertEquals(condition1.not, condition.getNot())

        condition = response.getNotificationRule().getConditions().find { it.expectedValue == condition2.expectedValue }
        assertEquals(condition2.field, condition.getField().value())
        assertEquals(condition2.expectedValue, condition.getExpectedValue())
        assertEquals(condition2.operation, condition.getOperation().value())
        assertEquals(condition2.not, condition.getNot())


        assertEquals(1, response.getNotificationRule().getSteps().size());
        NotificationRuleStep step = response.getNotificationRule().getSteps().get(0);
        assertEquals(step1.method, step.getMethod().value())
        assertEquals(step1.to, step.getTo())
        assertEquals(step1.id, step.getId())
        assertEquals(step1.sendAfter, step.getSendAfter())
        assertEquals(step1.enabled, step.getEnabled())


        assertEquals(jsonResponse[TestConstants.API.ID], response.getNotificationRule().getId())
        assertEquals(jsonResponse[TestConstants.API.ENABLED], response.getNotificationRule().getEnabled())
        assertEquals(jsonResponse[TestConstants.API.CONDITION_MATCH_TYPE], response.getNotificationRule().getConditionMatchType().value())
        assertEquals(jsonResponse[TestConstants.API.LOOP_AFTER], response.getNotificationRule().getLoopAfter())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
    }


    @Test
    public void testListNotificationRuleSuccessfullyWithUserName() throws Exception {
        def rule1 = [
                actionType  : "Schedule Start",
                applyOrder  : 0,
                schedules   : ["network_team_schedule"],
                name        : "Schedule Start Name",
                notifyBefore: ["Just Before", "15 mins"],
                restrictions: [[endHour: 0, startDay: "sunday", startHour: 0, endDay: "monday", startMinute: 0, endMinute: 0]],
                id          : "5ef5cc7f-23f4-4168-9f0f-d543d0c325b4",
                steps       : [
                        [
                                method   : "email",
                                to       : "john@opsgenie.com",
                                id       : "2dae31b1-5e89-4c33-a05f-d1f0e746c4db",
                                sendAfter: 0,
                                enabled  : true
                        ]
                ],
                "enabled"   : true
        ]
        def rule2 = [
                actionType        : "New Alert",
                applyOrder        : 0,
                name              : "New Alert",
                restrictions      : [
                        endHour    : 18,
                        startHour  : 0,
                        startMinute: 0,
                        endMinute  : 0
                ],
                id                : "67c4855a-4c1b-4cbf-9be2-87eb212760e9",
                conditions        : [
                        [
                                not          : false,
                                field        : "message",
                                expectedValue: "new",
                                operation    : "Contains"
                        ],
                        [
                                not          : false,
                                field        : "actions",
                                expectedValue: "ping",
                                operation    : "Contains"
                        ]
                ],
                steps             : [
                        [
                                method   : "voice",
                                to       : "1-9999999999",
                                id       : "6419cef5-ca1f-4842-9ddc-518952c60c2b",
                                sendAfter: 0,
                                enabled  : true
                        ]
                ],
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 0
        ]

        def rule3 = [
                actionType        : "New Alert",
                applyOrder        : 1,
                name              : "New Alert Night",
                restrictions      : [
                        endHour    : 18,
                        startHour  : 15,
                        startMinute: 4,
                        endMinute  : 58
                ],
                id                : "d609c2d4-c7e0-4c3c-b339-40bb58f89963",
                conditions        : [
                        [
                                not          : false,
                                field        : "message",
                                expectedValue: "asdf",
                                operation    : "Equals"
                        ],
                        [
                                not          : true,
                                field        : "extraProperties",
                                expectedValue: "asdf",
                                operation    : "Contains Key"
                        ]
                ],
                steps             : [
                        [
                                method   : "email",
                                to       : "john@opsgenie.com",
                                id       : "bd96aa09-e161-4c7e-8d15-ba5e55b7b1e2",
                                sendAfter: 5,
                                enabled  : true
                        ]
                ],
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 5
        ]


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson("rules": [rule1, rule2, rule3]).getBytes(), 200, "application/json; charset=utf-8"))

        ListNotificationRulesRequest request = new ListNotificationRulesRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().listNotificationRule(request)
        assertEquals(3, response.getRules().size())
        NotificationRule inputRule1 = response.getRules().find { it.name == rule1.name }
        assertEquals(inputRule1.getActionType().value(), rule1.actionType);
        assertEquals(inputRule1.getApplyOrder(), rule1.applyOrder);
        assertEquals(inputRule1.getSchedules(), rule1.schedules);
        assertEquals(inputRule1.getName(), rule1.name);
        assertEquals(inputRule1.getNotifyBefore().get(0).value(), rule1.notifyBefore.get(0));
        assertEquals(inputRule1.getNotifyBefore().get(1).value(), rule1.notifyBefore.get(1));
        assertEquals(1, inputRule1.getRestrictions().size())
        def inputRestriction = inputRule1.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule1.restrictions[0].endHour);
        assertEquals(inputRestriction.getStartDay().name(), rule1.restrictions[0].startDay);
        assertEquals(inputRestriction.getStartHour(), rule1.restrictions[0].startHour);
        assertEquals(inputRestriction.getEndDay().name(), rule1.restrictions[0].endDay);
        assertEquals(inputRestriction.getStartMinute(), rule1.restrictions[0].startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule1.restrictions[0].endMinute);
        assertEquals(inputRule1.getId(), rule1.id);
        assertEquals(inputRule1.getSteps().size(), rule1.steps.size());
        assertEquals(inputRule1.getSteps().get(0).getMethod().value(), rule1.steps.get(0).method);
        assertEquals(inputRule1.getSteps().get(0).getTo(), rule1.steps.get(0).to);
        assertEquals(inputRule1.getSteps().get(0).getId(), rule1.steps.get(0).id);
        assertEquals(inputRule1.getSteps().get(0).getSendAfter(), rule1.steps.get(0).sendAfter);
        assertEquals(inputRule1.getSteps().get(0).getEnabled(), rule1.steps.get(0).enabled);
        assertEquals(inputRule1.getEnabled(), rule1.enabled);

        NotificationRule inputRule2 = response.getRules().find { it.name == rule2.name }
        assertEquals(inputRule2.getActionType().value(), rule2.actionType);
        assertEquals(inputRule2.getApplyOrder(), rule2.applyOrder);
        assertEquals(inputRule2.getName(), rule2.name);
        inputRestriction = inputRule2.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule2.restrictions.endHour);
        assertEquals(inputRestriction.getStartHour(), rule2.restrictions.startHour);
        assertEquals(inputRestriction.getStartMinute(), rule2.restrictions.startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule2.restrictions.endMinute);
        assertEquals(inputRule2.getId(), rule2.id);
        assertEquals(inputRule2.getConditions().size(), rule2.conditions.size());

        assertEquals(inputRule2.getConditions().get(0).getNot(), rule2.conditions.get(0).not);
        assertEquals(inputRule2.getConditions().get(0).getField().value(), rule2.conditions.get(0).field);
        assertEquals(inputRule2.getConditions().get(0).getExpectedValue(), rule2.conditions.get(0).expectedValue);
        assertEquals(inputRule2.getConditions().get(0).getOperation().value(), rule2.conditions.get(0).operation);

        assertEquals(inputRule2.getConditions().get(1).getNot(), rule2.conditions.get(1).not);
        assertEquals(inputRule2.getConditions().get(1).getField().value(), rule2.conditions.get(1).field);
        assertEquals(inputRule2.getConditions().get(1).getExpectedValue(), rule2.conditions.get(1).expectedValue);
        assertEquals(inputRule2.getConditions().get(1).getOperation().value(), rule2.conditions.get(1).operation);

        assertEquals(inputRule2.getSteps().size(), rule2.steps.size());
        assertEquals(inputRule2.getSteps().get(0).getMethod().value(), rule2.steps.get(0).method);
        assertEquals(inputRule2.getSteps().get(0).getTo(), rule2.steps.get(0).to);
        assertEquals(inputRule2.getSteps().get(0).getId(), rule2.steps.get(0).id);
        assertEquals(inputRule2.getSteps().get(0).getSendAfter(), rule2.steps.get(0).sendAfter);
        assertEquals(inputRule2.getSteps().get(0).getEnabled(), rule2.steps.get(0).enabled);
        assertEquals(inputRule2.getEnabled(), rule2.enabled);
        assertEquals(inputRule2.getConditionMatchType().value(), rule2.conditionMatchType);
        assertEquals(inputRule2.getLoopAfter(), rule2.loopAfter);

        NotificationRule inputRule3 = response.getRules().find { it.name == rule3.name }
        assertEquals(inputRule3.getActionType().value(), rule3.actionType);
        assertEquals(inputRule3.getApplyOrder(), rule3.applyOrder);
        assertEquals(inputRule3.getName(), rule3.name);
        assertEquals(1, inputRule3.getRestrictions().size())
        inputRestriction = inputRule3.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule3.restrictions.endHour);
        assertEquals(inputRestriction.getStartHour(), rule3.restrictions.startHour);
        assertEquals(inputRestriction.getStartMinute(), rule3.restrictions.startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule3.restrictions.endMinute);
        assertEquals(inputRule3.getId(), rule3.id);
        assertEquals(inputRule3.getConditions().size(), rule3.conditions.size());

        assertEquals(inputRule3.getConditions().get(0).getNot(), rule3.conditions.get(0).not);
        assertEquals(inputRule3.getConditions().get(0).getField().value(), rule3.conditions.get(0).field);
        assertEquals(inputRule3.getConditions().get(0).getExpectedValue(), rule3.conditions.get(0).expectedValue);
        assertEquals(inputRule3.getConditions().get(0).getOperation().value(), rule3.conditions.get(0).operation);

        assertEquals(inputRule3.getConditions().get(1).getNot(), rule3.conditions.get(1).not);
        assertEquals(inputRule3.getConditions().get(1).getField().value(), rule3.conditions.get(1).field);
        assertEquals(inputRule3.getConditions().get(1).getExpectedValue(), rule3.conditions.get(1).expectedValue);
        assertEquals(inputRule3.getConditions().get(1).getOperation().value(), rule3.conditions.get(1).operation);

        assertEquals(inputRule3.getSteps().size(), rule3.steps.size());
        assertEquals(inputRule3.getSteps().get(0).getMethod().value(), rule3.steps.get(0).method);
        assertEquals(inputRule3.getSteps().get(0).getTo(), rule3.steps.get(0).to);
        assertEquals(inputRule3.getSteps().get(0).getId(), rule3.steps.get(0).id);
        assertEquals(inputRule3.getSteps().get(0).getSendAfter(), rule3.steps.get(0).sendAfter);
        assertEquals(inputRule3.getSteps().get(0).getEnabled(), rule3.steps.get(0).enabled);
        assertEquals(inputRule3.getEnabled(), rule3.enabled);
        assertEquals(inputRule3.getConditionMatchType().value(), rule3.conditionMatchType);
        assertEquals(inputRule3.getLoopAfter(), rule3.loopAfter);

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
    }

    @Test
    public void testListNotificationRuleSuccessfullyWithUserId() throws Exception {
        def rule1 = [
                actionType  : "Schedule Start",
                applyOrder  : 0,
                schedules   : ["network_team_schedule"],
                name        : "Schedule Start Name",
                notifyBefore: ["Just Before", "15 mins"],
                restrictions: [[endHour: 0, startDay: "sunday", startHour: 0, endDay: "monday", startMinute: 0, endMinute: 0]],
                id          : "5ef5cc7f-23f4-4168-9f0f-d543d0c325b4",
                steps       : [
                        [
                                method   : "email",
                                to       : "john@opsgenie.com",
                                id       : "2dae31b1-5e89-4c33-a05f-d1f0e746c4db",
                                sendAfter: 0,
                                enabled  : true
                        ]
                ],
                "enabled"   : true
        ]
        def rule2 = [
                actionType        : "New Alert",
                applyOrder        : 0,
                name              : "New Alert",
                restrictions      : [
                        endHour    : 18,
                        startHour  : 0,
                        startMinute: 0,
                        endMinute  : 0
                ],
                id                : "67c4855a-4c1b-4cbf-9be2-87eb212760e9",
                conditions        : [
                        [
                                not          : false,
                                field        : "message",
                                expectedValue: "new",
                                operation    : "Contains"
                        ],
                        [
                                not          : false,
                                field        : "actions",
                                expectedValue: "ping",
                                operation    : "Contains"
                        ]
                ],
                steps             : [
                        [
                                method   : "voice",
                                to       : "1-9999999999",
                                id       : "6419cef5-ca1f-4842-9ddc-518952c60c2b",
                                sendAfter: 0,
                                enabled  : true
                        ]
                ],
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 0
        ]

        def rule3 = [
                actionType        : "New Alert",
                applyOrder        : 1,
                name              : "New Alert Night",
                restrictions      : [
                        endHour    : 18,
                        startHour  : 15,
                        startMinute: 4,
                        endMinute  : 58
                ],
                id                : "d609c2d4-c7e0-4c3c-b339-40bb58f89963",
                conditions        : [
                        [
                                not          : false,
                                field        : "message",
                                expectedValue: "asdf",
                                operation    : "Equals"
                        ],
                        [
                                not          : true,
                                field        : "extraProperties",
                                expectedValue: "asdf",
                                operation    : "Contains Key"
                        ]
                ],
                steps             : [
                        [
                                method   : "email",
                                to       : "john@opsgenie.com",
                                id       : "bd96aa09-e161-4c7e-8d15-ba5e55b7b1e2",
                                sendAfter: 5,
                                enabled  : true
                        ]
                ],
                enabled           : true,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 5
        ]


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson("rules": [rule1, rule2, rule3]).getBytes(), 200, "application/json; charset=utf-8"))

        ListNotificationRulesRequest request = new ListNotificationRulesRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().listNotificationRule(request)
        assertEquals(3, response.getRules().size())
        NotificationRule inputRule1 = response.getRules().find { it.name == rule1.name }
        assertEquals(inputRule1.getActionType().value(), rule1.actionType);
        assertEquals(inputRule1.getApplyOrder(), rule1.applyOrder);
        assertEquals(inputRule1.getSchedules(), rule1.schedules);
        assertEquals(inputRule1.getName(), rule1.name);
        assertEquals(inputRule1.getNotifyBefore().get(0).value(), rule1.notifyBefore.get(0));
        assertEquals(inputRule1.getNotifyBefore().get(1).value(), rule1.notifyBefore.get(1));
        assertEquals(1, inputRule1.getRestrictions().size())
        def inputRestriction = inputRule1.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule1.restrictions[0].endHour);
        assertEquals(inputRestriction.getStartDay().name(), rule1.restrictions[0].startDay);
        assertEquals(inputRestriction.getStartHour(), rule1.restrictions[0].startHour);
        assertEquals(inputRestriction.getEndDay().name(), rule1.restrictions[0].endDay);
        assertEquals(inputRestriction.getStartMinute(), rule1.restrictions[0].startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule1.restrictions[0].endMinute);
        assertEquals(inputRule1.getId(), rule1.id);
        assertEquals(inputRule1.getSteps().size(), rule1.steps.size());
        assertEquals(inputRule1.getSteps().get(0).getMethod().value(), rule1.steps.get(0).method);
        assertEquals(inputRule1.getSteps().get(0).getTo(), rule1.steps.get(0).to);
        assertEquals(inputRule1.getSteps().get(0).getId(), rule1.steps.get(0).id);
        assertEquals(inputRule1.getSteps().get(0).getSendAfter(), rule1.steps.get(0).sendAfter);
        assertEquals(inputRule1.getSteps().get(0).getEnabled(), rule1.steps.get(0).enabled);
        assertEquals(inputRule1.getEnabled(), rule1.enabled);

        NotificationRule inputRule2 = response.getRules().find { it.name == rule2.name }
        assertEquals(inputRule2.getActionType().value(), rule2.actionType);
        assertEquals(inputRule2.getApplyOrder(), rule2.applyOrder);
        assertEquals(inputRule2.getName(), rule2.name);
        inputRestriction = inputRule2.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule2.restrictions.endHour);
        assertEquals(inputRestriction.getStartHour(), rule2.restrictions.startHour);
        assertEquals(inputRestriction.getStartMinute(), rule2.restrictions.startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule2.restrictions.endMinute);
        assertEquals(inputRule2.getId(), rule2.id);
        assertEquals(inputRule2.getConditions().size(), rule2.conditions.size());

        assertEquals(inputRule2.getConditions().get(0).getNot(), rule2.conditions.get(0).not);
        assertEquals(inputRule2.getConditions().get(0).getField().value(), rule2.conditions.get(0).field);
        assertEquals(inputRule2.getConditions().get(0).getExpectedValue(), rule2.conditions.get(0).expectedValue);
        assertEquals(inputRule2.getConditions().get(0).getOperation().value(), rule2.conditions.get(0).operation);

        assertEquals(inputRule2.getConditions().get(1).getNot(), rule2.conditions.get(1).not);
        assertEquals(inputRule2.getConditions().get(1).getField().value(), rule2.conditions.get(1).field);
        assertEquals(inputRule2.getConditions().get(1).getExpectedValue(), rule2.conditions.get(1).expectedValue);
        assertEquals(inputRule2.getConditions().get(1).getOperation().value(), rule2.conditions.get(1).operation);

        assertEquals(inputRule2.getSteps().size(), rule2.steps.size());
        assertEquals(inputRule2.getSteps().get(0).getMethod().value(), rule2.steps.get(0).method);
        assertEquals(inputRule2.getSteps().get(0).getTo(), rule2.steps.get(0).to);
        assertEquals(inputRule2.getSteps().get(0).getId(), rule2.steps.get(0).id);
        assertEquals(inputRule2.getSteps().get(0).getSendAfter(), rule2.steps.get(0).sendAfter);
        assertEquals(inputRule2.getSteps().get(0).getEnabled(), rule2.steps.get(0).enabled);
        assertEquals(inputRule2.getEnabled(), rule2.enabled);
        assertEquals(inputRule2.getConditionMatchType().value(), rule2.conditionMatchType);
        assertEquals(inputRule2.getLoopAfter(), rule2.loopAfter);

        NotificationRule inputRule3 = response.getRules().find { it.name == rule3.name }
        assertEquals(inputRule3.getActionType().value(), rule3.actionType);
        assertEquals(inputRule3.getApplyOrder(), rule3.applyOrder);
        assertEquals(inputRule3.getName(), rule3.name);
        assertEquals(1, inputRule3.getRestrictions().size())
        inputRestriction = inputRule3.getRestrictions().get(0);
        assertEquals(inputRestriction.getEndHour(), rule3.restrictions.endHour);
        assertEquals(inputRestriction.getStartHour(), rule3.restrictions.startHour);
        assertEquals(inputRestriction.getStartMinute(), rule3.restrictions.startMinute);
        assertEquals(inputRestriction.getEndMinute(), rule3.restrictions.endMinute);
        assertEquals(inputRule3.getId(), rule3.id);
        assertEquals(inputRule3.getConditions().size(), rule3.conditions.size());

        assertEquals(inputRule3.getConditions().get(0).getNot(), rule3.conditions.get(0).not);
        assertEquals(inputRule3.getConditions().get(0).getField().value(), rule3.conditions.get(0).field);
        assertEquals(inputRule3.getConditions().get(0).getExpectedValue(), rule3.conditions.get(0).expectedValue);
        assertEquals(inputRule3.getConditions().get(0).getOperation().value(), rule3.conditions.get(0).operation);

        assertEquals(inputRule3.getConditions().get(1).getNot(), rule3.conditions.get(1).not);
        assertEquals(inputRule3.getConditions().get(1).getField().value(), rule3.conditions.get(1).field);
        assertEquals(inputRule3.getConditions().get(1).getExpectedValue(), rule3.conditions.get(1).expectedValue);
        assertEquals(inputRule3.getConditions().get(1).getOperation().value(), rule3.conditions.get(1).operation);

        assertEquals(inputRule3.getSteps().size(), rule3.steps.size());
        assertEquals(inputRule3.getSteps().get(0).getMethod().value(), rule3.steps.get(0).method);
        assertEquals(inputRule3.getSteps().get(0).getTo(), rule3.steps.get(0).to);
        assertEquals(inputRule3.getSteps().get(0).getId(), rule3.steps.get(0).id);
        assertEquals(inputRule3.getSteps().get(0).getSendAfter(), rule3.steps.get(0).sendAfter);
        assertEquals(inputRule3.getSteps().get(0).getEnabled(), rule3.steps.get(0).enabled);
        assertEquals(inputRule3.getEnabled(), rule3.enabled);
        assertEquals(inputRule3.getConditionMatchType().value(), rule3.conditionMatchType);
        assertEquals(inputRule3.getLoopAfter(), rule3.loopAfter);

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals("/v1/json/user/notificationRule", requestSent.getUrl())
    }


    @Test
    public void testAddNotificationRuleStepSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNotificationRuleStepRequest request = new AddNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");
        request.setRuleId("notificationRule1Id")
        request.setMethod(Contact.Method.EMAIL)
        request.setTo("john@opsgenie.com");
        request.setSendAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRuleStep(request);
        assertEquals("notificationRuleStep1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO])
        assertEquals(request.getSendAfter(), jsonContent[TestConstants.API.SEND_AFTER])
    }

    @Test
    public void testAddNotificationRuleStepSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNotificationRuleStepRequest request = new AddNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setRuleId("notificationRule1Id")
        request.setMethod(Contact.Method.EMAIL)
        request.setTo("john@opsgenie.com");
        request.setSendAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRuleStep(request);
        assertEquals("notificationRuleStep1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO])
        assertEquals(request.getSendAfter(), jsonContent[TestConstants.API.SEND_AFTER])
    }

    @Test
    public void testUpdateNotificationRuleStepSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateNotificationRuleStepRequest request = new UpdateNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");
        request.setRuleId("notificationRule1Id")
        request.setId("notificationRuleStep1Id")
        request.setMethod(Contact.Method.VOICE)
        request.setTo("1-9999999999");
        request.setSendAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().updateNotificationRuleStep(request);
        assertEquals("notificationRuleStep1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO])
        assertEquals(request.getSendAfter(), jsonContent[TestConstants.API.SEND_AFTER])
    }

    @Test
    public void testUpdateNotificationRuleStepSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateNotificationRuleStepRequest request = new UpdateNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setRuleId("notificationRule1Id")
        request.setId("notificationRuleStep1Id")
        request.setMethod(Contact.Method.VOICE)
        request.setTo("1-9999999999");
        request.setSendAfter(5);

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().updateNotificationRuleStep(request);
        assertEquals("notificationRuleStep1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getMethod().value(), jsonContent[TestConstants.API.METHOD])
        assertEquals(request.getTo(), jsonContent[TestConstants.API.TO])
        assertEquals(request.getSendAfter(), jsonContent[TestConstants.API.SEND_AFTER])
    }

    @Test
    public void testDeleteNotificationRuleStepSuccessfullyWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteNotificationRuleStepRequest request = new DeleteNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");
        request.setRuleId("notificationRule1Id")
        request.setId("notificationRuleStep1Id")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().deleteNotificationRuleStep(request);
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME])
        assertEquals(request.getRuleId(), requestSent.getParameters()[TestConstants.API.RULE_ID])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
    }

    @Test
    public void testDeleteNotificationRuleStepSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRuleStep1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteNotificationRuleStepRequest request = new DeleteNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setRuleId("notificationRule1Id")
        request.setId("notificationRuleStep1Id")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().deleteNotificationRuleStep(request);
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step", requestSent.getUrl())

        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID])
        assertEquals(request.getRuleId(), requestSent.getParameters()[TestConstants.API.RULE_ID])
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID])
    }


    @Test
    public void testEnableNotificationRuleStepWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableNotificationRuleStepRequest request = new EnableNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setId("notificationRuleStep1")
        request.setRuleId("notificationRule1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().enableNotificationRuleStep(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
    }

    @Test
    public void testEnableNotificationRuleStepWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        EnableNotificationRuleStepRequest request = new EnableNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setId("notificationRuleStep1")
        request.setRuleId("notificationRule1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().enableNotificationRuleStep(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
    }

    @Test
    public void testDisableNotificationRuleStepWithUserName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableNotificationRuleStepRequest request = new DisableNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setId("notificationRuleStep1")
        request.setRuleId("notificationRule1")
        request.setUsername("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().disableNotificationRuleStep(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUsername(), jsonContent[TestConstants.API.USERNAME])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
    }

    @Test
    public void testDisableNotificationRuleStepWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DisableNotificationRuleStepRequest request = new DisableNotificationRuleStepRequest();
        request.setApiKey("customer1");
        request.setId("notificationRuleStep1")
        request.setRuleId("notificationRule1")
        request.setUserId("user1")

        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().disableNotificationRuleStep(request);

        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());

        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/user/notificationRule/step/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getUserId(), jsonContent[TestConstants.API.USER_ID])
        assertEquals(request.getRuleId(), jsonContent[TestConstants.API.RULE_ID])
    }
}
