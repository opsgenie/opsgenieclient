package com.ifountain.opsgenie.client.marid.http.action

import com.ifountain.opsgenie.client.http.OpsGenieHttpResponse
import com.ifountain.opsgenie.client.misc.SmartWait
import com.ifountain.opsgenie.client.script.AsyncScriptManager;
import com.ifountain.opsgenie.client.script.ScriptManager
import com.ifountain.opsgenie.client.test.util.RequestActionTestCase
import com.ifountain.opsgenie.client.test.util.file.TestFile
import org.apache.commons.io.FileUtils

import com.ifountain.opsgenie.client.util.JsonUtils

import org.apache.log4j.Logger
import com.ifountain.opsgenie.client.marid.MaridConfig
import com.ifountain.opsgenie.client.OpsGenieClient
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import org.junit.After
import org.junit.Before
import org.junit.Test;
import static org.junit.Assert.*
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/15/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptActionTest extends RequestActionTestCase {
    static Map objects;
    String url;
    File scriptsDir;

    @Before
    public void setUp() {
        super.setUp()
        MaridConfig.destroyInstance()
        MaridConfig.getInstance().setOpsGenieClient(new OpsGenieClient())
        objects = new HashMap();
        url = "https://${host}:${port}/script"
        scriptsDir = new TestFile("scripts");
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());
        AsyncScriptManager.getInstance().initialize(3, 10);
    }

    @After
    public void tearDown() {
        MaridConfig.destroyInstance()
        super.tearDown()
    }


    @Test
    public void testSuccessfulScriptExecution() throws Exception {
        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String expectedResponseContent = "content"
        int expectedResponseStatus = 255
        String expectedContentType = "application/xml"
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "${expectedResponseContent}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.contentType = "${expectedContentType}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.status = ${expectedResponseStatus}
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        def params = [param1: "param1Value"]
        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals(expectedResponseContent, new String(response.getContent()))
        assertEquals(expectedContentType, response.getContentType())
        assertEquals(expectedResponseStatus, response.getStatusCode())
        assertEquals(5, objects.size())
        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS])
        assertEquals(props, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF])
        assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST].parameters)
        assertEquals(Logger.getLogger("script." + scriptFile.getName()), objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER])
        assertEquals(MaridConfig.getInstance().opsGenieClient, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT].opsGenieClient)
    }

    @Test
    public void testSuccessfulScriptExecutionWithoutResponseModification() throws Exception {
        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String script = """
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        def params = [param1: "param1Value"]
        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals(JsonUtils.toJson([success: true]), new String(response.getContent()))
        assertEquals("application/json; charset=UTF-8", response.getContentType())
        assertEquals(200, response.getStatusCode())
        assertEquals(1, objects.size())
        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS])
    }

    @Test
    public void testSuccessfulScriptExecutionWithoutContenttype() throws Exception {
        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String expectedResponseContent = "content"
        String script = """
        ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "${expectedResponseContent}"
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        def params = [param1: "param1Value"]
        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals(expectedResponseContent, new String(response.getContent()))
        assertEquals("text/html; charset=UTF-8", response.getContentType())
        assertEquals(200, response.getStatusCode())
    }


    @Test
    public void testConfigPropsWillNotBeModified() throws Exception {
        def props = new Properties()
        props.putAll([conf1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF}.conf1 = "updatedval"
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        def params = [param1: "param1Value"]
        def response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals(JsonUtils.toJson([success: true]), response.contentAsString)
        assertEquals(1, objects.size())
        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        assertEquals("updatedval", objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF].conf1)
        assertEquals("conf1Value", props.conf1)
    }

    @Test
    public void testScriptMayModifyContent() throws Exception {
        String script = """
        ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "contentupdated"
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        String requestContent = "content1"
        def params = [param1: "param1Value"]
        def response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals("contentupdated", response.contentAsString)
    }

    @Test
    public void testScriptWithMaridKey() throws Exception {
        MaridConfig.getInstance().setMaridKey("key1")
        String script = """
        ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
        ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "contentupdated"
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        String requestContent = "content1"
        def params = [param1: "param1Value"]
        params[OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER] = MaridConfig.getInstance().getMaridKey();
        def response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals("contentupdated", response.contentAsString)
        assertNull(objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS][OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER])
    }

    @Test
    public void testScriptWithEmptyMaridKey() throws Exception {
        MaridConfig.getInstance().setMaridKey("")
        String script = """
        ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "contentupdated"
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        String requestContent = "content1"
        def params = [param1: "param1Value"]
        params[OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER] = MaridConfig.getInstance().getMaridKey();
        def response = httpUtils.post(url + "/" + scriptFile.getName(), params);
        assertEquals("contentupdated", response.contentAsString)
    }

    @Test
    public void testScriptWithNotSpeiciedMaridKeyWillThrowException() throws Exception {
        MaridConfig.getInstance().setMaridKey("key1")
        String script = """
        ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "contentupdated"
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        String requestContent = "content1"
        def params = [param1: "param1Value"]
        try {
            httpUtils.post(url + "/" + scriptFile.getName(), params);
        }
        catch (IOException ex) {
            Map res = JsonUtils.parse(new String(ex.getContent()))
            assertFalse(res.success)
            assertTrue(res.error.indexOf("invalid " + OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER) >= 0)
        }
    }

    @Test
    public void testScriptWithException() throws Exception {
        String exceptionMessage = "exception occurred"
        String script = """
        throw new Exception("${exceptionMessage}")
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        String requestContent = "content1"
        def params = [param1: "param1Value"]
        try {
            httpUtils.post(url + "/" + scriptFile.getName(), params);
        }
        catch (IOException ex) {
            Map res = JsonUtils.parse(new String(ex.getContent()))
            assertFalse(res.success)
            assertTrue(res.error.indexOf(exceptionMessage) >= 0)
        }

    }

    @Test
    public void testScriptWithAsyncKeyTrue() throws Exception {

        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String expectedResponseContent = "content"
        int expectedResponseStatus = 255
        String expectedContentType = "application/xml"
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "${expectedResponseContent}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.contentType = "${expectedContentType}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.status = ${expectedResponseStatus}
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        Map<String, Object> params = [:];
        params = ["async": "true", "param1": "param1Value"]

        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);

        assertEquals("{\"success\":true}", new String(response.getContent()))
        assertEquals("application/json; charset=UTF-8", response.getContentType())
        assertEquals(200, response.getStatusCode())

        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        expectedParams.remove(OpsgenieClientApplicationConstants.Marid.ASYNC)

        def expectedParameters = [scriptName: scriptFile.getName()]
        expectedParameters.putAll(params)

        SmartWait.waitForClosure {
            assertEquals(5, objects.size())

            println objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS]
            println "expectedParams " + expectedParams
            assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS])
            assertEquals(props, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF])
            assertEquals(expectedParameters, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST].parameters)
            assertEquals(Logger.getLogger("script." + scriptFile.getName()), objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER])
            assertEquals(MaridConfig.getInstance().opsGenieClient, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT].opsGenieClient)
        }

    }

    @Test
    public void testScriptWithAsyncKeyFalse() throws Exception {

        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String expectedResponseContent = "content"
        int expectedResponseStatus = 255
        String expectedContentType = "application/xml"
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "${expectedResponseContent}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.contentType = "${expectedContentType}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.status = ${expectedResponseStatus}
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        Map<String, Object> params = [:];
        params = ["async": "false", "param1": "param1Value"]

        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);

        assertEquals(expectedResponseContent, new String(response.getContent()))
        assertEquals(expectedContentType, response.getContentType())
        assertEquals(expectedResponseStatus, response.getStatusCode())
        assertEquals(5, objects.size())
        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        expectedParams.remove(OpsgenieClientApplicationConstants.Marid.ASYNC)

        def expectedParameters = [scriptName: scriptFile.getName()]
        expectedParameters.putAll(params)
        assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS])
        assertEquals(props, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF])
        assertEquals(expectedParameters, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST].parameters)
        assertEquals(Logger.getLogger("script." + scriptFile.getName()), objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER])
        assertEquals(MaridConfig.getInstance().opsGenieClient, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT].opsGenieClient)

    }

    @Test
    public void testScriptWithAsyncKeyNotBoolean() throws Exception {

        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String expectedResponseContent = "content"
        int expectedResponseStatus = 255
        String expectedContentType = "application/xml"
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.content = "${expectedResponseContent}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.contentType = "${expectedContentType}"
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE}.status = ${expectedResponseStatus}
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST})
            ${ScriptActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER})
        """;
        File scriptFile = new File(scriptsDir, "trial.groovy");
        scriptFile.setText(script);

        Map<String, Object> params = [:];
        params = ["async": "notBoolean", "param1": "param1Value"]

        OpsGenieHttpResponse response = httpUtils.post(url + "/" + scriptFile.getName(), params);

        assertEquals(expectedResponseContent, new String(response.getContent()))
        assertEquals(expectedContentType, response.getContentType())
        assertEquals(expectedResponseStatus, response.getStatusCode())
        assertEquals(5, objects.size())
        def expectedParams = [scriptName: scriptFile.getName()]
        expectedParams.putAll(params)
        expectedParams.remove(OpsgenieClientApplicationConstants.Marid.ASYNC)

        def expectedParameters = [scriptName: scriptFile.getName()]
        expectedParameters.putAll(params)
        assertEquals(expectedParams, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS])
        assertEquals(props, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF])
        assertEquals(expectedParameters, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST].parameters)
        assertEquals(Logger.getLogger("script." + scriptFile.getName()), objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER])
        assertEquals(MaridConfig.getInstance().opsGenieClient, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT].opsGenieClient)

    }

    public static void addObject(name, value) {
        objects.put(name, value)
    }
}
