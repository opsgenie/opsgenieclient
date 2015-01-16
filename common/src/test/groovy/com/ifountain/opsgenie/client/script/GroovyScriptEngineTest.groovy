package com.ifountain.opsgenie.client.script

import com.ifountain.opsgenie.client.test.util.file.TestFile
import org.apache.commons.io.FileUtils
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 2:37 PM
 */
class GroovyScriptEngineTest {
    def scriptsDir = new File(TestFile.TESTOUTPUT_DIR + "/scripts")

    @Before
    public void setUp() {
        FileUtils.deleteDirectory(scriptsDir)
        scriptsDir.mkdirs();
    }

    @Test
    public void testRunScript() throws Exception {
        def scriptCalls = [:]
        File scriptFile = new File(scriptsDir, "MyScript.groovy");
        scriptFile.setText("""
            scriptCalls.binding1 = binding1;
            scriptCalls.binding2 = binding2;
            scriptCalls.someParam = "someValue";
        """)

        GroovyCompiledScriptEngine engine = new GroovyCompiledScriptEngine();
        engine.initialize(Thread.currentThread().getContextClassLoader(), scriptsDir)

        def binding1Object = new Object();
        engine.runScript(scriptFile, [scriptCalls: scriptCalls, binding1: binding1Object, binding2: "binding2Value"])

        assertEquals(3, scriptCalls.size())
        assertSame(binding1Object, scriptCalls.binding1)
        assertEquals("binding2Value", scriptCalls.binding2)
        assertEquals("someValue", scriptCalls.someParam)
    }

    @Test
    public void testRecompilesScriptWhenFileLastModifyDateChanged() throws Exception {
        def scriptCalls = [:]
        File scriptFile = new File(scriptsDir, "MyScript.groovy");
        scriptFile.setText("""
            scriptCalls.binding1 = binding1;
        """)
        def lastModified = scriptFile.lastModified();
        GroovyCompiledScriptEngine engine = new GroovyCompiledScriptEngine();
        engine.initialize(Thread.currentThread().getContextClassLoader(), scriptsDir)

        def binding1Object = new Object();
        engine.runScript(scriptFile, [scriptCalls: scriptCalls, binding1: binding1Object])

        assertEquals(1, scriptCalls.size())
        assertSame(binding1Object, scriptCalls.binding1)


        scriptCalls.clear();
        scriptFile.setText("""
            scriptCalls.binding2 = binding2;
        """)
        scriptFile.setLastModified(lastModified + 10000);

        engine.runScript(scriptFile, [scriptCalls: scriptCalls, binding2: "binding2Value"])

        assertEquals(1, scriptCalls.size())
        assertSame("binding2Value", scriptCalls.binding2)

        scriptCalls.clear();
        scriptFile.setText("""
            scriptCalls.binding3 = binding3;
        """)
        scriptFile.setLastModified(lastModified - 10000);

        engine.runScript(scriptFile, [scriptCalls: scriptCalls, binding3: "binding3Value"])

        assertEquals(1, scriptCalls.size())
        assertSame("binding3Value", scriptCalls.binding3)
    }

    @Test
    public void testThrowsExceptionIfScriptCannotBeCompiled() throws Exception {
        File scriptFile = new File(scriptsDir, "MyScript.groovy");
        scriptFile.setText("""
            }}
        """)

        GroovyCompiledScriptEngine engine = new GroovyCompiledScriptEngine();
        engine.initialize(Thread.currentThread().getContextClassLoader(), scriptsDir)

        try {
            engine.runScript(scriptFile, [:])
            fail("should throw exception")
        }
        catch (MultipleCompilationErrorsException ignored) {
        }
    }

    @Test
    public void testThrowsExceptionIfScriptThrowsException() throws Exception {
        File scriptFile = new File(scriptsDir, "MyScript.groovy");
        scriptFile.setText("""
            throw new Exception("script exception")
        """)

        GroovyCompiledScriptEngine engine = new GroovyCompiledScriptEngine();
        engine.initialize(Thread.currentThread().getContextClassLoader(), scriptsDir)

        try {
            engine.runScript(scriptFile, [:])
            fail("should throw exception")
        }
        catch (Exception e) {
            assertEquals("script exception", e.getMessage())
        }
    }
}
