/*
* Created on Jan 21, 2008
*
*/
package com.ifountain.opsgenie.client.test.util.logging;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Enumeration;

public class TestLogUtils {
    public static Logger log = Logger.getLogger(TestLogUtils.class);

    public static void enableLogger() {
        enableLogger(TestLogUtils.log);
    }

    public static void disableLogger() {
        disableLogger(TestLogUtils.log);
    }

    public static void enableLogger(Logger logger) {
        logger.removeAllAppenders();
        LoggerUtils.addConsoleAppender(logger, "%d{yy/MM/dd HH:mm:ss.SSS} %p: (%F:%L) %m%n");
        logger.setLevel(Level.DEBUG);
    }

    public static void enableLogger(Logger logger, Level level) {
        enableLogger(logger);
        logger.setLevel(level);
    }

    public static void enableRootLogger() {
        enableLogger(Logger.getRootLogger());
    }

    public static void enableRootLogger(Level level) {
        enableLogger(Logger.getRootLogger(), level);
    }

    public static void enableRootLoggerWithoutThirdParties() {
        enableLogger(Logger.getRootLogger());
        Enumeration loggers = LogManager.getCurrentLoggers();
        while(loggers.hasMoreElements()){
            Object element = loggers.nextElement();
            if(element instanceof Logger){
                Logger logger = (Logger) element;
                if(logger.getName().startsWith("com.amazon") || logger.getName().startsWith("org.apache")){
                    logger.setLevel(Level.WARN);
                }
            }
        }
    }

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
