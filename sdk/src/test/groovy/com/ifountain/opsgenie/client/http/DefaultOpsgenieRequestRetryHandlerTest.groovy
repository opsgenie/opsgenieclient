package com.ifountain.opsgenie.client.http


import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.protocol.BasicHttpContext
import org.apache.http.protocol.ExecutionContext
import org.junit.Test
import static org.junit.Assert.*
/**
 * Created by ifountain-qj on 4/4/14.
 */
class DefaultOpsgenieRequestRetryHandlerTest {
    @Test
    public void testRetryWithResponse() {
        DefaultOpsgenieRequestRetryHandler opsgenieRequestRetryHandler = new DefaultOpsgenieRequestRetryHandler(10)
        opsgenieRequestRetryHandler.setWaitTime(0)

        OpsGenieHttpResponse response = new OpsGenieHttpResponse(statusCode: HttpStatus.SC_INTERNAL_SERVER_ERROR)
        (opsgenieRequestRetryHandler.getMaxRetryCount() + 1).times {
            assertTrue(opsgenieRequestRetryHandler.retryRequest(new HttpDelete("xyz.com"), response, it))
        }
        assertFalse(opsgenieRequestRetryHandler.retryRequest(new HttpDelete("xyz.com"), response, 11))
        100.times {
            response = new OpsGenieHttpResponse(statusCode: 500 + it)
            assertTrue(opsgenieRequestRetryHandler.retryRequest(new HttpDelete("xyz.com"), response, 0))
        }

        500.times {
            if (it != HttpStatus.SC_REQUEST_TIMEOUT) {
                response = new OpsGenieHttpResponse(statusCode: it)
                assertFalse(opsgenieRequestRetryHandler.retryRequest(new HttpDelete("xyz.com"), response, 0))
            }
        }

        response = new OpsGenieHttpResponse(statusCode: HttpStatus.SC_REQUEST_TIMEOUT)
        assertTrue(opsgenieRequestRetryHandler.retryRequest(new HttpDelete("xyz.com"), response, 0))
    }

    @Test
    public void testRetryWithIOException() {
        DefaultOpsgenieRequestRetryHandler opsgenieRequestRetryHandler = new DefaultOpsgenieRequestRetryHandler(10)
        opsgenieRequestRetryHandler.setWaitTime(0)
        def context = new BasicHttpContext()
        HttpUriRequest uriRequest = new HttpDelete("")
        context.setAttribute(ExecutionContext.HTTP_REQUEST, uriRequest)
        (opsgenieRequestRetryHandler.getMaxRetryCount() + 1).times {
            assertTrue(opsgenieRequestRetryHandler.retryRequest(new IOException("ex"), it, context))
        }
        assertFalse(opsgenieRequestRetryHandler.retryRequest(new IOException("ex"), 11, context))


        context = new BasicHttpContext()
        uriRequest = new HttpDelete("")
        uriRequest.abort()
        context.setAttribute(ExecutionContext.HTTP_REQUEST, uriRequest)
        assertFalse(opsgenieRequestRetryHandler.retryRequest(new IOException("ex"), 0, context))
    }

    @Test
    public void testExponentialBackoff() {
        int maxRetryCount = 5;
        DefaultOpsgenieRequestRetryHandler opsgenieRequestRetryHandler = new DefaultOpsgenieRequestRetryHandler(maxRetryCount)
        opsgenieRequestRetryHandler.setWaitTime(200)
        def context = new BasicHttpContext()
        HttpUriRequest uriRequest = new HttpDelete("")
        context.setAttribute(ExecutionContext.HTTP_REQUEST, uriRequest)
        long time = System.currentTimeMillis()
        (maxRetryCount + 1).times {
            assertTrue(opsgenieRequestRetryHandler.retryRequest(new IOException("ex"), it, context))
        }

        long totalTime = System.currentTimeMillis() - time;


        def expectedWaitTime = (maxRetryCount * (maxRetryCount + 1)) * opsgenieRequestRetryHandler.getWaitTime() / 2 - 100
        println "expectedWaitTime:${expectedWaitTime} total:${totalTime}"
        assertTrue(totalTime > expectedWaitTime)
    }
}