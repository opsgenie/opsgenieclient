package com.ifountain.opsgenie.client.script

import com.ifountain.opsgenie.client.test.util.file.TestFile
import org.apache.commons.io.FileUtils
import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

class ScriptManagerTest {
    static List messages;
    File scriptsDir;

    @Before
    public void setUp() {
        messages = new ArrayList();
        ScriptManager.destroyInstance();
        scriptsDir = new TestFile("scripts");
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());
    }

    @After
    public void tearDown() {
        ScriptManager.destroyInstance();
    }

    @Test
    public void testRunScript() {
        File script = new File(scriptsDir, "trial.groovy");
        script.setText("""
            ${ScriptManagerTest.class.name}.addMessage(params.param1);
            ${ScriptManagerTest.class.name}.addMessage(logger);
            new Class1().getX();
        """);

        //other class access test for groovy
        File anotherClass = new File(scriptsDir, "Class1.groovy");
        anotherClass.setText("""
            class Class1{
                def getX(){
                    ${ScriptManagerTest.class.name}.addMessage("getX");
                }
            }
        """);
        def parameterMap = [params: [param1: "param1Value"]]
        ScriptManager.getInstance().runScript(script.getName(), parameterMap)
        assertEquals(3, messages.size())
        assertEquals(parameterMap.params.param1, messages[0])
        assertSame(Logger.getLogger("script." + script.getName()), messages[1])
        assertSame("getX", messages[2])
    }

    @Test
    public void testRunScriptWillDiscardnullParameter() {
        File script = new File(scriptsDir, "trial.groovy");
        script.setText("""
            ${ScriptManagerTest.class.name}.addMessage(params);
        """);
        def parameterMap = [params: null]
        try {
            ScriptManager.getInstance().runScript(script.getName(), parameterMap)
            fail("Should throw exception");
        }
        catch (Throwable t) {
            assertTrue(t.getCause().toString(), t.getCause().toString().indexOf("No such property: params") >= 0)
        }
    }

    @Test
    public void testRunScriptWithException() {
        File script = new File(scriptsDir, "trial.groovy");
        String exceptionMessage = "an exception";
        script.setText("""
            throw new Exception("${exceptionMessage}");
        """);
        def params = [params: [param1: "param1Value"]]
        try {
            ScriptManager.getInstance().runScript(script.getName(), params)
            fail("Should throw exception");
        }
        catch (Throwable t) {
            assertTrue(t.getCause().toString().indexOf(exceptionMessage) >= 0)
        }
    }

    @Test
    public void testRunScriptThrowExceptionIfScriptFileDoesNotExist() {
        File script = new File(scriptsDir, "trial.groovy");
        try {
            ScriptManager.getInstance().runScript(script.getName(), [:])
            fail("Should throw exception");
        }
        catch (Throwable t) {
            assertEquals("Script [${script.getName()}] does not exist.", t.getMessage())
        }
    }

    @Test
    public void testRunScriptThrowExceptionLanguageIsNotRegistered() {
        File script = new File(scriptsDir, "trial.unknown");
        script.setText("""
        int x =5;
        """);
        try {
            ScriptManager.getInstance().runScript(script.getName(), [:])
            fail("Should throw exception");
        }
        catch (Throwable t) {
            assertTrue(t.toString().indexOf("unable to determine language for") >= 0)
        }
    }


    public static void addMessage(Object message) {
        messages.add(message);
    }
}
