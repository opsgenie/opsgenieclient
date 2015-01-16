package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest
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
class AlertPolicyOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {
    @Test
    public void testEnableAlertPolicySuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableAlertPolicyRequest request = new EnableAlertPolicyRequest();
        request.setApiKey("customer1");
        request.setName("utomation1");
        request.setEnabled(true);

        def response = OpsGenieClientTestCase.opsgenieClient.alertPolicy().enableAlertPolicy(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/policy/enable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
    }

    @Test
    public void testDisableAlertPolicySuccessfully() throws Exception {
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse("{\"status\":\"success\", \"took\":1}".getBytes(), 200, "application/json; charset=utf-8"))

        EnableAlertPolicyRequest request = new EnableAlertPolicyRequest();
        request.setApiKey("customer1");
        request.setName("utomation1");
        request.setEnabled(false);

        def response = OpsGenieClientTestCase.opsgenieClient.alertPolicy().enableAlertPolicy(request)
        assertTrue(response.success)
        assertEquals(1, response.getTook())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(HttpPost.METHOD_NAME, requestSent.getMethod());
        assertEquals("/v1/json/alert/policy/disable", requestSent.getUrl())
        assertEquals("application/json; charset=utf-8", requestSent.getHeader(HttpHeaders.CONTENT_TYPE));

        def jsonContent = JsonUtils.parse(requestSent.getContentAsByte())
        assertEquals(request.getApiKey(), jsonContent[TestConstants.API.API_KEY])
        assertEquals(request.getName(), jsonContent[TestConstants.API.NAME])
    }


    @Test
    public void testEnableAlertPolicyThrowsExceptionIfRequestCannotBeValidated() throws Exception {
        _testThrowsExceptionIfRequestCannotBeValidated(OpsGenieClientTestCase.opsgenieClient.alertPolicy(), "enableAlertPolicy", new EnableAlertPolicyRequest())
    }
}
