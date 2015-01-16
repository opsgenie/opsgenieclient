
package com.ifountain.opsgenie;

import com.ifountain.opsgenie.client.test.util.logging.LoggerUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: iFountain
 * Date: Nov 6, 2008
 * Time: 2:09:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoggerUtilsTest {

    @Test
    public void testConfigureLoggerWithAllParams(){
        Logger logger= Logger.getLogger("testlogger");
        String logFile="testlogfile";
        

        LoggerUtils.configureLogger(logger, Level.DEBUG, logFile, false);
        assertEquals(logger.getLevel(), Level.DEBUG);
        assertFalse(logger.getAllAppenders().hasMoreElements());
        assertTrue(logger.getAdditivity());

        LoggerUtils.configureLogger(logger, Level.INFO,logFile,false);
        assertEquals(logger.getLevel(), Level.INFO);
        assertFalse(logger.getAllAppenders().hasMoreElements());
        assertTrue(logger.getAdditivity());
        
        LoggerUtils.configureLogger(logger, Level.INFO,logFile,true);
        assertEquals(logger.getLevel(), Level.INFO);
        assertTrue(logger.getAllAppenders().hasMoreElements());
        assertFalse(logger.getAdditivity());
        
        assertEquals(logger.getAllAppenders().nextElement().getClass(),(new DailyRollingFileAppender()).getClass());

    }

    @Test
    public void testDestroyLogger()
    {
        Logger logger= Logger.getLogger("testlogger");
        String logFile="testlogfile";

        LoggerUtils.configureLogger(logger, Level.INFO,logFile,true);

        assertFalse(logger.getAdditivity());
        assertTrue(logger.getAllAppenders().hasMoreElements());
        
        LoggerUtils.destroyLogger(logger);

        assertTrue(logger.getAdditivity());
        assertFalse(logger.getAllAppenders().hasMoreElements());

    }
    
    
}
