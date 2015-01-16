package com.ifountain.opsgenie.client.marid.http

import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.handler.codec.http.HttpMethod
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/16/12
 * Time: 1:52 PM
 */
public class HttpControllerTest {

    @Before
    public void tearDown() throws Exception {
        HttpController.destroyInstance();
    }

    @Test
    public void testSingleton() throws Exception {
        HttpController instance1 = HttpController.getInstance();
        HttpController instance2 = HttpController.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testRegisterAndGetRequestHandler() throws Exception {
        HTTPRequest request = new HTTPRequest(new InetSocketAddress(100), ChannelBuffers.buffer(10));

        request.setUrl("/ACtioN1");
        request.setMethod(HttpMethod.GET.name);

        HTTPResponse response = HttpController.getInstance().dispatchRequest(request);
        assertEquals(HttpResponseStatus.BAD_REQUEST, response.getStatus());
        assertTrue(new String(response.getContent()).indexOf("No handler found for url") >= 0);

        MockRequestAction action1 = new MockRequestAction();
        action1.expectedResponse = new HTTPResponse();
        String content = "action1 content"
        action1.expectedResponse.setContent(content.getBytes());
        action1.expectedResponse.setStatus(HttpResponseStatus.OK);
        HttpController.getInstance().register(action1, "/action1", HttpMethod.POST, HttpMethod.PUT);

        response = HttpController.getInstance().dispatchRequest(request);
        assertEquals(HttpResponseStatus.BAD_REQUEST, response.getStatus());
        assertTrue(new String(response.getContent()).indexOf("No handler found for url") >= 0);

        request.setMethod(HttpMethod.POST.name);
        response = HttpController.getInstance().dispatchRequest(request);
        assertEquals(HttpResponseStatus.OK, response.getStatus());
        assertEquals(content, new String(response.getContent()));

        request.setMethod(HttpMethod.PUT.name);
        response = HttpController.getInstance().dispatchRequest(request);
        assertEquals(HttpResponseStatus.OK, response.getStatus());
        assertEquals(content, new String(response.getContent()));
    }

    @Test
    public void testDispatchRequest() throws Exception {
        RequestAction action1 = new MockRequestAction();
        HttpController.getInstance().register(action1, "/action1", HttpMethod.POST, HttpMethod.PUT);

        HTTPRequest request = new HTTPRequest(new InetSocketAddress(100), ChannelBuffers.buffer(10));
        request.setUrl("/action1");
        request.setMethod(HttpMethod.POST.name);

        HTTPResponse requestResponse = HttpController.getInstance().dispatchRequest(request);
        assertNotNull(requestResponse);
        assertEquals(HttpResponseStatus.OK, requestResponse.getStatus());

        request.setMethod(HttpMethod.GET.name);
        requestResponse = HttpController.getInstance().dispatchRequest(request);
        assertNotNull(requestResponse);
        assertEquals(HttpResponseStatus.BAD_REQUEST, requestResponse.getStatus());
        junit.framework.Assert.assertEquals("text/plain; charset=UTF-8", requestResponse.getContentType());
        assertEquals("No handler found for url [GET-/action1]", new String(requestResponse.getContent()));

        request.setMethod(HttpMethod.CONNECT.name);
        request.setUrl("/action1");
        requestResponse = HttpController.getInstance().dispatchRequest(request);
        assertNotNull(requestResponse);
        assertEquals(HttpResponseStatus.BAD_REQUEST, requestResponse.getStatus());
        junit.framework.Assert.assertEquals("text/plain; charset=UTF-8", requestResponse.getContentType());
        assertEquals("No handler found for url [CONNECT-/action1]", new String(requestResponse.getContent()));
    }

    private class MockRequestAction implements RequestAction {
        HTTPResponse expectedResponse = new HTTPResponse();

        @Override
        public HTTPResponse execute(HTTPRequest request) {
            return expectedResponse;
        }

        @Override
        public void register() {
        }

    }

}
