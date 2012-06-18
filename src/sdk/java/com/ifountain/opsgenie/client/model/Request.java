package com.ifountain.opsgenie.client.model;

/**
 * Interface for OpsGenie service api requests.
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/30/12 9:40 AM
 * @see com.ifountain.opsgenie.client.IOpsGenieClient
 * @see com.ifountain.opsgenie.client.OpsGenieClient
 */
public interface Request {
    /**
     * Returns the request's rest api uri.
     */
    public String getEndPoint();
}
