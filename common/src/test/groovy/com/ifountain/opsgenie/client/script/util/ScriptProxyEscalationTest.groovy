package com.ifountain.opsgenie.client.script.util


import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.model.BaseRequest

import com.ifountain.opsgenie.client.model.beans.Escalation
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock

import com.ifountain.opsgenie.client.model.escalation.*
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyEscalationTest {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testAddEscalation() throws Exception {
        _testAddEscalation(false);
        opsGenieClient.getExecutedRequests().clear()
        _testAddEscalation(true);

    }

    public void _testAddEscalation(boolean useConfig) throws Exception {
        def params = [name: "esc1", rules: [
                [delay: 1, notify: "group1"]
        ]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new AddEscalationResponse()
        opsGenieClient.escalation().setAddEscalationResponse(expectedResponse);
        Map response = proxy.addEscalation(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddEscalationRequest request = executedRequests[0] as AddEscalationRequest;

        assertEquals("esc1", request.getName())
        assertEquals(1, request.getRules().size());
        assertEquals("group1", request.getRules()[0].notify);
        assertEquals(1, request.getRules()[0].delay);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testAddEscalationReturningException() throws Exception {
        _testeReturningException("addEscalation", [apiKey: "customer1"], new Exception("Escalation does not exist."))
    }

    @Test
    public void testUpdateEscalation() throws Exception {
        _testUpdateEscalation(false);
        opsGenieClient.getExecutedRequests().clear()
        _testUpdateEscalation(true);

    }

    public void _testUpdateEscalation(boolean useConfig) throws Exception {
        def params = [id: "esc1Id", name: "esc1", rules: [
                [delay: 1, notify: "group1"]
        ]];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new UpdateEscalationResponse()
        opsGenieClient.escalation().setUpdateEscalationResponse(expectedResponse);
        Map response = proxy.updateEscalation(params)
        assertEquals(expectedResponse.getId(), response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UpdateEscalationRequest request = executedRequests[0] as UpdateEscalationRequest;

        assertEquals("esc1Id", request.getId())
        assertEquals("esc1", request.getName())
        assertEquals(1, request.getRules().size());
        assertEquals("group1", request.getRules()[0].notify);
        assertEquals(1, request.getRules()[0].delay);
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testUpdateEscalationReturningException() throws Exception {
        _testeReturningException("updateEscalation", [apiKey: "customer1"], new Exception("Escalation does not exist."))
    }

    @Test
    public void testDeleteEscalation() throws Exception {
        _testDeleteEscalation(false);
        opsGenieClient.getExecutedRequests().clear()
        _testDeleteEscalation(true);

    }

    public void _testDeleteEscalation(boolean useConfig) throws Exception {
        def params = [id: "esc1Id", name: "esc1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new DeleteEscalationResponse()
        opsGenieClient.escalation().setDeleteEscalationResponse(expectedResponse);
        Map response = proxy.deleteEscalation(params)
        assertEquals(true, response[OpsgenieClientApplicationConstants.ScriptProxy.SUCCESS])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        DeleteEscalationRequest request = executedRequests[0] as DeleteEscalationRequest;

        assertEquals("esc1Id", request.getId())
        assertEquals("esc1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testDeleteEscalationReturningException() throws Exception {
        _testeReturningException("deleteEscalation", [apiKey: "customer1"], new Exception("Escalation does not exist."))
    }

    @Test
    public void testGetEscalation() throws Exception {
        _testGetEscalation(false);
        opsGenieClient.getExecutedRequests().clear()
        _testGetEscalation(true);

    }

    public void _testGetEscalation(boolean useConfig) throws Exception {
        def params = [id: "esc1Id", name: "esc1"];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new GetEscalationResponse()
        expectedResponse.setEscalation(new Escalation(id: "esc1"))
        opsGenieClient.escalation().setGetEscalationResponse(expectedResponse);
        Map response = proxy.getEscalation(params)
        assertEquals("esc1", response[TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        GetEscalationRequest request = executedRequests[0] as GetEscalationRequest;

        assertEquals("esc1Id", request.getId())
        assertEquals("esc1", request.getName())
        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testGetEscalationReturningException() throws Exception {
        _testeReturningException("getEscalation", [apiKey: "customer1"], new Exception("Escalation does not exist."))
    }

    @Test
    public void testListEscalation() throws Exception {
        _testListEscalation(false);
        opsGenieClient.getExecutedRequests().clear()
        _testListEscalation(true);

    }

    public void _testListEscalation(boolean useConfig) throws Exception {
        def params = [:];
        if (!useConfig) {
            params.apiKey = "customer1";
        }
        def expectedResponse = new ListEscalationsResponse()
        expectedResponse.setEscalations([new Escalation(id: "esc1")])
        opsGenieClient.escalation().setListEscalationsResponse(expectedResponse);
        List<Map> response = proxy.listEscalations(params)
        assertEquals(1, response.size())
        assertEquals("esc1", response[0][TestConstants.API.ID])
        assertEquals(1, opsGenieClient.getExecutedRequests().size())
        def executedRequests = opsGenieClient.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        ListEscalationsRequest request = executedRequests[0] as ListEscalationsRequest;

        if (useConfig) {
            assertEquals(apiKey, request.getApiKey())
        } else {
            assertEquals("customer1", request.getApiKey())
        }
    }

    @Test
    public void testListEscalationReturningException() throws Exception {
        _testeReturningException("listEscalations", [apiKey: "customer1"], new Exception("Escalation does not exist."))
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
