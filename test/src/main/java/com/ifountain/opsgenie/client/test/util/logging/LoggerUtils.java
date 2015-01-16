
/*
 * Created on Nov 14, 2007
 *
 */
package com.ifountain.opsgenie.client.test.util.logging;

import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;

public class LoggerUtils
{

    public static final String DAILY_ROLLING_FILE_APPENDER = "DailyRollingFileAppender";
    public static final String DEFAULT_LAYOUT_PATTERN="%d{yy/MM/dd HH:mm:ss.SSS} %p: %m%n";

    public static void configureLogger(Logger aLogger,  Level level ,String fileName)
    {
        configureLogger(aLogger, level, fileName,  DEFAULT_LAYOUT_PATTERN,true);
    }
    public static void configureLogger(Logger aLogger,  Level level ,String fileName,boolean addFileAppender)
    {
        configureLogger(aLogger, level, fileName,  DEFAULT_LAYOUT_PATTERN,addFileAppender);
    }
    public static void configureLogger(Logger aLogger, Level level, String fileName, String layoutPattern, boolean addFileAppender)
    {
        File file = null;
        if(fileName != null){
            file = new File(fileName);
        }
        configureLogger(aLogger, level, file, layoutPattern, addFileAppender);
    }
    public static void configureLogger(Logger aLogger, Level level, File filePath, String layoutPattern, boolean addFileAppender)
    {
        aLogger.removeAllAppenders();

        if(level != null)
        {
            aLogger.setLevel(level);
        }

        if(addFileAppender)
        {
            aLogger.setAdditivity(false);
            if (filePath != null) {
                try {
                    PatternLayout layout = new PatternLayout(layoutPattern);
                    DailyRollingFileAppender fileappender = new DailyRollingFileAppender(layout, filePath.getPath(), ".yyyy-MM-dd");
                    fileappender.setAppend(true);
                    fileappender.setName(DAILY_ROLLING_FILE_APPENDER);
                    aLogger.addAppender(fileappender);
                } catch (IOException e) {
                    System.err.println(filePath.getPath() + " log file could not be initialized.");
                    addConsoleAppender(aLogger, layoutPattern);
                }
            }
            else {
                System.err.println(filePath.getPath() + " log file could not be initialized.");
                addConsoleAppender(aLogger, layoutPattern);
            }
        }
        else
        {
            aLogger.setAdditivity(true);
        }

    }
    //log file can be deleted and log messages will go to root logger
    public static void destroyLogger(Logger aLogger)
    {
        aLogger.removeAllAppenders();
        aLogger.setAdditivity(true);
    }
    
    public static void addConsoleAppender(Logger aLogger, String layoutPattern)
    {       	
    	PatternLayout layout = new PatternLayout( layoutPattern );
    	ConsoleAppender app = new ConsoleAppender( layout, "System.err" );
    	app.setTarget( "System.err" );
    	app.setName( "ConsoleAppender" );
    	aLogger.addAppender( app );
    }

    public static void addConsoleAppender(Logger aLogger)
    {
        addConsoleAppender(aLogger, DEFAULT_LAYOUT_PATTERN);
    }

}
