/*
* Created on Jan 21, 2008
*
*/
package com.ifountain.opsgenie.client.test.util.logging

import org.apache.logging.log4j.Level
import org.slf4j.Logger;


public class TestLogUtils {
    public static Logger log = Logger.getLogger(TestLogUtils.class);

    public static MockAppender addMockAppender(Logger logger) {
        logger.removeAllAppenders();
        MockAppender mockAppender = new MockAppender();
        logger.addAppender(mockAppender);
        logger.setLevel(Level.DEBUG);
        return mockAppender;
    }

    public static void disableLogger(Logger logger) {
        logger.removeAllAppenders();
    }


}
