package com.ifountain.opsgenie.client.cli.commands;

import com.ifountain.opsgenie.client.IOpsGenieClient;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 3:28 PM
 */
public interface Command {
    public String getName();

    public void printUsage();

    public void execute(IOpsGenieClient opsGenieClient) throws Exception;

    public void setApiKey(String apiKey);

    public void setUser(String user);
}
