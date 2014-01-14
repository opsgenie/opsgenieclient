package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.ifountain.opsgenie.client.IOpsGenieClient;

import java.util.Arrays;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/5/12
 * Time: 4:52 PM
 */
public abstract class BaseCommand implements Command {
    protected JCommander commander;

    public BaseCommand(JCommander commander) {
        this.commander = commander;
    }

    @Override
    public void printUsage() {
        commander.usage(getName());
    }

    @Override
    public void execute(IOpsGenieClient opsGenieClient) throws Exception {
        if (getCommonCommandOptions().isHelp()) {
            printUsage();
        } else {
            doExecute(opsGenieClient);
        }
    }

    @Override
    public void setApiKey(String apiKey) {
        if (getCommonCommandOptions().getApiKey() == null) {
            getCommonCommandOptions().setApiKey(apiKey);
        }
    }

    @Override
    public void setUser(String user) {
        CommonCommandOptions commonCommandOptions = getCommonCommandOptions();
        if (commonCommandOptions.getUser() == null || commonCommandOptions.getUserList().size() == 0) {
            commonCommandOptions.setUser(Arrays.asList(user));
        }
    }

    protected abstract void doExecute(IOpsGenieClient opsGenieClient) throws Exception;

    protected abstract CommonCommandOptions getCommonCommandOptions();

}
