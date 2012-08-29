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
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OpsGenieCommandLine.class);

    public static void main(String[] args) {
        OpsGenieCommandLine commandLine = new OpsGenieCommandLine();
        boolean result = commandLine.run(args);
        if (!result) System.exit(1);
    }

    public boolean run(String... args) {
        loadLogConfiguration();
        loadConfiguration();
        try {
            configureScriptingManager();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        IOpsGenieClient opsGenieClient = configureClient();
        try{
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
            if (LampConfig.getInstance().getConfiguration().containsKey("customerKey")) {
                command.setCustomerKey((String) LampConfig.getInstance().getConfiguration().get("customerKey"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("user")) {
                command.setUser((String) LampConfig.getInstance().getConfiguration().get("user"));
            }
            try {
                command.execute(opsGenieClient);
            } catch (Exception e) {
                logger.warn("Exception occurred while executing command ["+command.getClass().getSimpleName()+"]", e);
                if (e instanceof IOException) {
                    System.out.println(e.getClass().getSimpleName() + "[" + e.getMessage() + "]");
                } else {
                    System.out.println(e.getMessage());
                }
                return false;
            }
            return true;
        }finally {
            opsGenieClient.close();
            LampConfig.destroyInstance();
        }
    }

    private void configureScriptingManager() throws Exception {
        ScriptManager.getInstance().initialize(getBaseDirectory()+"/scripts");
        String engineNamesStr = LampConfig.getInstance().getConfiguration().getProperty("script.engines", "").trim();
        if(engineNamesStr.length() != 0){
            String[] engineNames = engineNamesStr.split(",");
            for(String engineName:engineNames){
                String classPropName = "script.engine." + engineName + ".class";
                String className = LampConfig.getInstance().getConfiguration().getProperty(classPropName);
                if(className == null){
                    throw new Exception("Script engine should be configured properly. Missing ["+classPropName+"]");
                }
                String extensionsPropName = "script.engine." + engineName + ".extensions";
                String extensionsStr = LampConfig.getInstance().getConfiguration().getProperty(extensionsPropName);
                if(extensionsStr == null){
                    throw new Exception("Script engine should be configured properly. Missing ["+extensionsPropName+"]");
                }
                String[]extensions = extensionsStr.split(",");
                ScriptManager.getInstance().registerScriptingLanguage(engineName, className, extensions);
                
            }
        }
    }

    private IOpsGenieClient configureClient() {
        ClientConfiguration clientConfig = new ClientConfiguration();
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyHost")) {
            clientConfig.setProxyHost(LampConfig.getInstance().getConfiguration().getProperty("proxyHost"));
        }
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyPort")) {
            clientConfig.setProxyPort(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("proxyPort")));
        }
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyUsername")) {
            clientConfig.setProxyUsername(LampConfig.getInstance().getConfiguration().getProperty("proxyUsername"));
        }
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyPassword")) {
            clientConfig.setProxyPassword(LampConfig.getInstance().getConfiguration().getProperty("proxyPassword"));
        }
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyDomain")) {
            clientConfig.setProxyDomain(LampConfig.getInstance().getConfiguration().getProperty("proxyDomain"));
        }
        if (LampConfig.getInstance().getConfiguration().containsKey("proxyWorkstation")) {
            clientConfig.setProxyWorkstation(LampConfig.getInstance().getConfiguration().getProperty("proxyWorkstation"));
        }
        clientConfig.setSocketTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketTimeout", "30"))*1000);
        clientConfig.setConnectionTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("connectionTimeout", "30"))*1000);
        clientConfig.setMaxConnections(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("maxConnectionCount", "50")));
        if(LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint") != null){
            clientConfig.setSocketReceiveBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint")));
        }
        if(LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint") != null){
            clientConfig.setSocketSendBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint")));
        }

        IOpsGenieClient opsGenieClient = createOpsGenieClient(clientConfig);
        if (LampConfig.getInstance().getConfiguration().containsKey("opsgenie.api.url")) {
            opsGenieClient.setRootUri(LampConfig.getInstance().getConfiguration().getProperty("opsgenie.api.url"));
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

    private void loadConfiguration() {
        File confDir = new File(getBaseDirectory(), "conf");
        LampConfig.getInstance().init(new File(confDir, "lamp.conf").getPath());
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
