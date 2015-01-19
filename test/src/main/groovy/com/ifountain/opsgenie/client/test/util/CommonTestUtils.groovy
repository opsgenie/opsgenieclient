
/*
 * Created on Aug 31, 2007
 *
 */
package com.ifountain.opsgenie.client.test.util

public class CommonTestUtils
{

    public static Properties testProperties;

    public static void initializeFromFile(String fileName){
        testProperties = new Properties();
        FileInputStream stream = null;
        try {
        	
        	stream = new FileInputStream(fileName);
            testProperties.load(stream);
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } finally {
        	try {
    			if (stream != null ) stream.close();
    		} catch (IOException e1) {
    
    		}
        }
    }
    public static String getTestProperty(String propertyName) {
        String value = testProperties.getProperty(String.valueOf(propertyName));
        if(value == null)
        {
            throw new RuntimeException("Test property "+propertyName+" does not exists in testProperties file");
        }
        return value.trim();
    }
    public static String getTestProperty(String propertyName, String defaultValue) {
        return testProperties.getProperty(String.valueOf(propertyName), defaultValue).trim();
    }

    public static String getLocalhostIp() {
        return getTestProperty("Localhost");
    }

    public static int getLocalPort(){
        return Integer.parseInt(CommonTestUtils.getTestProperty("LocalPort"));
    }

}
