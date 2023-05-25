package com.ifountain.opsgenie.client

import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.model.account.GetAccountInfoRequest
import com.ifountain.opsgenie.client.test.util.OpsGenieClientTestCase
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.json.JSONObject
import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * @author Manisha Singla
 */
class AccountOpsGenieClientTest extends OpsGenieClientTestCase implements HttpTestRequestListener {

    @Test
    void getUserSuccessfullyWithUsername() throws Exception {
        String responseStr = getResponseString("GetAccountInfoResponseString")
        httpServer.setResponseToReturn(new HttpTestResponse(responseStr.getBytes(), 200, "application/json; charset=utf-8"))

        GetAccountInfoRequest request = new GetAccountInfoRequest()
        request.setApiKey("customer1")

        def response = opsgenieClient.account().getAccount(request)
        assertEquals(0, response.getTook())
        assertEquals(1450, response.getAccount().getUserCount())
        assertEquals("opsgenie", response.getAccount().getName())

        assertEquals(true, response.getAccount().getPlan().getIsYearly())
        assertEquals("Enterprise", response.getAccount().getPlan().getName())
        assertEquals(1500, response.getAccount().getPlan().getMaxUserCount())

        assertEquals(1, receivedRequests.size());
        HttpTestRequest requestSent = receivedRequests[0]
        assertEquals("/v2/account", requestSent.getUrl())
    }

    private String getResponseString(String responseType) {
        def jsonSlurper = new JsonSlurper()
        JSONObject data = jsonSlurper.parse(new FileReader("sdk/src/test/resources/AccountOpsGenieClient.json")) as JSONObject
        Object responseJson = data.get(responseType)
        String responseStr = new JsonBuilder(responseJson).toPrettyString()
        responseStr
    }

}
