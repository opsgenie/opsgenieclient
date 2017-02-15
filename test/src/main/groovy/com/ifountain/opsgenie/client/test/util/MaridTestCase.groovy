package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.script.ScriptManager
import org.junit.After
import org.junit.BeforeClass

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/7/12 1:51 PM
 */
class MaridTestCase {
    private static boolean isSetupCompleted = false;

    @BeforeClass
    public static void beforeClass() {
        if (!isSetupCompleted) {
            isSetupCompleted = true;
            println "CURRENT DIRECTORY : " + new File(".").getCanonicalPath() + " !!!!!!!"
            CommonTestUtils.initializeFromFile("Test.properties");
        }
    }

    @After
    public void tearDown() {
        ScriptManager.destroyInstance();
    }
}
