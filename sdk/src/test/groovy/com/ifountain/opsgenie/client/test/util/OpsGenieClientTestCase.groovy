package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.OpsGenieClient
import com.ifountain.opsgenie.client.OpsGenieClientException
import com.ifountain.opsgenie.client.http.HttpTestRequest
import com.ifountain.opsgenie.client.http.HttpTestRequestListener
import com.ifountain.opsgenie.client.http.HttpTestResponse
import com.ifountain.opsgenie.client.http.HttpTestServer
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.commons.io.FileUtils
import org.apache.http.HttpStatus
import org.junit.After
import org.junit.Before

import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/31/12
 * Time: 11:09 AM
 */
public class OpsGenieClientTestCase  implements HttpTestRequestListener {
    static host = "localhost"
    static port = 9998;
    static httpServer = new HttpTestServer(host, port)
    static OpsGenieClient opsgenieClient;
    List receivedRequests = [];


    @Before
    public void setUp() {
        httpServer.open()
        httpServer.addRequestListener(this);
        def rootDir = new TestFile()
        FileUtils.deleteDirectory(rootDir);
        rootDir.mkdirs();
        receivedRequests.clear();
        opsgenieClient = new OpsGenieClient();
        opsgenieClient.setRootUri("http://${host}:${port}");
    }

    @After
    public void tearDown() {
        httpServer.close()
        httpServer.removeRequestListener(this);
    }

    public void _testThrowsExceptionIfRequestCannotBeValidated(client, methodName, request) throws Exception {
        def errorResponse = [error: "Could not authenticate.", code: 2]
        httpServer.setResponseToReturn(new HttpTestResponse(JsonUtils.toJsonAsBytes(errorResponse), HttpStatus.SC_BAD_REQUEST, "application/json; charset=utf-8"))

        try {
            client."${methodName}"(request)
            fail("should throw exception")
        }
        catch (OpsGenieClientException e) {
            assertEquals(2, e.getCode())
            assertEquals("Could not authenticate.", e.getMessage())
        }

        //unexpected responses will be thrown as ioexception
        httpServer.setResponseToReturn(new HttpTestResponse("No handler found.".getBytes(), HttpStatus.SC_BAD_REQUEST, "text/plain; charset=utf-8"))
        try {
            client."${methodName}"(request)
            fail("should throw exception")
        }
        catch (IOException e) {
            assertEquals("No handler found.", e.getMessage())
        }
    }

    @Override
    void requestRecieved(HttpTestRequest request) {
        receivedRequests.add(request);
    }
}
