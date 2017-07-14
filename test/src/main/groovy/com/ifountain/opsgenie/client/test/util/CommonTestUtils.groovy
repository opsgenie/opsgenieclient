
/*
 * Created on Aug 31, 2007
 *
 */
package com.ifountain.opsgenie.client.test.util

import com.ifountain.opsgenie.client.swagger.model.SuccessResponse

import static org.junit.Assert.assertEquals

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

    public static SuccessResponse createGenericSuccessResponse() {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setResult("Request will be processed");
        successResponse.setTook(0.169f);
        successResponse.setRequestId("request-id");

        return successResponse;
    }

    public static SuccessResponseMock createGenericSuccessResponseMock() {
        SuccessResponseMock successResponseMock = new SuccessResponseMock();
        successResponseMock.setResult("Request will be processed");
        successResponseMock.setTook(0.169f);
        successResponseMock.setRequestId("request-id");

        return successResponseMock;
    }

    public static void assertGenericResponseWithoutData(Map response) {
        assertEquals("Request will be processed", response.result);
        assertEquals(0.169f, (float) response.took, 0.001f);
        assertEquals("request-id", response.requestId);
    }
}
