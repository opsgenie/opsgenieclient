package com.ifountain.opsgenie.client.script.util

import com.ifountain.opsgenie.client.swagger.model.AddAlertDetailsRequest
import com.ifountain.opsgenie.client.swagger.model.DeleteAlertDetailsRequest
import com.ifountain.opsgenie.client.swagger.model.SnoozeAlertRequest
import com.ifountain.opsgenie.client.swagger.model.SuccessResponse
import com.ifountain.opsgenie.client.swagger.model.UnAcknowledgeAlertRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import static  org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:22 AM
 */
class ScriptProxyTest  {
    OpsGenieClientMock opsGenieClient;
    String apiKey = "key1"
    ScriptProxy proxy;

    @Before
    public void setUp() {
        opsGenieClient = new OpsGenieClientMock();
        proxy = new ScriptProxy(opsGenieClient, apiKey);
    }

    @Test
    public void testSnooze() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        opsGenieClient.alertV2().setGenericSuccessResponse(successResponse);
        Map response = proxy.snooze(["id":"id1", "endDate": new Date("11/24/2016 11:45"), "user" : "user1", "note" : "note1", "source" : "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = opsGenieClient.getExecutedRequestsV2();
        assertEquals(1, executedRequests.size());
        SnoozeAlertRequest request = executedRequests[0] as SnoozeAlertRequest;

        assertEquals(new DateTime(new Date("11/24/2016 11:45")), request.getEndTime());
        assertEquals("user1", request.getUser());
        assertEquals("note1", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testAddDetails() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        opsGenieClient.alertV2().setGenericSuccessResponse(successResponse);
        Map response = proxy.addDetails(["id": "id1", "details": ["k1" : "v1", "k2" : "v2", "k3" : "v3"], "user" : "user1", "note" : "note1", "source" : "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = opsGenieClient.getExecutedRequestsV2();
        assertEquals(1, executedRequests.size())
        AddAlertDetailsRequest request = executedRequests[0] as AddAlertDetailsRequest;

        assertEquals(["k1" : "v1", "k2" : "v2", "k3" : "v3"], request.getDetails())
        assertEquals("user1", request.getUser());
        assertEquals("note1", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testRemoveDetails() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        opsGenieClient.alertV2().setGenericSuccessResponse(successResponse);
        Map response = proxy.removeDetails(["id" : "id1", "keys" : ["key1", "key2", "key3"], "user" : "user1", "note" : "note1", "source" : "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = opsGenieClient.getExecutedRequestsV2();
        assertEquals(1, executedRequests.size())
        DeleteAlertDetailsRequest request = executedRequests[0] as DeleteAlertDetailsRequest;

        assertEquals(["key1", "key2", "key3"], request.getKeys());
        assertEquals("user1", request.getUser());
        assertEquals("note1", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testUnAcknowledge() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        opsGenieClient.alertV2().setGenericSuccessResponse(successResponse);
        Map response = proxy.unAcknowledge(["id" : "id1", "user" : "user1", "source" : "source1", "note" : "note1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = opsGenieClient.getExecutedRequestsV2();
        assertEquals(1, executedRequests.size())
        UnAcknowledgeAlertRequest request = executedRequests[0] as UnAcknowledgeAlertRequest;

        assertEquals("user1", request.getUser());
        assertEquals("note1", request.getNote());
        assertEquals("source1", request.getSource());
    }
}
