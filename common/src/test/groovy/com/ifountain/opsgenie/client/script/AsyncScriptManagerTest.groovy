package com.ifountain.opsgenie.client.script

import com.ifountain.opsgenie.client.misc.SmartWait
import com.ifountain.opsgenie.client.test.util.file.TestFile
import com.ifountain.opsgenie.client.test.util.logging.MockAppender
import com.ifountain.opsgenie.client.test.util.logging.TestLogUtils
import org.apache.commons.io.FileUtils
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import static org.junit.Assert.*

/**
 * Created by burak on 7/22/14.
 */
class AsyncScriptManagerTest {

    static List messages;
    File scriptsDir;
    MockAppender logAppender;

    public static Object scriptWaitLock = new Object()
    public static Lock scriptLock = new ReentrantLock();
    private static final Condition condition = scriptLock.newCondition();

    @Before
    public void setUp() {
        messages = new ArrayList();
        ScriptManager.destroyInstance();
        scriptsDir = new TestFile("scripts");
        FileUtils.deleteDirectory(scriptsDir);
        scriptsDir.mkdirs();
        AsyncScriptManager.getInstance().initialize();
        ScriptManager.getInstance().initialize(scriptsDir.getPath());

        logAppender = TestLogUtils.addMockAppender(Logger.getLogger(AsyncScriptManager.class))
    }

    @After
    public void tearDown() {
        AsyncScriptManager.destroyInstance();
        ScriptManager.destroyInstance();
        TestLogUtils.disableLogger(Logger.getLogger(AsyncScriptManager.class))
    }

    @Test
    public void testRunScript() {
        File script = new File(scriptsDir, "trial.groovy");
        script.setText("""
            ${AsyncScriptManagerTest.class.name}.addMessage(params.param1);
            ${AsyncScriptManagerTest.class.name}.addMessage(logger);
            new Class1().getX();
        """);

        //other class access test for groovy
        File anotherClass = new File(scriptsDir, "Class1.groovy");
        anotherClass.setText("""
            class Class1{
                def getX(){
                    ${AsyncScriptManagerTest.class.name}.addMessage("getX");
                }
            }
        """);

        def parameterMap = [params: [param1: "param1Value"]]
        def long startTime = System.currentTimeMillis();
        AsyncScriptManager.getInstance().runScript(script.getName(), parameterMap)
        def long finishedTime = System.currentTimeMillis();

        //Test asyncronous. Runscript took about 240 miliseconds
        assertTrue(finishedTime - startTime < 20)

        SmartWait.waitForClosure(3000, 10, {
            assertEquals(3, messages.size())
            assertEquals(parameterMap.params.param1, messages[0])
            assertSame(Logger.getLogger("script." + script.getName()), messages[1])
            assertSame("getX", messages[2])
        });
    }

