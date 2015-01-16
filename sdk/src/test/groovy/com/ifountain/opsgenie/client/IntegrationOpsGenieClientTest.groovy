package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest
import com.ifountain.opsgenie.client.model.integration.SendToIntegrationRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpPost
import org.junit.Test
import static org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
class IntegrationOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    public void testEnableIntegrationWithName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableIntegrationRequest request = new EnableIntegrationRequest();
        request.setApiKey("customer1");
        request.setName("integration1");
        request.setEnabled(true);

        def response = OpsGenieClientTestCase.opsgenieClient.integration().enableIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/integration/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
    }

    @Test
    public void testEnableIntegrationWithId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableIntegrationRequest request = new EnableIntegrationRequest();
        request.setApiKey("customer1");
        request.setId("integration1");
        request.setEnabled(true);

        def response = OpsGenieClientTestCase.opsgenieClient.integration().enableIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/integration/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
    }

    @Test
    public void testDisableIntegrationWithName() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableIntegrationRequest request = new EnableIntegrationRequest();
        request.setApiKey("customer1");
        request.setName("integration1");
        request.setEnabled(false);

        def response = OpsGenieClientTestCase.opsgenieClient.integration().enableIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/integration/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
    }

    @Test
    public void testDisableIntegrationWithId() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableIntegrationRequest request = new EnableIntegrationRequest();
        request.setApiKey("customer1");
        request.setId("integration1");
        request.setEnabled(false);

        def response = OpsGenieClientTestCase.opsgenieClient.integration().enableIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/integration/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getId(), jsonContent[TestConstants.API.ID])
    }

    @Test
    public void testEnableIntegrationThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.integration(), "enableIntegration", new EnableIntegrationRequest(id: "integration1"))
    }

    @Test
    public void testSendToIntegration() {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        SendToIntegrationRequest request = new SendToIntegrationRequest()
        request.setApiKey("apiKey1")
        request.setEndPoint("/v1/json/zabbix")
        request.setHttpParameters(["param1": "par1"])
        request.setContentParameters(["conte1": "cont1"])

        def response = OpsGenieClientTestCase.opsgenieClient.integration().sendToIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/zabbix", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals("cont1", jsonContent["conte1"])

        assertEquals(1, requestSent.parameters.size())
        assertEquals("par1", requestSent.parameters.param1)
    }

    @Test
    public void testSendToIntegrationWithApiKeyInContentParametersOverridesBaseRequestApiKey() {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        SendToIntegrationRequest request = new SendToIntegrationRequest()
        request.setApiKey("apiKey1")
        request.setEndPoint("/v1/json/zabbix")
        request.setHttpParameters(["param1": "par1"])
        request.setContentParameters(["conte1": "cont1", "apiKey": "apiKey2"])

        def response = OpsGenieClientTestCase.opsgenieClient.integration().sendToIntegration(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())
        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/zabbix", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals("apiKey2", jsonContent[TestConstants.API.API_KEY])
        assertEquals("cont1", jsonContent["conte1"])
    }
}
