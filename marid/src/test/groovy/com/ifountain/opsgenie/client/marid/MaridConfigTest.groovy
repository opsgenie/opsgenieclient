package com.ifountain.opsgenie.client.marid

import com.ifountain.opsgenie.client.test.util.CommonTestUtils
import com.ifountain.opsgenie.client.test.util.MaridTestCase
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.util.ClientProxyConfiguration
import org.apache.commons.io.FileUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

class MaridConfigTest extends MaridTestCase {

    @Before
    public void setUp() {
        File testFileDir = new TestFile();
        FileUtils.deleteDirectory(testFileDir);
        testFileDir.mkdirs();
        MaridConfig.destroyInstance();
    }

    @After
    public void tearDown() {
        MaridConfig.destroyInstance();
        super.tearDown()
    }

    @Test
    public void testInit() {
        String host = CommonTestUtils.getLocalhostIp()
        int port = CommonTestUtils.getLocalPort()
        String username = "user1";
        String password = "pass";
        String protocol = "https";
        File configFile = new TestFile("conf.props");
        configFile.setText("""
            http.proxy.enabled=true
            http.proxy.port=${port}
            http.proxy.host=${host}
            http.proxy.authMethod=${ClientProxyConfiguration.AuthType.BASIC.name()}
            http.proxy.username=${username}
            http.proxy.password=${password}
            http.proxy.protocol=${protocol}
            apiKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            opsgenie.api.url=url1
            extra.config.param=extraval
        """)
        MaridConfig.getInstance().init(configFile.getCanonicalPath())
        //test opegnie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertEquals(host, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyHost);
        assertEquals(port, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyPort);
        assertEquals(username, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyUsername);
        assertEquals(password, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyPassword);
        assertEquals(protocol, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyProtocol);
        assertEquals(ClientProxyConfiguration.AuthType.BASIC, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.authType);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))
    }

    @Test
    public void testInitWithUsingConfInHomeDir() {
        String host = CommonTestUtils.getLocalhostIp()
        int port = CommonTestUtils.getLocalPort()
        String username = "user1";
        String password = "pass";
        String protocol = "https";
        File configFile = new File("conf/marid.conf");
        configFile.parentFile.mkdirs()
        configFile.setText("""
            http.proxy.enabled=true
            http.proxy.port=${port}
            http.proxy.host=${host}
            http.proxy.authMethod=${ClientProxyConfiguration.AuthType.BASIC.name()}
            http.proxy.username=${username}
            http.proxy.password=${password}
            http.proxy.protocol=${protocol}
            apiKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            opsgenie.api.url=url1
            extra.config.param=extraval
        """)
        MaridConfig.getInstance().init(new TestFile("conf.props").getCanonicalPath())
        //test opegnie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertEquals(host, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyHost);
        assertEquals(port, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyPort);
        assertEquals(username, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyUsername);
        assertEquals(password, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyPassword);
        assertEquals(protocol, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.proxyProtocol);
        assertEquals(ClientProxyConfiguration.AuthType.BASIC, MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration.authType);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))

        FileUtils.deleteDirectory(configFile.parentFile)
    }

    @Test
    public void testInitWithDisabledProxy() {
        String host = CommonTestUtils.getLocalhostIp()
        int port = CommonTestUtils.getLocalPort()
        String username = "user1";
        String password = "pass";
        String protocol = "https";
        File configFile = new TestFile("conf.props");
        configFile.setText("""
            http.proxy.enabled=false
            http.proxy.port=${port}
            http.proxy.host=${host}
            http.proxy.username=${username}
            http.proxy.password=${password}
            http.proxy.protocol=${protocol}
            http.proxy.authMethod=${ClientProxyConfiguration.AuthType.BASIC.name()}
            apiKey=123
            maridKey=key1
            opsgenie.connection.timeout=20
            opsgenie.connection.sockettimeout=25
            opsgenie.connection.maxConnectionCount=26
            opsgenie.api.url=url1
            extra.config.param=extraval
        """)
        MaridConfig.getInstance().init(configFile.getCanonicalPath())
        //test opegnie client config
        assertEquals(26, MaridConfig.getInstance().getOpsGenieHttpClient().config.maxConnections);
        assertEquals(25000, MaridConfig.getInstance().getOpsGenieHttpClient().config.socketTimeout);
        assertEquals(20000, MaridConfig.getInstance().getOpsGenieHttpClient().config.connectionTimeout);
        assertNull(MaridConfig.getInstance().getOpsGenieHttpClient().config.clientProxyConfiguration);
        assertNotNull(MaridConfig.getInstance().opsGenieClient)
        assertEquals(MaridConfig.getInstance().getOpsgenieApiUrl(), MaridConfig.getInstance().opsGenieClient.jsonHttpClient.rootUri)
        assertEquals("extraval", MaridConfig.getInstance().getConfiguration().getProperty("extra.config.param"))
    }
}
