package com.ifountain.opsgenie.client.marid.http

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.http.OpsGenieHttpClient
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import com.ifountain.opsgenie.client.test.util.file.TestFile
import org.hamcrest.CoreMatchers
import org.jboss.netty.channel.ChannelException
import org.jboss.netty.handler.codec.http.HttpMethod
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import sun.security.tools.KeyTool

import static org.junit.Assert.*

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/17/12
 * Time: 1:53 PM
 */
public class HttpServerTest extends MaridTestCase {
    HttpServer server;
    OpsGenieHttpClient httpClient;
    def keyPass = "123456"
    def keystorePath = TestFile.TESTOUTPUT_DIR + "/.keystore"

    @BeforeClass
    public static void beforeClass() {
        MaridTestCase.beforeClass()
    }

    @Before
    public void setUp() throws Exception {
        new File(keystorePath).delete();
        String[] args = ["-genkey", "-alias", "localhost", "-dname",
                         "CN=localhost,OU=Test,O=Test,C=US", "-keyalg", "RSA",
                         "-validity", "365", "-storepass", "key", "-keystore",
                         "${keystorePath}", "-storepass", "${keyPass}",
                         "-keypass", "${keyPass}"] as String[];
        KeyTool.main(args);
        server = new HttpServer(CommonTestUtils.getLocalhostIp(), 9998, keystorePath, keyPass);
        httpClient = new OpsGenieHttpClient();
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.close();
        }
        HttpController.destroyInstance();
        super.tearDown();
    }

    @Test
    public void testOpenThrowsExceptionIfHostNameIsInvalid() throws Exception {
        server.setHost("invalidHost");
        try {
            server.open();
            fail("should throw exception");
        } catch (ChannelException e) {
            assertTrue(e.getMessage().indexOf("Failed to bind to: invalidHost") >= 0);
        }
    }

    @Test
    public void testOpenThrowsExceptionIfPortIsInvalid() throws Exception {
        server.setPort(-1);
        try {
            server.open();
        } catch (IllegalArgumentException e) {
            assertEquals("port out of range:-1", e.getMessage());
        }
    }

    @Test
    public void testSuccessfulOpen() throws Exception {
        server.open();
        assertTrue(server.getChannel().isOpen());
    }

    @Test
    public void testClose() throws Exception {
        server.open();
        assertTrue(server.getChannel().isOpen());
        server.close();
        assertFalse(server.getChannel().isOpen());
    }

    @Test
    public void testHttpRequestResponse() throws Exception {
        HttpController.getInstance().register(new RequestAction() {
            @Override
            public HTTPResponse execute(HTTPRequest request) {
                HTTPResponse response = new HTTPResponse();
                String content = "Successfully executed";
                response.setContent(content.getBytes());
                response.setContentType("text/plain; charset=UTF-8");
                return response;
            }

            @Override
            public void register() throws Exception {
            }
        }, "/sub1/action1", HttpMethod.POST, HttpMethod.GET);

        server.open();

        def response = httpClient.get("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", new HashMap());
        assertEquals("Successfully executed", response.contentAsString);
        def forwardedForIp = "ip"
        def headers = new HashMap()
        headers.put(TestConstants.AWS.X_FORWARDED_FOR, forwardedForIp)
//        def headers = [ : ]
        response = httpClient.post("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", "" , headers);
        assertEquals("Successfully executed", response.contentAsString);

        HttpController.getInstance().register(new RequestAction() {
            @Override
            public HTTPResponse execute(HTTPRequest request) {
                HTTPResponse requestResponse = new HTTPResponse();
                requestResponse.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
                String content = "Please see the log files";
                requestResponse.setContent(content.getBytes());
                requestResponse.setContentType("text/plain; charset=UTF-8");
                return requestResponse;
            }

            @Override
            public void register() throws Exception {
            }
        }, "/sub1/action2", HttpMethod.GET);

        response = httpClient.get("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action2", new HashMap())
        assertTrue(response.getStatusCode() > 399)
    }

    @Test
    public void testServerWithoutSSL() throws Exception {
        server = new HttpServer(CommonTestUtils.getLocalhostIp(), 9998);
        HttpController.getInstance().register(new RequestAction() {
            @Override
            public HTTPResponse execute(HTTPRequest request) {
                HTTPResponse response = new HTTPResponse();
                String content = "Successfully executed";
                response.setContent(content.getBytes());
                response.setContentType("text/plain; charset=UTF-8");
                return response;
            }

            @Override
            public void register() throws Exception {
            }
        }, "/sub1/action1", HttpMethod.GET);

        server.open();

        def response = httpClient.get("http://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", new HashMap());
        assertEquals("Successfully executed", response.contentAsString);
    }

    @Test
    public void testThrowsExceptionIfMaxContentSizeIsGreaterThanThreshold() throws Exception {
        HttpController.getInstance().register(new RequestAction() {
            @Override
            public HTTPResponse execute(HTTPRequest request) {
                HTTPResponse response = new HTTPResponse();
                String content = "Successfully executed";
                response.setContent(content.getBytes());
                response.setContentType("text/plain; charset=UTF-8");
                return response;
            }

            @Override
            public void register() throws Exception {
            }
        }, "/sub1/action1", HttpMethod.POST, HttpMethod.GET);
        int maxContentSize = 100;
        server.setMaxContentLength(maxContentSize)

        server.open();

        String content = "";
        (maxContentSize).times {
            content += "a";
        }
        def response = httpClient.post("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", content);
        assertEquals("Successfully executed", response.contentAsString);
        content += "a";
        try {
            httpClient.post("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", content);
            fail("Should throw exception since content length is greater than max content length");
        }
        catch (Exception e) {
            assertTrue(e.getMessage().indexOf("The target server failed to respond") >= 0)
            assertThat(e.getMessage(), CoreMatchers.containsString("The target server failed to respond"))
        }
    }

}
