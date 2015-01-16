package com.ifountain.opsgenie.client.marid.alert

import com.ifountain.opsgenie.client.http.OpsGenieHttpClient
import com.ifountain.opsgenie.client.marid.MaridConfig
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants
import com.ifountain.opsgenie.client.script.ScriptManager
import com.ifountain.opsgenie.client.script.util.ScriptProxy
import com.ifountain.opsgenie.client.test.util.OpsGenieClientMock
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.logging.MockAppender
import org.apache.commons.io.FileUtils
import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 8/29/12
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
class AlertActionUtilsTest {
    def scriptsDir = new TestFile("scripts")
    static def scriptCalls = [];
    MockAppender logAppender;

    @Before
    public void setUp() {
        MaridConfig.destroyInstance();
        MaridConfig.getInstance().setApiKey("customer1")
        MaridConfig.getInstance().setConfiguration(new Properties([prop1: "prop1Value"]))
        MaridConfig.getInstance().setOpsGenieHttpClient(new OpsGenieHttpClient());
        MaridConfig.getInstance().setOpsGenieClient(new OpsGenieClientMock());
        scriptCalls.clear();
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());
    }

    @After
    public void tearDown() {
        Thread.sleep(300);
        MaridConfig.destroyInstance();
    }

    @Test
    public void testActionScriptExecution() throws Exception {
        def scriptFile = new File(scriptsDir, "restart.groovy");
        scriptFile.setText("""
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ALERT});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_ACTION});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_LOGGER});
           ${this.class.getName()}.scriptCall(${OpsgenieClientApplicationConstants.ScriptProxy.BINDING_SOURCE});

        """)

        Map alertmap = new HashMap();
        alertmap.put("alertId", "alert1")
        alertmap.put("entity", "router1")
        alertmap.put("username", "user1")
        def action = "restart"
        def sources = [type: "api"]

        AlertActionUtils.executeActionScript(new AlertActionUtils.AlertActionBean(action, alertmap, sources));
        assertEquals(6, scriptCalls.size());
        def alert = scriptCalls[0]
        assertEquals("alert1", alert.alertId)
        assertEquals("router1", alert.entity)
        assertEquals("user1", alert.username)

        assertEquals(action, scriptCalls[1])
        assertTrue(scriptCalls[2] instanceof ScriptProxy)
        assertSame(MaridConfig.getInstance().getOpsGenieClient(), scriptCalls[2].opsGenieClient)
        assertEquals(MaridConfig.getInstance().getConfiguration(), scriptCalls[3])
        assertSame(Logger.getLogger("script." + scriptFile.getName()), scriptCalls[4])
        assertSame(sources, scriptCalls[5])

        //script file name convention
        alertmap = new HashMap();
        alertmap.put("alertId", "alert2")
        alertmap.put("username", "user2")
        action = "re START /:; ";
        scriptCalls.clear();
        AlertActionUtils.executeActionScript(new AlertActionUtils.AlertActionBean(action, alertmap, sources));
        assertEquals(6, scriptCalls.size());

        alert = scriptCalls[0]
        assertEquals("alert2", alert.alertId)
        assertEquals("user2", alert.username)
        assertEquals(action, scriptCalls[1])
        assertTrue(scriptCalls[2] instanceof ScriptProxy)
        assertSame(MaridConfig.getInstance().getOpsGenieClient(), scriptCalls[2].opsGenieClient)
        assertEquals(MaridConfig.getInstance().getConfiguration(), scriptCalls[3])
        assertSame(Logger.getLogger("script." + scriptFile.getName()), scriptCalls[4])
        assertSame(sources, scriptCalls[5])
    }

    @Test
    public void testActionScriptExecutionWithMapping() throws Exception {
        def action = "ack"
        def scriptFile = new File(scriptsDir, "script1.groovy");
        Properties props = new Properties();
        props.put("actions." + action + ".script", scriptFile.getName())
        MaridConfig.getInstance().setConfiguration(props)

        scriptFile.setText("""
           ${this.class.getName()}.scriptCall("executed");

        """)

        Map alertmap = new HashMap();
        alertmap.put("alertId", "alert1")
        alertmap.put("entity", "router1")
        alertmap.put("username", "user1")


        AlertActionUtils.executeActionScript(new AlertActionUtils.AlertActionBean(action, alertmap, null));
        assertEquals(1, scriptCalls.size());
        assertEquals("executed", scriptCalls[0])

        //test with uppservase action
        Thread.sleep(1100)
        scriptCalls.clear();

        action = "Acknowledge"
        props = new Properties();
        props.put("actions." + action.replaceAll(" ", "") + ".script", scriptFile.getName())
        MaridConfig.getInstance().setConfiguration(props)

        scriptFile.setText("""
           ${this.class.getName()}.scriptCall("executedWithUppercase");

        """)

        alertmap = new HashMap();
        alertmap.put("alertId", "alert1")
        alertmap.put("entity", "router1")
        alertmap.put("username", "user1")

        AlertActionUtils.executeActionScript(new AlertActionUtils.AlertActionBean(action, alertmap, null));
        assertEquals(1, scriptCalls.size());
        assertEquals("executedWithUppercase", scriptCalls[0])

        //test with space
        //fails on hudson without this
        Thread.sleep(1100)
        scriptCalls.clear();

        action = "ack 2"
        props = new Properties();
        props.put("actions." + action.replaceAll(" ", "") + ".script", scriptFile.getName())
        MaridConfig.getInstance().setConfiguration(props)

        scriptFile.setText("""
           ${this.class.getName()}.scriptCall("executedWithSpace");

        """)

        alertmap = new HashMap();
        alertmap.put("alertId", "alert1")
        alertmap.put("entity", "router1")
        alertmap.put("username", "user1")

        AlertActionUtils.executeActionScript(new AlertActionUtils.AlertActionBean(action, alertmap, null));
        assertEquals(1, scriptCalls.size());
        assertEquals("executedWithSpace", scriptCalls[0])

    }

    public static void scriptCall(Object call) {
        scriptCalls.add(call);
    }
}
