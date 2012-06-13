package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.cli.OpsGenieCommandLine;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 11:38 AM
 */
@Parameters(commandDescription = "Displays usage.")
public class HelpCommand implements Command {
    private JCommander jCommander;

    public HelpCommand(JCommander jCommander) {
        this.jCommander = jCommander;
    }

    @Override
    public String getName() {
        return "--" + OpsGenieClientConstants.API.HELP;
    }

    @Override
    public void printUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usage: ").append(OpsGenieCommandLine.TOOL_NAME).append(" [command] [options]\n\n");
        sb.append(OpsGenieCommandLine.TOOL_NAME).append(" commands are:\n");
        Map<String, JCommander> commands = jCommander.getCommands();
        for (String command : commands.keySet()) {
            if(!command.equals(getName())){
                spaces(sb, 2);
                sb.append(command).append(":");
                spaces(sb, 15 - command.length());
                sb.append(jCommander.getCommandDescription(command)).append("\n");
            }
        }
        sb.append("\nUse '").append(OpsGenieCommandLine.TOOL_NAME).append(" [command] --help' for more information on a specific command.");
        System.out.println(sb.toString());
    }

    private void spaces(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) sb.append(" ");
    }

    @Override
    public void execute(IOpsGenieClient opsGenieClient) throws Exception {
        printUsage();
    }

    @Override
    public void setCustomerKey(String customerKey) {
    }

    @Override
    public void setUser(String user) {
    }
}
