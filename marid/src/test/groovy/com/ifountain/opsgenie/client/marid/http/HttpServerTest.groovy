package com.ifountain.opsgenie.client.marid.http

import com.ifountain.opsgenie.client.TestConstants
import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.http.HttpStatusException
import com.ifountain.opsgenie.client.test.util.http.HttpUtils
import org.apache.http.client.methods.HttpPost
import org.jboss.netty.channel.ChannelException
import org.jboss.netty.handler.codec.http.HttpMethod
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import sun.security.tools.KeyTool
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import static org.junit.Assert.*
/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/17/12
 * Time: 1:53 PM
 */
public class HttpServerTest extends MaridTestCase {
    HttpServer server;
    HttpUtils httpUtils;
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
        httpUtils = new HttpUtils(30000);
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.close();
        }
        if (httpUtils != null) {
            httpUtils.destroy();
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

        String response = httpUtils.doGetRequest("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", new HashMap());
        assertEquals("Successfully executed", response);

        HttpPost post = httpUtils.preparePostMethod("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", new HashMap())
        def forwardedForIp = "ip"
        post.addHeader(TestConstants.AWS.X_FORWARDED_FOR, forwardedForIp)
        response = httpUtils.executePostMethod(post);
        assertEquals("Successfully executed", response);

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

        try {
            httpUtils.doGetRequest("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action2", new HashMap());
            fail("should throw exception");
        } catch (HttpStatusException e) {
        }

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

        String response = httpUtils.doGetRequest("http://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", new HashMap());
        assertEquals("Successfully executed", response);
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
        String response = httpUtils.doPostRequest("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", content);
        assertEquals("Successfully executed", response);
        content += "a";
        try {
            httpUtils.doPostRequest("https://" + CommonTestUtils.getLocalhostIp() + ":9998/sub1/action1", content);
            fail("Should throw exception since content length is greater than max content length");
        }
        catch (Exception e) {
            assertTrue(e.getMessage().indexOf("The target server failed to respond") >= 0)
        }
    }

}
