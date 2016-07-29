package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Contact
import com.ifountain.opsgenie.client.model.beans.NotificationRule
import com.ifountain.opsgenie.client.model.beans.NotificationRuleConditions
import com.ifountain.opsgenie.client.model.beans.NotificationRuleRestriction
import com.ifountain.opsgenie.client.model.beans.Team
import com.ifountain.opsgenie.client.model.contact.*
import com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.AddNotificationRuleStepRequest
import com.ifountain.opsgenie.client.model.notificationRule.ChangeNotificationRuleOrderRequest
import com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.DeleteNotificationRuleStepRequest
import com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.DisableNotificationRuleStepRequest
import com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.EnableNotificationRuleStepRequest
import com.ifountain.opsgenie.client.model.notificationRule.RepeatNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleRequest
import com.ifountain.opsgenie.client.model.notificationRule.UpdateNotificationRuleStepRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import com.sun.org.apache.regexp.internal.RE
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

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
        request.setAction(NotificationRule.ActionType.NEW_ALERT);
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        NotificationRuleConditions condition1 = new NotificationRuleConditions();
        condition1.setNot(false);
        condition1.setField(NotificationRuleConditions.Field.MESSAGE);
        condition1.setOperation(NotificationRuleConditions.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        NotificationRuleConditions condition2 = new NotificationRuleConditions();
        condition2.setNot(true);
        condition2.setField(NotificationRuleConditions.Field.EXTRA_PROPERTIES);
        condition2.setOperation(NotificationRuleConditions.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1,condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);
        request.setApplyOrder(2);

        NotificationRuleRestriction restriction = new NotificationRuleRestriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestirictions([restriction])
        request.setSchedules(["schedule1","schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRule(request);
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
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getAction().value(), jsonContent[TestConstants.API.ACTION_TYPE])
        assertEquals(request.getConditionMatchType().value(), jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false,con1[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.MESSAGE.value(),con1[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS.value(),con1[TestConstants.API.OPERATION])
        assertEquals("night",con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true,con2[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.EXTRA_PROPERTIES.value(),con2[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS_KEY.value(),con2[TestConstants.API.OPERATION])
        assertEquals("prop1",con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores,["15 mins", "Just Before"]);
        assertEquals(request.getApplyOrder(),jsonContent[TestConstants.API.APPLY_ORDER])

        NotificationRuleRestriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(),15);
        assertEquals(restriction1.getStartMinute(),4);
        assertEquals(restriction1.getEndHour(),18);
        assertEquals(restriction1.getEndMinute(),58);

        assertEquals(request.getSchedules(),jsonContent[TestConstants.API.SCHEDULES])
    }

    @Test
    public void testAddNotificationRuleSuccessfullyWithUserId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"notificationRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddNotificationRuleRequest request = new AddNotificationRuleRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");
        request.setName("notificationRule1")
        request.setAction(NotificationRule.ActionType.NEW_ALERT);
        request.setConditionMatchType(NotificationRule.ConditionMatchType.MATCH_ALL_CONDITIONS)

        NotificationRuleConditions condition1 = new NotificationRuleConditions();
        condition1.setNot(false);
        condition1.setField(NotificationRuleConditions.Field.MESSAGE);
        condition1.setOperation(NotificationRuleConditions.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        NotificationRuleConditions condition2 = new NotificationRuleConditions();
        condition2.setNot(true);
        condition2.setField(NotificationRuleConditions.Field.EXTRA_PROPERTIES);
        condition2.setOperation(NotificationRuleConditions.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1,condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);
        request.setApplyOrder(2);

        NotificationRuleRestriction restriction = new NotificationRuleRestriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestirictions([restriction])
        request.setSchedules(["schedule1","schedule2"]);


        def response = OpsGenieClientTestCase.opsgenieClient.notificationRule().addNotificationRule(request);
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
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getAction().value(), jsonContent[TestConstants.API.ACTION_TYPE])
        assertEquals(request.getConditionMatchType().value(), jsonContent[TestConstants.API.CONDITION_MATCH_TYPE])

        def conditions = jsonContent[TestConstants.API.CONDITIONS]
        assertEquals(2, conditions.size());
        def con1 = conditions.find { it.expectedValue == "night" }
        assertNotNull(con1)
        assertEquals(false,con1[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.MESSAGE.value(),con1[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS.value(),con1[TestConstants.API.OPERATION])
        assertEquals("night",con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true,con2[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.EXTRA_PROPERTIES.value(),con2[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS_KEY.value(),con2[TestConstants.API.OPERATION])
        assertEquals("prop1",con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores,["15 mins", "Just Before"]);
        assertEquals(request.getApplyOrder(),jsonContent[TestConstants.API.APPLY_ORDER])

        NotificationRuleRestriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(),15);
        assertEquals(restriction1.getStartMinute(),4);
        assertEquals(restriction1.getEndHour(),18);
        assertEquals(restriction1.getEndMinute(),58);

        assertEquals(request.getSchedules(),jsonContent[TestConstants.API.SCHEDULES])
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

        NotificationRuleConditions condition1 = new NotificationRuleConditions();
        condition1.setNot(false);
        condition1.setField(NotificationRuleConditions.Field.MESSAGE);
        condition1.setOperation(NotificationRuleConditions.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        NotificationRuleConditions condition2 = new NotificationRuleConditions();
        condition2.setNot(true);
        condition2.setField(NotificationRuleConditions.Field.EXTRA_PROPERTIES);
        condition2.setOperation(NotificationRuleConditions.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1,condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);

        NotificationRuleRestriction restriction = new NotificationRuleRestriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestirictions([restriction])
        request.setSchedules(["schedule1","schedule2"]);


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
        assertEquals(false,con1[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.MESSAGE.value(),con1[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS.value(),con1[TestConstants.API.OPERATION])
        assertEquals("night",con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true,con2[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.EXTRA_PROPERTIES.value(),con2[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS_KEY.value(),con2[TestConstants.API.OPERATION])
        assertEquals("prop1",con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores,["15 mins", "Just Before"]);

        NotificationRuleRestriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(),15);
        assertEquals(restriction1.getStartMinute(),4);
        assertEquals(restriction1.getEndHour(),18);
        assertEquals(restriction1.getEndMinute(),58);

        assertEquals(request.getSchedules(),jsonContent[TestConstants.API.SCHEDULES])
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

        NotificationRuleConditions condition1 = new NotificationRuleConditions();
        condition1.setNot(false);
        condition1.setField(NotificationRuleConditions.Field.MESSAGE);
        condition1.setOperation(NotificationRuleConditions.Operation.CONTAINS);
        condition1.setExpectedValue("night");

        NotificationRuleConditions condition2 = new NotificationRuleConditions();
        condition2.setNot(true);
        condition2.setField(NotificationRuleConditions.Field.EXTRA_PROPERTIES);
        condition2.setOperation(NotificationRuleConditions.Operation.CONTAINS_KEY);
        condition2.setExpectedValue("prop1");

        request.setConditions([condition1,condition2]);

        request.setNotifyBefore([NotificationRule.NotifyBefore.FIFTEEN_MINUTES, NotificationRule.NotifyBefore.JUST_BEFORE]);

        NotificationRuleRestriction restriction = new NotificationRuleRestriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestirictions([restriction])
        request.setSchedules(["schedule1","schedule2"]);


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
        assertEquals(false,con1[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.MESSAGE.value(),con1[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS.value(),con1[TestConstants.API.OPERATION])
        assertEquals("night",con1[TestConstants.API.EXPECTED_VALUE])


        def con2 = conditions.find { it.expectedValue == "prop1" }
        assertNotNull(con2)
        assertEquals(true,con2[TestConstants.API.NOT])
        assertEquals(NotificationRuleConditions.Field.EXTRA_PROPERTIES.value(),con2[TestConstants.API.FIELD])
        assertEquals(NotificationRuleConditions.Operation.CONTAINS_KEY.value(),con2[TestConstants.API.OPERATION])
        assertEquals("prop1",con2[TestConstants.API.EXPECTED_VALUE])

        def notifyBefores = jsonContent[TestConstants.API.NOTIFY_BEFORE]
        assertNotNull(notifyBefores);
        assertEquals(notifyBefores,["15 mins", "Just Before"]);

        NotificationRuleRestriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(),15);
        assertEquals(restriction1.getStartMinute(),4);
        assertEquals(restriction1.getEndHour(),18);
        assertEquals(restriction1.getEndMinute(),58);

        assertEquals(request.getSchedules(),jsonContent[TestConstants.API.SCHEDULES])
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
    public void testRepeatNotificationRuleWithUserId () throws Exception {
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
    
    
    
    
    
    
    
    
    
    
    





















































    
    
    
    
    @Test
    public void testGetContactSuccessfullyWithUserName() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.DISABLED_REASON, "reason");
        jsonContent.put(TestConstants.API.METHOD, "email");
        jsonContent.put(TestConstants.API.TO, "john@opsgenie.com");
        jsonContent.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        jsonContent.put(TestConstants.API.ENABLED, true);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetContactRequest request = new GetContactRequest();
        request.setApiKey("customer1");
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().getContact(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.DISABLED_REASON], response.getContact().getDisabledReason())
        assertEquals(jsonContent[TestConstants.API.METHOD], response.getContact().getMethod().value())
        assertEquals(jsonContent[TestConstants.API.TO], response.getContact().getTo())
        assertEquals(jsonContent[TestConstants.API.ID], response.getContact().getId())
        assertEquals(jsonContent[TestConstants.API.ENABLED], response.getContact().getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testGetContactSuccessfullyWithUserId() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.DISABLED_REASON, "reason");
        jsonContent.put(TestConstants.API.METHOD, "email");
        jsonContent.put(TestConstants.API.TO, "john@opsgenie.com");
        jsonContent.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        jsonContent.put(TestConstants.API.ENABLED, true);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetContactRequest request = new GetContactRequest();
        request.setApiKey("customer1");
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().getContact(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.DISABLED_REASON], response.getContact().getDisabledReason())
        assertEquals(jsonContent[TestConstants.API.METHOD], response.getContact().getMethod().value())
        assertEquals(jsonContent[TestConstants.API.TO], response.getContact().getTo())
        assertEquals(jsonContent[TestConstants.API.ID], response.getContact().getId())
        assertEquals(jsonContent[TestConstants.API.ENABLED], response.getContact().getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testListContactSuccessfullyWithUserName() throws Exception {
        Map contact1Content = new HashMap();
        contact1Content.put(TestConstants.API.DISABLED_REASON, "reason1");
        contact1Content.put(TestConstants.API.METHOD, "sms");
        contact1Content.put(TestConstants.API.TO, "1-9999999999");
        contact1Content.put(TestConstants.API.ID, "60b77613-f86f-4642-bf32-dee2aa678e4d");
        contact1Content.put(TestConstants.API.ENABLED, true);

        Map contact2Content = new HashMap();
        contact2Content.put(TestConstants.API.DISABLED_REASON, "reason2");
        contact2Content.put(TestConstants.API.METHOD, "voice");
        contact2Content.put(TestConstants.API.TO, "1-9999999998");
        contact2Content.put(TestConstants.API.ID, "6987706b-eaa9-4801-9269-94b13c2eda9a");
        contact2Content.put(TestConstants.API.ENABLED, false);

        Map contact3Content = new HashMap();
        contact3Content.put(TestConstants.API.DISABLED_REASON, "reason3");
        contact3Content.put(TestConstants.API.METHOD, "email");
        contact3Content.put(TestConstants.API.TO, "john@opsgenie.com");
        contact3Content.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        contact3Content.put(TestConstants.API.ENABLED, false);

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(userContacts: [contact1Content, contact2Content,contact3Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListContactsRequest request = new ListContactsRequest();
        request.setApiKey("customer1");
        request.setUsername("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().listContact(request)
        assertEquals(3, response.getContacts().size())
        Contact contact = response.getContacts().find { it.id == contact1Content[TestConstants.API.ID] }
        assertEquals(contact1Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact1Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact1Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact1Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact1Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact2Content[TestConstants.API.ID] }
        assertEquals(contact2Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact2Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact2Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact2Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact2Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact3Content[TestConstants.API.ID] }
        assertEquals(contact3Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact3Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact3Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact3Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact3Content[TestConstants.API.ENABLED], contact.getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUsername(), requestSent.getParameters()[TestConstants.API.USERNAME]);
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }

    @Test
    public void testListContactSuccessfullyWithUserId() throws Exception {
        Map contact1Content = new HashMap();
        contact1Content.put(TestConstants.API.DISABLED_REASON, "reason1");
        contact1Content.put(TestConstants.API.METHOD, "sms");
        contact1Content.put(TestConstants.API.TO, "1-9999999999");
        contact1Content.put(TestConstants.API.ID, "60b77613-f86f-4642-bf32-dee2aa678e4d");
        contact1Content.put(TestConstants.API.ENABLED, true);

        Map contact2Content = new HashMap();
        contact2Content.put(TestConstants.API.DISABLED_REASON, "reason2");
        contact2Content.put(TestConstants.API.METHOD, "voice");
        contact2Content.put(TestConstants.API.TO, "1-9999999998");
        contact2Content.put(TestConstants.API.ID, "6987706b-eaa9-4801-9269-94b13c2eda9a");
        contact2Content.put(TestConstants.API.ENABLED, false);

        Map contact3Content = new HashMap();
        contact3Content.put(TestConstants.API.DISABLED_REASON, "reason3");
        contact3Content.put(TestConstants.API.METHOD, "email");
        contact3Content.put(TestConstants.API.TO, "john@opsgenie.com");
        contact3Content.put(TestConstants.API.ID, "d670912e-25fe-4101-9719-0b72898b74e5");
        contact3Content.put(TestConstants.API.ENABLED, false);

        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(userContacts: [contact1Content, contact2Content,contact3Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListContactsRequest request = new ListContactsRequest();
        request.setApiKey("customer1");
        request.setUserId("user1");

        def response = OpsGenieClientTestCase.opsgenieClient.contact().listContact(request)
        assertEquals(3, response.getContacts().size())
        Contact contact = response.getContacts().find { it.id == contact1Content[TestConstants.API.ID] }
        assertEquals(contact1Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact1Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact1Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact1Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact1Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact2Content[TestConstants.API.ID] }
        assertEquals(contact2Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact2Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact2Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact2Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact2Content[TestConstants.API.ENABLED], contact.getEnabled())

        contact = response.getContacts().find { it.id == contact3Content[TestConstants.API.ID] }
        assertEquals(contact3Content[TestConstants.API.DISABLED_REASON], contact.getDisabledReason())
        assertEquals(contact3Content[TestConstants.API.METHOD], contact.getMethod().value())
        assertEquals(contact3Content[TestConstants.API.TO], contact.getTo())
        assertEquals(contact3Content[TestConstants.API.ID], contact.getId())
        assertEquals(contact3Content[TestConstants.API.ENABLED], contact.getEnabled())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals(request.getUserId(), requestSent.getParameters()[TestConstants.API.USER_ID]);
        assertEquals("/v1/json/user/contact", requestSent.getUrl())
    }
}
