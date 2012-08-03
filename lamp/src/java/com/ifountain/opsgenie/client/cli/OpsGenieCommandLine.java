package com.ifountain.opsgenie.client.cli;

import com.ifountain.opsgenie.client.cli.commands.*;
import com.beust.jcommander.JCommander;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.script.ScriptManager;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
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
    private Map<String, Command> commands = new HashMap<String, Command>();
    private HelpCommand helpCommand;

    public static void main(String[] args) {
        OpsGenieCommandLine commandLine = new OpsGenieCommandLine();
        boolean result = commandLine.run(args);
        if (!result) System.exit(1);
    }

    public boolean run(String... args) {
        loadLogConfiguration();
        Properties config = loadConfiguration();
        try {
            configureScriptingManager(config);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        IOpsGenieClient opsGenieClient = configureClient(config);
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
            if (e instanceof IOException) {
                System.out.println(e.getClass().getSimpleName() + "[" + e.getMessage() + "]");
            } else {
                System.out.println(e.getMessage());
            }
            return false;
        }
        return true;
    }

    private void configureScriptingManager(Properties configProps) throws Exception {
        ScriptManager.getInstance().initialize(getBaseDirectory()+"/scripts");
        String engineNamesStr = configProps.getProperty("script.engines", "").trim();
        if(engineNamesStr.length() != 0){
            String[] engineNames = engineNamesStr.split(",");
            for(String engineName:engineNames){
                String classPropName = "script.engine." + engineName + ".class";
                String className = configProps.getProperty(classPropName);
                if(className == null){
                    throw new Exception("Script engine should be configured properly. Missing ["+classPropName+"]");
                }
                String extensionsPropName = "script.engine." + engineName + ".extensions";
                String extensionsStr = configProps.getProperty(extensionsPropName);
                if(extensionsStr == null){
                    throw new Exception("Script engine should be configured properly. Missing ["+extensionsPropName+"]");
                }
                String[]extensions = extensionsStr.split(",");
                ScriptManager.getInstance().registerScriptingLanguage(engineName, className, extensions);
                
            }
        }
    }

    private IOpsGenieClient configureClient(Properties config) {
        ClientConfiguration clientConfig = new ClientConfiguration();
        if (config.containsKey("proxyHost")) {
            clientConfig.setProxyHost(config.getProperty("proxyHost"));
        }
        if (config.containsKey("proxyPort")) {
            clientConfig.setProxyPort(Integer.parseInt(config.getProperty("proxyPort")));
        }
        if (config.containsKey("proxyUsername")) {
            clientConfig.setProxyUsername(config.getProperty("proxyUsername"));
        }
        if (config.containsKey("proxyPassword")) {
            clientConfig.setProxyPassword(config.getProperty("proxyPassword"));
        }
        if (config.containsKey("proxyDomain")) {
            clientConfig.setProxyDomain(config.getProperty("proxyDomain"));
        }
        if (config.containsKey("proxyWorkstation")) {
            clientConfig.setProxyWorkstation(config.getProperty("proxyWorkstation"));
        }
        IOpsGenieClient opsGenieClient = createOpsGenieClient(clientConfig);
        if (config.containsKey("opsgenie.api.uri")) {
            opsGenieClient.setRootUri(config.getProperty("opsgenie.api.uri"));
        }
        return opsGenieClient;
    }

    private void loadLogConfiguration() {
        File logConfFile = new File(getBaseDirectory(), "conf/log.properties");
        if(logConfFile.exists()){
            PropertyConfigurator.configure(logConfFile.getPath());
        }
        else{
            org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
            org.apache.log4j.Logger.getLogger("org.apache.http").setLevel(org.apache.log4j.Level.OFF);
            org.apache.log4j.Logger.getLogger("script").setLevel(org.apache.log4j.Level.OFF);
            Logger.getLogger("org.apache.http").setLevel(Level.OFF);
        }
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

        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(commander);
        commands.put(executeScriptCommand.getName(), executeScriptCommand);
        commander.addCommand(executeScriptCommand.getName(), executeScriptCommand);

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
        return System.getProperty("lamphome");
    }

    protected IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
        return new OpsGenieClient(clientConfiguration);
    }
}
