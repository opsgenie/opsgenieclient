package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.account.GetAccountInfoRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import com.ifountain.opsgenie.client.util.JsonUtils
import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * Created by MEHMET MUSTAFA DEMİR
 * Date: 7/29/16
 * Time: 10:39 AM
 */
class AccountOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    public void getUserSuccessfullyWithUsername() throws Exception {
        Map jsonContent = new HashMap();
        jsonContent.put("took", 1);
        jsonContent.put(TestConstants.API.USER_COUNT, 1450);
        jsonContent.put(TestConstants.API.NAME, "opsgenie");
        Map planContent = new HashMap();
        planContent.put(TestConstants.API.IS_YEARLY,true);
        planContent.put(TestConstants.API.NAME,"Enterprise");
        planContent.put(TestConstants.API.MAX_USER_COUNT,1500);
        jsonContent.put(TestConstants.API.PLAN,planContent);
        OpsGenieClientTestCase.httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJson(jsonContent).getBytes(), 200, "application/json; charset=utf-8"))

        GetAccountInfoRequest request = new GetAccountInfoRequest();
        request.setApiKey("customer1");

        def response = OpsGenieClientTestCase.opsgenieClient.account().getAccount(request);
        assertEquals(1, response.getTook());
        assertEquals(jsonContent[TestConstants.API.USER_COUNT], response.getAccount().getUserCount());
        assertEquals(jsonContent[TestConstants.API.NAME], response.getAccount().getName());

        assertEquals(planContent[TestConstants.API.IS_YEARLY], response.getAccount().getPlan().getIsYearly());
        assertEquals(planContent[TestConstants.API.NAME], response.getAccount().getPlan().getName());
        assertEquals(planContent[TestConstants.API.MAX_USER_COUNT], response.getAccount().getPlan().getMaxUserCount());

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals(request.getApiKey(), requestSent.getParameters()[TestConstants.API.API_KEY])
        assertEquals("/v1/json/account/info", requestSent.getUrl())
    }

}
