package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient
import com.ifountain.opsgenie.client.marid.http.HttpController
import com.ifountain.opsgenie.client.marid.http.HttpServer
import com.ifountain.opsgenie.client.test.util.file.TestFile
import org.apache.commons.io.FileUtils
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import sun.security.tools.KeyTool

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/18/12
 * Time: 9:36 AM
 */
public class RequestActionTestCase extends MaridTestCase {
    protected OpsGenieHttpClient httpUtils;
    HttpServer httpServer;
    protected int port;
    protected String host;
    def keyPass = "123456"
    def keystorePath = TestFile.TESTOUTPUT_DIR + "/.keystore"

    @BeforeClass
    public static void beforeClass() {
        MaridTestCase.beforeClass()
    }

    @Before
    public void setUp() throws Exception {
        port = CommonTestUtils.getLocalPort();
        def rootDir = new TestFile();
        FileUtils.deleteDirectory(rootDir);
        rootDir.mkdirs();
        new File(keystorePath).delete();
        String[] args = ["-genkey", "-alias", "localhost", "-dname",
                "CN=localhost,OU=Test,O=Test,C=US", "-keyalg", "RSA",
                "-validity", "365", "-storepass", "key", "-keystore",
                "${keystorePath}", "-storepass", "${keyPass}",
                "-keypass", "${keyPass}"] as String[];
        KeyTool.main(args);
        host = CommonTestUtils.getLocalhostIp();
        HttpController.registerActions();
        httpServer = new HttpServer(host, port, keystorePath, keyPass);
        httpUtils = new OpsGenieHttpClient();
        httpServer.open();
    }

    @After
    public void tearDown() {
        if (httpServer != null) {
            httpServer.close();
        }
        HttpController.destroyInstance();
        super.tearDown()
    }

}
