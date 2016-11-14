package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.beans.Condition
import com.ifountain.opsgenie.client.model.beans.NotificationRule.ConditionMatchType
import com.ifountain.opsgenie.client.model.beans.Restriction
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule
import com.ifountain.opsgenie.client.model.beans.TeamRoutingRule.TeamRoutingRuleNotify
import com.ifountain.opsgenie.client.model.team.routing_rule.*
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Mehmet Mustafa Demir
 */
class TeamRoutingRuleOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void testAddNotificationRuleSuccessfullyWithTeamName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"routingRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        AddTeamRoutingRuleRequest request = new AddTeamRoutingRuleRequest();
        setBaseTeamParameters(request);
        request.setName("routingRule")
        TeamRoutingRuleNotify notify = new TeamRoutingRuleNotify();
        notify.setName("escalation1");
        notify.setType("escalation");
        request.setNotify(notify);

        request.setConditionMatchType(ConditionMatchType.MATCH_ALL_CONDITIONS)
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

        request.setApplyOrder(2);

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])


        def response = OpsGenieClientTestCase.opsgenieClient.team().addTeamRoutingRule(request);
        assertEquals("routingRule1Id", response.getId())
        assertEquals(1, response.getTook())

        def jsonContent = testPostRequestMethodParametersAndReturnJsonContent("/v1.1/json/team/routingRule");
        assertEquals("routingRule", jsonContent[TestConstants.API.NAME])
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

        assertEquals(request.getApplyOrder(), jsonContent[TestConstants.API.APPLY_ORDER])

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);


        def requestNotify = jsonContent[TestConstants.API.NOTIFY]
        assertNotNull(requestNotify)
        assertEquals(notify.getName(), requestNotify[TestConstants.API.NAME])
        assertEquals(notify.getType(), requestNotify[TestConstants.API.TYPE])

    }

    @Test
    public void testUpdateRoutingnRuleSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"id\":\"routingRule1Id\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        UpdateTeamRoutingRuleRequest request = new UpdateTeamRoutingRuleRequest();
        setBaseTeamParameters(request);
        request.setId("routingRuleId")
        request.setName("routingRule")
        TeamRoutingRuleNotify notify = new TeamRoutingRuleNotify();
        notify.setName("escalation1");
        notify.setType("escalation");
        request.setNotify(notify);

        request.setConditionMatchType(ConditionMatchType.MATCH_ALL_CONDITIONS)
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

        Restriction restriction = new Restriction();
        restriction.setStartHour(15);
        restriction.setStartMinute(04);
        restriction.setEndHour(18);
        restriction.setEndMinute(58);

        request.setRestrictions([restriction])


        def response = OpsGenieClientTestCase.opsgenieClient.team().updateTeamRoutingRule(request);
        assertEquals("routingRule1Id", response.getId())
        assertEquals(1, response.getTook())


        def jsonContent = testPostRequestMethodParametersAndReturnJsonContent("/v1.1/json/team/routingRule");
        assertEquals("routingRule", jsonContent[TestConstants.API.NAME])
        assertEquals("routingRuleId", jsonContent[TestConstants.API.ID])
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

        Restriction restriction1 = jsonContent[TestConstants.API.RESTRICTIONS]
        assertNotNull(restriction1)
        assertEquals(restriction1.getStartHour(), 15);
        assertEquals(restriction1.getStartMinute(), 4);
        assertEquals(restriction1.getEndHour(), 18);
        assertEquals(restriction1.getEndMinute(), 58);


        def requestNotify = jsonContent[TestConstants.API.NOTIFY]
        assertNotNull(requestNotify)
        assertEquals(notify.getName(), requestNotify[TestConstants.API.NAME])
        assertEquals(notify.getType(), requestNotify[TestConstants.API.TYPE])
    }

    @Test
    public void testDeleteRoutingRuleSuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        DeleteTeamRoutingRuleRequest request = new DeleteTeamRoutingRuleRequest();
        setBaseTeamParameters(request);
        request.setId("routingRuleId");

        def response = OpsGenieClientTestCase.opsgenieClient.team().deleteTeamRoutingRule(request)
        assertEquals(1, response.getTook())

        HttpTestRequest requestSent = testRequestMethodParameters(HttpDelete.METHOD_NAME, "/v1/json/team/routingRule")
        testBaseTeamParameters(requestSent.getParameters());
        assertEquals("routingRuleId", requestSent.getParameters()[TestConstants.API.ID]);
    }


    @Test
    public void testChangeRoutingRuleOrder() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))
        ChangeTeamRoutingRuleOrderRequest request = new ChangeTeamRoutingRuleOrderRequest();
        setBaseTeamParameters(request);
        request.setId("routingRuleId");
        request.setApplyOrder(1);

        def response = OpsGenieClientTestCase.opsgenieClient.team().changeTeamRoutingRuleOrder(request);
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        def jsonContent = testPostRequestMethodParametersAndReturnJsonContent("/v1/json/team/routingRule/changeOrder");
        assertEquals("routingRuleId", jsonContent[TestConstants.API.ID])
        assertEquals(1, jsonContent[TestConstants.API.APPLY_ORDER])
    }


    @Test
    public void testGetRoutingRuleSuccessfully() throws Exception {
        String teamRoutingRuleId = "67c4855a-4c1b-4cbf-9be2-87eb212760e9";
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
        def notify = [
                name: "escalationName",
                type: "escalation",
                id  : "6419cef5-ca1f-4842-9ddc-518952c60c2b"
        ]

        def restrictionMap = [
                endHour    : 18,
                startHour  : 15,
                startMinute: 4,
                endMinute  : 58
        ]


        def jsonResponse = [
                name              : "routingRuleName",
                restrictions      : [
                        [endHour: 1, startDay: "MONDAY", startHour: 2, endDay: "TUESDAY", startMinute: 3, endMinute: 4]
                ],
                id                : teamRoutingRuleId,
                isDefault         : false,
                conditionMatchType: "Match All Conditions",
                loopAfter         : 0
        ]
        jsonResponse.put("conditions", [condition1, condition2])
        jsonResponse.put("notify", notify)
        jsonResponse.put("restrictions", [restrictionMap])


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonResponse).getBytes(), 200, "application/json; charset=utf-8"))

        GetTeamRoutingRuleRequest request = new GetTeamRoutingRuleRequest();
        setBaseTeamParameters(request);
        request.setId(teamRoutingRuleId);


        def response = OpsGenieClientTestCase.opsgenieClient.team().getTeamRoutingRule(request)

        TeamRoutingRule teamRoutingRule = response.getRule();
        assertNotNull(teamRoutingRule);
        assertEquals(jsonResponse[TestConstants.API.NAME], teamRoutingRule.getName())
        assertEquals(teamRoutingRuleId, teamRoutingRule.getId());
        assertEquals(jsonResponse[TestConstants.API.IS_DEFAULT], teamRoutingRule.getIsDefault())

        //testing restrictions
        assertEquals(1, teamRoutingRule.getRestrictions().size());
        testRestriction(restrictionMap, teamRoutingRule.getRestrictions().get(0))

        //testing conditions
        assertEquals(jsonResponse[TestConstants.API.CONDITION_MATCH_TYPE], teamRoutingRule.getConditionMatchType().value())

        assertEquals(2, teamRoutingRule.getConditions().size());
        testCondition(condition1,teamRoutingRule.getConditions().find { it.expectedValue == "new" });
        testCondition(condition2,teamRoutingRule.getConditions().find { it.expectedValue == "ping" });

        //testing notify
        TeamRoutingRuleNotify ruleNotify = teamRoutingRule.getNotify();
        assertNotNull(ruleNotify);
        assertEquals(notify[TestConstants.API.NAME], ruleNotify.getName())
        assertEquals(notify[TestConstants.API.TYPE], ruleNotify.getType())
        assertEquals(notify[TestConstants.API.ID], ruleNotify.getId())

        //testing get request parameters
        HttpTestRequest requestSent = testRequestMethodParameters(HttpGet.METHOD_NAME, "/v1.1/json/team/routingRule")
        testBaseTeamParameters(requestSent.getParameters());
        assertEquals(teamRoutingRuleId, requestSent.getParameters()[TestConstants.API.ID])
    }


    @Test
    public void testListRoutingRuleSuccessfully() throws Exception {
        def rule1 = [
                applyOrder        : 0,
                name              : "rule1Name",
                restrictions      : [[endHour: 0, startHour: 0, startMinute: 0, endMinute: 0]],
                id                : "rule1Id",
                isDefault         : false,
                conditionMatchType: "Match Any Condition",
                conditions        : [
                        ["not": false, "field": "message", "expectedValue": "night", "operation": "Contains", "order": 0],
                        ["not": true, "field": "extraProperties", "expectedValue": "prop1", "operation": "Contains Key", "order": 0]],
                notify            : [
                        "name": "scheduleName",
                        "id"  : "scheduleId",
                        "type": "schedule"
                ]
        ]


        def rule2 = [
                applyOrder        : 1,
                name              : "rule2Name",
                restrictions      : [[
                                             endHour    : 18,
                                             startDay   : "sunday",
                                             startHour  : 0,
                                             startMinute: 0,
                                             endDay     : "monday",
                                             endMinute  : 0
                                     ],
                                     [
                                             endHour    : 21,
                                             startDay   : "tuesday",
                                             startHour  : 15,
                                             startMinute: 8,
                                             endDay     : "wednesday",
                                             endMinute  : 7
                                     ]],
                id                : "rule2Id",
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
                notify            : [
                        "name": "escalationName",
                        "id"  : "escalationId",
                        "type": "escalation"
                ],
                isDefault         : false,
                conditionMatchType: "Match All Conditions"
        ]

        def rule3 = [
                applyOrder        : 2,
                isDefault         : true,
                name              : "Default Routing Rule",
                id                : "defaultRuleId",
                conditions        : [],
                conditionMatchType: "Match All",
                notify            : ["name": "none"]
        ]


        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson("rules": [rule1, rule2, rule3]).getBytes(), 200, "application/json; charset=utf-8"))

        ListTeamRoutingRulesRequest request = new ListTeamRoutingRulesRequest();
        setBaseTeamParameters(request);

        def response = OpsGenieClientTestCase.opsgenieClient.team().listTeamRoutingRules(request)
        assertEquals(3, response.getRules().size())
        //test Rule1
        TeamRoutingRule inputRule1 = response.getRules().find { it.name == rule1.name }
        assertEquals(rule1.applyOrder, inputRule1.getApplyOrder());
        assertEquals(rule1.name, inputRule1.getName());
        assertEquals(rule1.id, inputRule1.getId());
        assertFalse(inputRule1.getIsDefault())

        TeamRoutingRuleNotify notify1 = inputRule1.getNotify();
        assertNotNull(notify1)
        assertEquals("scheduleName", notify1.getName())
        assertEquals("schedule", notify1.getType())
        assertEquals("scheduleId", notify1.getId())

        assertEquals(1, inputRule1.getRestrictions().size())
        testRestriction(rule1.restrictions[0], inputRule1.getRestrictions().get(0))


        assertEquals(rule1.conditionMatchType, inputRule1.getConditionMatchType().value());
        assertEquals(2, inputRule1.getConditions().size());
        testCondition(rule1.conditions[0],inputRule1.getConditions().find { it.expectedValue == "night" });
        testCondition(rule1.conditions[1],inputRule1.getConditions().find { it.expectedValue == "prop1" });

        //test rule2
        TeamRoutingRule inputRule2 = response.getRules().find { it.name == rule2.name }
        assertEquals(rule2.applyOrder, inputRule2.getApplyOrder());
        assertEquals(rule2.name, inputRule2.getName());
        assertEquals(rule2.id, inputRule2.getId());
        assertFalse(inputRule2.getIsDefault())

        TeamRoutingRuleNotify notify2 = inputRule2.getNotify();
        assertNotNull(notify2)
        assertEquals("escalationName", notify2.getName())
        assertEquals("escalation", notify2.getType())
        assertEquals("escalationId", notify2.getId())


        assertEquals(2, inputRule2.getRestrictions().size())
        testRestriction(rule2.restrictions[0], inputRule2.getRestrictions().find { it.startDay.name() == "sunday" })
        testRestriction(rule2.restrictions[1], inputRule2.getRestrictions().find { it.startDay.name() == "tuesday" })

        assertEquals(rule2.conditionMatchType, inputRule2.getConditionMatchType().value());
        assertEquals(2, inputRule2.getConditions().size());
        testCondition(rule2.conditions[0],inputRule2.getConditions().find { it.expectedValue == "new" });
        testCondition(rule2.conditions[1],inputRule2.getConditions().find { it.expectedValue == "ping" });


        //test rule3
        TeamRoutingRule inputRule3 = response.getRules().find { it.name == rule3.name }
        assertEquals(rule3.applyOrder, inputRule3.getApplyOrder());
        assertEquals(rule3.name, inputRule3.getName());
        assertEquals(rule3.id, inputRule3.getId());
        assertTrue(inputRule3.getIsDefault())
        assertEquals(rule3.conditionMatchType, inputRule3.getConditionMatchType().value());
        assertEquals(0, inputRule3.getConditions().size())

        TeamRoutingRuleNotify defaultNotify = inputRule3.getNotify();
        assertNotNull(defaultNotify)
        assertEquals("none", defaultNotify.getName())

        HttpTestRequest requestSent = testRequestMethodParameters(HttpGet.METHOD_NAME, "/v1.1/json/team/routingRule")
        testBaseTeamParameters(requestSent.getParameters());

    }

    private void testCondition(Map conditionMap, Condition condition) {
        assertEquals( conditionMap.not, condition.getNot());
        assertEquals( conditionMap.field, condition.getField().value());
        assertEquals( conditionMap.operation, condition.getOperation().value());
        assertEquals( conditionMap.expectedValue, condition.getExpectedValue());
    }

    private void testRestriction(Map restrictionMap, Restriction restriction) {
        assertEquals(restrictionMap.endHour, restriction.getEndHour());
        assertEquals(restrictionMap.startHour, restriction.getStartHour());
        assertEquals(restrictionMap.startMinute, restriction.getStartMinute());
        assertEquals(restrictionMap.endMinute, restriction.getEndMinute());
        if (restrictionMap.startDay != null)
            assertEquals(restrictionMap.startDay, restriction.getStartDay().name());
        if (restrictionMap.endDay != null)
            assertEquals(restrictionMap.endDay, restriction.getEndDay().name());
    }

    private Map testPostRequestMethodParametersAndReturnJsonContent(String link) {
        HttpTestRequest requestSent = testRequestMethodParameters(HttpPost.METHOD_NAME, link)
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));
        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        testBaseTeamParameters(jsonContent);
        return jsonContent;
    }

    private HttpTestRequest testRequestMethodParameters(String methodName, String link) {
        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(methodName, requestSent.getMethod());
        assertEquals(link, requestSent.getUrl())
        return requestSent;
    }

    private void testBaseTeamParameters(Map requestMap) {
        assertEquals("customer1", requestMap[TestConstants.API.API_KEY])
        assertEquals("teamName", requestMap[TestConstants.API.TEAM_NAME]);
        assertEquals("teamId", requestMap[TestConstants.API.TEAM_ID]);
    }

    private BaseTeamRoutingRuleRequest setBaseTeamParameters(BaseTeamRoutingRuleRequest request) {
        request.setApiKey("customer1")
        request.setTeamName("teamName")
        request.setTeamId("teamId")
        return request;
    }

}
