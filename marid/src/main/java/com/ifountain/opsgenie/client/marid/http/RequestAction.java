package com.ifountain.opsgenie.client.marid.http;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 1/16/12
 * Time: 11:50 AM
 */
public interface RequestAction {
    public HTTPResponse execute(HTTPRequest request);
    public void register() throws Exception;
}
