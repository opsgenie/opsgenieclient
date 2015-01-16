package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Escalation
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import com.ifountain.opsgenie.client.model.escalation.*
import com.ifountain.opsgenie.client.model.beans.EscalationRule
import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
class EscalationOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    public void testAddEscalationSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"escalation1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddEscalationRequest request = new AddEscalationRequest();
        request.setApiKey("customer1");
        request.setName("escalation1");
        request.setRules([
                new EscalationRule(notify: "user1@xyz.com", delay: 2),
                new EscalationRule(notify: "team1", delay: 4, notifyType: EscalationRule.NotifyType.admins)
        ]);

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().addEscalation(request)
        assertEquals("escalation1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/escalation", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(2, jsonContent[TestConstants.API.RULES].size())
        def rule = jsonContent[TestConstants.API.RULES].find { it.notify == request.getRules()[0].notify }
        assertEquals(request.getRules()[0].delay, rule[TestConstants.API.DELAY])
        assertEquals(request.getRules()[0].notifyType.value(), rule[TestConstants.API.NOTIFY_TYPE])

        rule = jsonContent[TestConstants.API.RULES].find { it.notify == request.getRules()[1].notify }
        assertEquals(request.getRules()[1].delay, rule[TestConstants.API.DELAY])
        assertEquals(request.getRules()[1].notifyType.value(), rule[TestConstants.API.NOTIFY_TYPE])
    }


    @Test
    public void testAddEscalationThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.escalation(), "addEscalation", new AddEscalationRequest())
    }

    @Test
    public void testUpdateEscalationSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"escalation1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateEscalationRequest request = new UpdateEscalationRequest();
        request.setApiKey("customer1");
        request.setId("escalation1Id");
        request.setName("escalation1_updated");
        request.setRules([
                new EscalationRule(notify: "team1", delay: 2, notifyType: EscalationRule.NotifyType.admins),
        ]);

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().updateEscalation(request)
        assertEquals("escalation1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/escalation", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getRules()[0].notify, jsonContent[TestConstants.API.RULES][0][TestConstants.API.NOTIFY])
        assertEquals(request.getRules()[0].notifyType.value(), jsonContent[TestConstants.API.RULES][0][TestConstants.API.NOTIFY_TYPE])
        assertEquals(request.getRules()[0].delay, jsonContent[TestConstants.API.RULES][0][TestConstants.API.DELAY])
    }

    @Test
    public void testUpdateEscalationWithPartialUpdate() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"escalation1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateEscalationRequest request = new UpdateEscalationRequest();
        request.setApiKey("customer1");
        request.setId("escalation1Id");

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().updateEscalation(request)
        assertEquals("escalation1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(2, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])

        //test update name only
        request = new UpdateEscalationRequest();
        request.setApiKey("customer1");
        request.setId("escalation1Id");
        request.setName("escalation1Updated");

        response = OpsGenieClientTestCase.opsgenieClient.escalation().updateEscalation(request)
        assertEquals("escalation1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(2, receivedRequests.size());
        requestSent = receivedRequests[1]

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(3, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])

        //test update users only
        request = new UpdateEscalationRequest();
        request.setApiKey("customer1");
        request.setId("escalation1Id");
        request.setRules([new EscalationRule(notify: "user1@xyz.com", delay: 2)]);

        response = OpsGenieClientTestCase.opsgenieClient.escalation().updateEscalation(request)
        assertEquals("escalation1Id", response.getId())
        assertEquals(1, response.getTook())

        assertEquals(3, receivedRequests.size());
        requestSent = receivedRequests[2]

        jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(3, jsonContent.size())
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getRules()[0].notify, jsonContent[TestConstants.API.RULES][0][TestConstants.API.NOTIFY])
        assertEquals(request.getRules()[0].delay, jsonContent[TestConstants.API.RULES][0][TestConstants.API.DELAY])
    }

    @Test
    public void testUpdateEscalationThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.escalation(), "updateEscalation", new UpdateEscalationRequest())
    }

    @Test
    public void testDeleteEscalationSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        DeleteEscalationRequest request = new DeleteEscalationRequest();
        request.setApiKey("customer1");
        request.setId("escalation1Id");
        request.setName("escalation1");

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().deleteEscalation(request)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpDelete.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/escalation", requestSent.getUrl())

        assertEquals(3, requestSent.getParameters().size())
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
    }


    @Test
    public void testDeleteUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.escalation(), "deleteEscalation", new DeleteEscalationRequest())
    }

    @Test
    public void testGetEscalationSuccessfully() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.ID, "escalation1id");
        jsonContent.put(TestConstants.API.NAME, "escalation1");
        jsonContent.put(TestConstants.API.TEAM, "team1");
        jsonContent.put(TestConstants.API.RULES, [
                [notify: "group1", type: "group", delay: 5],
                [notify: "user1@xyz.com", type: "user", delay: 2],
                [notify: "schedule1", type: "schedule", delay: 2, notifyType: EscalationRule.NotifyType.next]
        ]);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetEscalationRequest request = new GetEscalationRequest();
        request.setId(jsonContent[TestConstants.API.ID]);
        request.setName(jsonContent[TestConstants.API.NAME]);
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().getEscalation(request)
        assertEquals(1, response.getTook())
        assertEquals(jsonContent[TestConstants.API.NAME], response.getEscalation().name)
        assertEquals(jsonContent[TestConstants.API.TEAM], response.getEscalation().team)
        assertEquals(jsonContent[TestConstants.API.ID], response.getEscalation().id)
        assertEquals(3, response.getEscalation().rules.size())
        def rule = response.getEscalation().rules.find { it.notify == "group1" }
        assertEquals(5, rule.delay)
        assertEquals(EscalationRule.Type.group, rule.type)
        assertEquals(EscalationRule.NotifyType.Default, rule.notifyType)

        rule = response.getEscalation().rules.find { it.notify == "user1@xyz.com" }
        assertEquals(2, rule.delay)
        assertEquals(EscalationRule.Type.user, rule.type)
        assertEquals(EscalationRule.NotifyType.Default, rule.notifyType)

        rule = response.getEscalation().rules.find { it.notify == "schedule1" }
        assertEquals(2, rule.delay)
        assertEquals(EscalationRule.Type.schedule, rule.type)
        assertEquals(EscalationRule.NotifyType.next, rule.notifyType)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getId(), requestSent.getParameters()[TestConstants.API.ID]);
        assertEquals(request.getName(), requestSent.getParameters()[TestConstants.API.NAME]);
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/escalation", requestSent.getUrl())
    }

    @Test
    public void testGetUserThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.escalation(), "getEscalation", new GetEscalationRequest(id: "esc1"))
    }


    @Test
    public void testListEscalationsSuccessfully() throws Exception {
        Map escalation1Content = new HashMap();
        escalation1Content.put(TestConstants.API.ID, "escalation1id");
        escalation1Content.put(TestConstants.API.NAME, "escalation1");
        escalation1Content.put(TestConstants.API.RULES, [[notify: "group1", type: EscalationRule.Type.group.name(), delay: 5]]);
        Map escalation2Content = new HashMap();
        escalation2Content.put(TestConstants.API.ID, "escalation2id");
        escalation1Content.put(TestConstants.API.NAME, "escalation2");
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(escalations: [escalation1Content, escalation2Content]).getBytes(), 200, "application/json; charset=utf-8"))

        ListEscalationsRequest request = new ListEscalationsRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.escalation().listEscalations(request)
        assertEquals(2, response.escalations.size())
        Escalation escalation = response.getEscalations().find { it.id == escalation1Content[TestConstants.API.ID] }
        assertEquals(escalation1Content[TestConstants.API.NAME], escalation.name)
        assertEquals(escalation1Content[TestConstants.API.ID], escalation.id)
        assertEquals(1, escalation.rules.size())
        assertEquals(5, escalation.rules[0].delay)
        assertEquals(EscalationRule.Type.group, escalation.rules[0].type)
        assertEquals("group1", escalation.rules[0].notify)

        escalation = response.getEscalations().find { it.id == escalation2Content[TestConstants.API.ID] }
        assertEquals(escalation2Content[TestConstants.API.NAME], escalation.name)
        assertEquals(escalation2Content[TestConstants.API.ID], escalation.id)
        assertNull(escalation.rules)

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpGet.METHOD_NAME, requestSent.getMethod());
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/escalation", requestSent.getUrl())
    }

    @Test
    public void testListEscalationsThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.escalation(), "listEscalations", new ListEscalationsRequest())
    }


}
