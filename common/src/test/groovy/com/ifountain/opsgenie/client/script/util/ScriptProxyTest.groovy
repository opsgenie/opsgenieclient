package com.ifountain.opsgenie.client.script.util


import com.ifountain.opsgenie.client.test.util.AlertApiMock
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.opsgenie.oas.sdk.ApiClient
import com.opsgenie.oas.sdk.model.AddDetailsToAlertRequest
import com.opsgenie.oas.sdk.model.RemoveDetailsFromAlertRequest
import com.opsgenie.oas.sdk.model.SnoozeAlertRequest
import com.opsgenie.oas.sdk.model.SuccessResponse
import com.opsgenie.oas.sdk.model.UnAcknowledgeAlertRequest
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
    private ApiClient apiClient;
    private AlertApiMock alertApiMock = new AlertApiMock()
    private String apiKey = "key1"
    private ScriptProxy scriptProxy;

    @Before
    public void setUp() {
        apiClient = new ApiClient()
        apiClient.setApiKey(apiKey)
        alertApiMock.setApiClient(apiClient)
        scriptProxy = new ScriptProxy(apiClient)
        scriptProxy.setAlertApi(alertApiMock)
    }
    @Test
    public void testSnooze() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.snooze(["id":"id1", "endDate": new Date("11/24/2016 11:45"), "user": "user1", "note": "note1", "source": "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size());
        SnoozeAlertRequest request = executedRequests[0] as SnoozeAlertRequest;

        assertEquals(new DateTime(new Date("11/24/2016 11:45")), request.getBody().getEndTime());
        assertEquals("user1", request.getBody().getUser());
        assertEquals("note1", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
    }

    @Test
    public void testAddDetails() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.addDetails(["id": "id1", "details": ["k1": "v1", "k2": "v2", "k3": "v3"], "user": "user1", "note": "note1", "source": "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        AddDetailsToAlertRequest request = executedRequests[0] as AddDetailsToAlertRequest;

        assertEquals(["k1" : "v1", "k2" : "v2", "k3" : "v3"], request.getBody().getDetails())
        assertEquals("user1", request.getBody().getUser());
        assertEquals("note1", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
    }

    @Test
    public void testRemoveDetails() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.removeDetails(["id": "id1", "keys": ["key1", "key2", "key3"], "user": "user1", "note": "note1", "source": "source1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        RemoveDetailsFromAlertRequest request = executedRequests[0] as RemoveDetailsFromAlertRequest;

        assertEquals(["key1", "key2", "key3"], request.getKeys());
        assertEquals("user1", request.getUser());
        assertEquals("note1", request.getNote());
        assertEquals("source1", request.getSource());
    }

    @Test
    public void testUnAcknowledge() throws Exception {
        SuccessResponse successResponse = CommonTestUtils.createGenericSuccessResponse();

        alertApiMock.setGenericSuccessResponse(successResponse);
        Map response = scriptProxy.unAcknowledge(["id": "id1", "user": "user1", "source": "source1", "note": "note1"]);

        CommonTestUtils.assertGenericResponseWithoutData(response);

        def executedRequests = alertApiMock.getExecutedRequests();
        assertEquals(1, executedRequests.size())
        UnAcknowledgeAlertRequest request = executedRequests[0] as UnAcknowledgeAlertRequest;

        assertEquals("user1", request.getBody().getUser());
        assertEquals("note1", request.getBody().getNote());
        assertEquals("source1", request.getBody().getSource());
    }
}
