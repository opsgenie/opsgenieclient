package com.ifountain.opsgenie.client.cli;

import com.beust.jcommander.JCommander;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.cli.commands.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/28/12
 * Time: 10:18 AM
 */
public class OpsGenieCommandLine {
    public static final String TOOL_NAME = "lamp";
    private IOpsGenieClient opsGenieClient;
    private Map<String, Command> commands = new HashMap<String, Command>();
    private HelpCommand helpCommand;

    public OpsGenieCommandLine() {
        this(new OpsGenieClient());
    }

    public OpsGenieCommandLine(IOpsGenieClient opsGenieClient) {
        this.opsGenieClient = opsGenieClient;
    }

    public static void main(String[] args) {
        OpsGenieCommandLine commandLine = new OpsGenieCommandLine();
        boolean result = commandLine.run(args);
        if (!result) System.exit(1);
    }

    public boolean run(String... args) {
        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
        Properties config = loadConfiguration();
        if (config.containsKey("opsgenie.api.uri")) {
            opsGenieClient.setRootUri(config.getProperty("opsgenie.api.uri"));
        }
        JCommander commander = new JCommander();
        commander.setProgramName(TOOL_NAME);
        addCommands(commander);
        try {
            commander.parse(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String parsedCommand = commander.getParsedCommand();
            if (parsedCommand != null) {
                commands.get(parsedCommand).printUsage();
            } else {
                helpCommand.printUsage();
            }
            return false;
        }
        Command command = commands.get(commander.getParsedCommand());
        if (command == null) {
            System.out.println("No command has been specified.");
            helpCommand.printUsage();
            return false;
        }
        if (config.containsKey("customerKey")) {
            command.setCustomerKey((String) config.get("customerKey"));
        }
        if (config.containsKey("user")) {
            command.setUser((String) config.get("user"));
        }
        try {
            command.execute(opsGenieClient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private Properties loadConfiguration() {
        Properties config = new Properties();
        File confDir = new File(getBaseDirectory(), "conf");
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(confDir, "lamp.conf"));
            config.load(in);
        } catch (Exception ignored) {
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException ignored) {
            }
        }
        return config;
    }

    private void addCommands(JCommander commander) {
        helpCommand = new HelpCommand(commander);
        commands.put(helpCommand.getName(), helpCommand);
        commander.addCommand(helpCommand.getName(), helpCommand);

        CreateAlertCommand createAlertCommand = new CreateAlertCommand(commander);
        commands.put(createAlertCommand.getName(), createAlertCommand);
        commander.addCommand(createAlertCommand.getName(), createAlertCommand);

        CloseAlertCommand closeAlertCommand = new CloseAlertCommand(commander);
        commands.put(closeAlertCommand.getName(), closeAlertCommand);
        commander.addCommand(closeAlertCommand.getName(), closeAlertCommand);

        AddNoteCommand addNoteCommand = new AddNoteCommand(commander);
        commands.put(addNoteCommand.getName(), addNoteCommand);
        commander.addCommand(addNoteCommand.getName(), addNoteCommand);

        AttachCommand attachCommand = new AttachCommand(commander);
        commands.put(attachCommand.getName(), attachCommand);
        commander.addCommand(attachCommand.getName(), attachCommand);

        HeartbeatCommand heartbeatCommand = new HeartbeatCommand(commander);
        commands.put(heartbeatCommand.getName(), heartbeatCommand);
        commander.addCommand(heartbeatCommand.getName(), heartbeatCommand);

        GetAlertCommand getAlertCommand = new GetAlertCommand(commander);
        commands.put(getAlertCommand.getName(), getAlertCommand);
        commander.addCommand(getAlertCommand.getName(), getAlertCommand);
    }

    public String getBaseDirectory() {
        return System.getProperty("ochome");
    }
}