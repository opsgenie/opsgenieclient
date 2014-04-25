package com.ifountain.client;

/**
 * <p>This is the main type of exception that a caller need to deal with when using the OpsGenie services.
 * This exception represents an error response from an OpsGenie service. For example, if a caller requests
 * to close an alert that doesn't exist, OpsGenie will return an error response and all the details of that
 * error response will be included in the thrown ClientException.</p>
 * <p>When a caller encounters an ClientException, it means that the request was successfully sent
 * to the OpsGenie service, but could not be successfully processed either because of errors in the request's
 * parameters or because of issues on the service side.</p>
 *
 * @author Sezgin Kucukkaraaslan
 * @version 5/31/12 1:42 PM
 */
public class ClientException extends Exception {
    /**
     * Represents error code returning form OpsGenie service.
     */
    private int code;

    /**
     * Constructs an ClientException with specified error message and error code.
     */
    public ClientException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * Retrieves error code returning form OpsGenie service.
     */
    public int getCode() {
        return code;
    }
}
