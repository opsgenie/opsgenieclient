package com.ifountain.client.model;

/**
 * @author Tuba Ozturk
 * @version 12.5.2014 16:06
 */
public interface IClient {
    /**
     * Set root endpoint uri that the client uses to send http requests.
     * @param rootUri Uri to set.
     */
    public void setRootUri(String rootUri);

    /**
     * Closes client
     */
    public void close();
}