    @Test
    public void testRunScriptThrowExceptionHandleAndWriteItToLog() {

        File script = new File(scriptsDir, "trial.groovy");

        AsyncScriptManager.getInstance().runScript(script.getName(), [:])

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.WARN.toString())
            assertEquals("[AsyncScriptManager]: Error occured while processing ${script.getName()}: java.lang.Exception: Script [${script.getName()}] does not exist.".toString(),
                    logMessages[0])
        }

    }

    @Test
    public void testRunScriptWithExceptionNotThrowExceptionWriteItToLog() {

        File script = new File(scriptsDir, "trial.groovy");
        String exceptionMessage = "an exception";
        script.setText("""
            throw new Exception("${exceptionMessage}");
        """);
        def params = [params: [param1: "param1Value"]]

        AsyncScriptManager.getInstance().runScript(script.getName(), params)

        SmartWait.waitForClosure {
            def logMessages = logAppender.getMessages(Level.WARN.toString())
            assertTrue(logMessages[0].contains(exceptionMessage))
            assertTrue(logMessages[0].contains(script.getName()))
        }

    }

    @Test
    public void testActionScriptExecutionWillNotBlockOtherScriptExecutions() {

        try {
            def scriptFile1 = new File(scriptsDir, "script1.groovy");
            scriptFile1.setText("""
            ${this.class.getName()}.addMessage("script1");
        """)

            def scriptFile2 = new File(scriptsDir, "script2.groovy");
            scriptFile2.setText("""
            synchronized(${this.class.getName()}.scriptWaitLock){
                ${this.class.getName()}.addMessage("script2");
                ${this.class.getName()}.scriptWaitLock.wait()
                ${this.class.getName()}.addMessage("script2_end");
            }
        """)


            def params = [params: [:]]
            AsyncScriptManager.getInstance().runScript(scriptFile2.getName(), params)

            SmartWait.waitForClosure {
                assertEquals(1, messages.size());
                assertTrue(messages.contains("script2"));
            }

            Thread.sleep(500)

            AsyncScriptManager.getInstance().runScript(scriptFile1.getName(), params)

            SmartWait.waitForClosure {
                assertEquals(2, messages.size());
                assertTrue(messages.contains("script2"));
                assertTrue(messages.contains("script1"));
            }

            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }

            SmartWait.waitForClosure {
                assertEquals(3, messages.size());
                assertTrue(messages.contains("script1"));
                assertTrue(messages.contains("script2"));
                assertTrue(messages.contains("script2_end"));
            }
        } finally {
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }
    }

    @Test
    public void testClientWaitUnderHeavyLoad() throws Exception {

        AsyncScriptManager.getInstance().destroyInstance()
        AsyncScriptManager.getInstance().initialize(2, 2);
        def shutdownWaitTime = 1000
        AsyncScriptManager.getInstance().setShutdownWaitTime(shutdownWaitTime);

        try {

            def script = new File(scriptsDir, "script2.groovy");
            script.setText("""

                try{
                    ${AsyncScriptManagerTest.class.getName()}.scriptLock.lock();
                    ${this.class.getName()}.addMessage("afterLock");
                    ${AsyncScriptManagerTest.class.getName()}.condition.await()
                    ${this.class.getName()}.addMessage("afterAwait");
                }
                catch(Throwable t){
                    ${this.class.getName()}.addMessage("script_exception");
                } finally{
                    ${AsyncScriptManagerTest.class.getName()}.scriptLock.unlock();
                }
            """)

            def script2 = new File(scriptsDir, "script1.groovy");
            script2.setText("""
                ${this.class.getName()}.addMessage("script2");
            """)

            (1..3).each {
                AsyncScriptManager.getInstance().runScript(script.getName(), [:])
            }
            Thread.sleep(100)

            AsyncScriptManager.getInstance().runScript(script2.getName(), [:])
            Thread.sleep(100)


            SmartWait.waitForClosure {
                assertEquals(2, messages.size());
                assertTrue(messages.contains("afterLock"))
                assertFalse(messages.contains("afterAwait"))
                assertFalse(messages.contains("script2"))
                assertFalse(messages.contains("script_exception"));
            }

            try {
                AsyncScriptManager.getInstance().runScript(script2.getName(), [:])
                fail("Should throw exception when exceed the limit of scriptExecuter thread + queue size")
            } catch (RejectedExecutionException e) {
            }

            scriptLock.lock();
            condition.signalAll();
            scriptLock.unlock();

            SmartWait.waitForClosure(AsyncScriptManager.getInstance().getShutdownWaitTime(), {
                println messages
                assertEquals(6, messages.size());
                assertTrue(messages.contains("afterLock"))
                assertTrue(messages.contains("afterAwait"))
            });


            scriptLock.lock();
            condition.signalAll();
            scriptLock.unlock();

            SmartWait.waitForClosure(AsyncScriptManager.getInstance().getShutdownWaitTime(), {
                println messages
                assertEquals(7, messages.size());
                assertTrue(messages.contains("afterLock"))
                assertTrue(messages.contains("afterAwait"))
                assertTrue(messages.contains("script2"))
                assertFalse(messages.contains("script_exception"));
            });

        } finally {
            println messages
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }
    }

    @Test
    public void testDestroyWillKillTerminationAndThenWillTerminate() {

        def shutdownWaitTime = 1000
        AsyncScriptManager.getInstance().setShutdownWaitTime(shutdownWaitTime);

        try {
            def script = new File(scriptsDir, "script2.groovy");
            script.setText("""
            synchronized(${AsyncScriptManagerTest.class.getName()}.scriptWaitLock){
                ${this.class.getName()}.addMessage("script");
                try{
                ${AsyncScriptManagerTest.class.getName()}.scriptWaitLock.wait()
                ${this.class.getName()}.addMessage("script2");
                }
                catch(Throwable t){
                ${this.class.getName()}.addMessage("script_exception");
                }

            }
        """)

            def parameterMap = [params: [:]]
            AsyncScriptManager.getInstance().runScript(script.getName(), parameterMap)

            SmartWait.waitForClosure {
                assertEquals(1, messages.size());
                assertTrue(messages.contains("script"));
            }

            Thread.sleep(500)

            long t = System.currentTimeMillis()
            AsyncScriptManager.destroyInstance();

            def shutdownTime = System.currentTimeMillis() - t
            assertTrue("shutdownTime:" + shutdownTime + " should be greater than " + shutdownWaitTime, shutdownTime >= shutdownWaitTime)

            SmartWait.waitForClosure {
                assertEquals(2, messages.size());
                assertTrue(messages.contains("script"));
                assertTrue(messages.contains("script_exception"));
            }
        } finally {
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }

    }

    @Test
    public void testDestroyWillWaitGracefullTerminationAndThenWillTerminate() {

        def shutdownWaitTime = 1000
        AsyncScriptManager.getInstance().setShutdownWaitTime(shutdownWaitTime);

        try {
            def script = new File(scriptsDir, "script2.groovy");
            script.setText("""
            synchronized(${AsyncScriptManagerTest.class.getName()}.scriptWaitLock){
                ${this.class.getName()}.addMessage("script");
                try{
                ${AsyncScriptManagerTest.class.getName()}.scriptWaitLock.wait()
                Thread.sleep(200)
                ${this.class.getName()}.addMessage("script2");
                }
                catch(Throwable t){
                ${this.class.getName()}.addMessage("script_exception");
                }

            }
        """)

            def parameterMap = [params: [:]]
            AsyncScriptManager.getInstance().runScript(script.getName(), parameterMap)

            SmartWait.waitForClosure {
                assertEquals(1, messages.size());
                assertTrue(messages.contains("script"));
            }

            Thread.sleep(500)

            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
            AsyncScriptManager.destroyInstance();

            SmartWait.waitForClosure {
                assertEquals(2, messages.size());
                assertTrue(messages.contains("script"));
                assertTrue(messages.contains("script2"));
            }
        } finally {
            synchronized (scriptWaitLock) {
                scriptWaitLock.notifyAll()
            }
        }
    }

    public static void addMessage(Object message) {
        messages.add(message);
    }
}
