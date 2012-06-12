package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.ifountain.opsgenie.client.IOpsGenieClient;

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
        if (isHelp()) {
            printUsage();
        } else {
            doExecute(opsGenieClient);
        }
    }

    protected abstract boolean isHelp();

    protected abstract void doExecute(IOpsGenieClient opsGenieClient) throws Exception;
}
