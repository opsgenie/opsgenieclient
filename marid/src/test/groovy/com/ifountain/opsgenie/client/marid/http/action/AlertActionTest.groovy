package com.ifountain.opsgenie.client.marid.http.action;


import com.ifountain.opsgenie.client.OpsGenieClient
import com.ifountain.opsgenie.client.marid.MaridConfig
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.script.ScriptManager
import com.ifountain.opsgenie.client.test.util.RequestActionTestCase
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.http.HttpStatusException
import com.ifountain.opsgenie.client.util.JsonUtils
import org.apache.commons.io.FileUtils
import org.apache.log4j.Logger
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import com.ifountain.opsgenie.client.script.util.ScriptProxy
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/15/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlertActionTest extends RequestActionTestCase {
    static Map objects;
    String url;
    File scriptsDir;

    @Before
    public void setUp() {
        super.setUp()
        MaridConfig.destroyInstance()
        MaridConfig.getInstance().setOpsGenieClient(new OpsGenieClient())
        MaridConfig.getInstance().setApiKey("key1")
        MaridConfig.getInstance().setConfiguration(new Properties())
        objects = new HashMap();
        url = "https://${host}:${port}/alert/action"
        scriptsDir = new TestFile("scripts");
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());
    }

    @Override
    public void tearDown() {
        MaridConfig.destroyInstance()
        super.tearDown()
    }

    @Test
    public void testSuccessfulAlertExecution() throws Exception {
        def props = new Properties()
        props.putAll([con1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String script = """
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT})
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT})
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION})
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER})
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE})
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        def source = [type: "api"]
        def alertPropertyMap = [alertId: "alert1", username: "user1"]
        String requestContent = JsonUtils.toJson([alert: alertPropertyMap, action: actionName, source: source])
        def response = doRequest([:], requestContent);
        assertEquals(JsonUtils.toJson([success: true]), response)
        assertEquals(6, objects.size())
        assertEquals(alertPropertyMap, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT])
        assertEquals(props, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF])
        assertEquals(actionName, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION])
        assertEquals([type: "api"], objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE])
        assertEquals(Logger.getLogger("script." + scriptFile.getName()), objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER])
        assertTrue(objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT] instanceof ScriptProxy)
        assertEquals(MaridConfig.getInstance().opsGenieClient, objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT].opsGenieClient)
    }


    @Test
    public void testConfigPropsWillNotBeModified() throws Exception {
        def props = new Properties()
        props.putAll([conf1: "conf1Value"])
        MaridConfig.getInstance().setConfiguration(props)
        String script = """
            ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF}.conf1 = "updatedval"
            ${AlertActionTest.class.getName()}.addObject("${
            OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF
        }", ${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF})
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        def alertPropertyMap = [alertId: "alert1", username: "user1"]
        String requestContent = JsonUtils.toJson([alert: alertPropertyMap, action: actionName])

        def response = doRequest([:], requestContent);
        assertEquals(JsonUtils.toJson([success: true]), response)
        assertEquals(1, objects.size())
        assertEquals("updatedval", objects[OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF].conf1)
        assertEquals("conf1Value", props.conf1)
    }

    @Test
    public void testWithMaridKey() throws Exception {
        MaridConfig.getInstance().setMaridKey("key1")
        String script = """
        ${AlertActionTest.class.getName()}.addObject("executed", true)
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        def alertPropertyMap = [alertId: "alert1", username: "user1"]
        String requestContent = JsonUtils.toJson([alert: alertPropertyMap, action: actionName])
        def params = [:]
        params[OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER] = MaridConfig.getInstance().getMaridKey();
        def response = doRequest(params, requestContent);
        assertEquals(JsonUtils.toJson([success: true]), response)
        assertEquals(1, objects.size())
        assertTrue(objects.executed)
    }


    @Test
    public void testWithNoActionShouldReturnSuccessForOpsGenieWebhookTest() throws Exception {
        MaridConfig.getInstance().setMaridKey("key1")
        def params = [:]
        params[OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER] = MaridConfig.getInstance().getMaridKey();
        def response = doRequest(params, JsonUtils.toJson([:]));
        def responseData = JsonUtils.parse(response);
        assertEquals(true, responseData.success)
        assertEquals("No action specified. Ignoring message :{}", responseData.message)
        assertEquals(0, objects.size())
    }

    @Test
    public void testWithNotSpecifiedMaridKeyWillThrowException() throws Exception {
        MaridConfig.getInstance().setMaridKey("key1")
        String script = """
        ${AlertActionTest.class.getName()}.addObject("executed", true)
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        def alertPropertyMap = [alertId: "alert1", username: "user1"]
        String requestContent = JsonUtils.toJson([alert: alertPropertyMap, action: actionName])
        try {
            doRequest([:], requestContent);

        }
        catch (HttpStatusException ex) {
            Map res = JsonUtils.parse(new String(ex.getContent()))
            assertEquals(0, objects.size())
            assertFalse(res.success)
            assertTrue(res.error.indexOf("invalid " + OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER) >= 0)
        }
    }

    @Test
    public void testWithScriptException() throws Exception {
        String exceptionMessage = "exception occurred"
        String script = """
        throw new Exception("${exceptionMessage}")
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        def alertPropertyMap = [alertId: "alert1", username: "user1"]
        String requestContent = JsonUtils.toJson([alert: alertPropertyMap, action: actionName])
        try {
            httpUtils.doPostRequest(url, requestContent);
        }
        catch (HttpStatusException ex) {
            Map res = JsonUtils.parse(new String(ex.getContent()))
            assertFalse(res.success)
            assertTrue(res.error.indexOf(exceptionMessage) >= 0)
        }
    }

    @Test
    public void testWithInvalidAlertContent() throws Exception {
        String script = """
        ${AlertActionTest.class.getName()}.addObject("executed", true)
        """;
        String actionName = "action1";
        File scriptFile = new File(scriptsDir, "${actionName}.groovy");
        scriptFile.setText(script);

        String requestContent = JsonUtils.toJson([alert: "invalid alert", action: actionName])
        try {
            httpUtils.doPostRequest(url, requestContent);
        }
        catch (HttpStatusException ex) {
            Map res = JsonUtils.parse(new String(ex.getContent()))
            println res
            assertFalse(res.success)
            assertEquals(0, objects.size())
        }
    }


    private String doRequest(Map paramsMap, String content) {
        def urlParams = [];
        paramsMap.each { name, value ->
            urlParams << "${name}=${URLEncoder.encode(value)}"
        }
        url = "${url}?${urlParams.join("&")}"
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(content, "UTF-8");
        entity.setChunked(true);
        post.setEntity(entity);
        httpUtils.executePostMethod(post);
    }

    public static void addObject(name, value) {
        objects.put(name, value)
    }
}
