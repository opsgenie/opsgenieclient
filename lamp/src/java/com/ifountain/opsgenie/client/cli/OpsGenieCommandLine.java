package com.ifountain.opsgenie.client.cli;

import com.beust.jcommander.JCommander;
import com.ifountain.client.ClientException;
import com.ifountain.client.model.IClient;
import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.cli.commands.Command;
import com.ifountain.opsgenie.client.cli.commands.HelpCommand;
import com.ifountain.opsgenie.client.cli.commands.VersionCommand;
import com.ifountain.opsgenie.client.cli.commands.opsgenie.*;
import com.ifountain.opsgenie.client.cli.commands.statussiren.*;
import com.ifountain.opsgenie.client.cli.commands.util.CommandUtils;
import com.ifountain.opsgenie.client.script.ScriptManager;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 5/28/12
 * Time: 10:18 AM
 */
public class OpsGenieCommandLine {
    public static final String TOOL_NAME = "lamp";
    public static final String LAMP_CONF_DIR_SYSTEM_PROPERTY = "lamp.conf.dir";
    public static final String LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY = "lamp.scripts.dir";
    private Map<String, Command> opsGenieCommands = new HashMap<String, Command>();
    private Map<String, Command> statusSirenCommands = new HashMap<String, Command>();
    private HelpCommand helpCommand;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OpsGenieCommandLine.class);
    private IClient client;

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
        ClientConfiguration clientConfiguration = CommandUtils.configureClient();
        try {
            JCommander commander = new JCommander();
            commander.setProgramName(TOOL_NAME);
            addCommands(commander);
            try {
                commander.parse(args);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                String parsedCommand = commander.getParsedCommand();
                if (parsedCommand != null) {
                    opsGenieCommands.get(parsedCommand).printUsage();
                } else {
                    helpCommand.printUsage();
                }
                return false;
            }
            Command command = opsGenieCommands.get(commander.getParsedCommand());
            if(command != null){
                client = createOpsGenieClient(clientConfiguration);
            }
            else{
                command = statusSirenCommands.get(commander.getParsedCommand());
                if(command != null){
                    client = createStatusSirenClient(clientConfiguration);
                }
            }
            if (command == null) {
                logger.warn("No command has been specified.");
                System.out.println("No command has been specified.");
                helpCommand.printUsage();
                return false;
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("apiKey")) {
                command.setApiKey((String) LampConfig.getInstance().getConfiguration().get("apiKey"));
            }
            //TODO:Will be removed
            else if (LampConfig.getInstance().getConfiguration().containsKey("customerKey")) {
                command.setApiKey((String) LampConfig.getInstance().getConfiguration().get("customerKey"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("user")) {
                command.setUser((String) LampConfig.getInstance().getConfiguration().get("user"));
            }
            try {
                command.execute(client);
            } catch (Exception e) {
                logException(e);
                if (e instanceof IOException) {
                    System.out.println(e.getClass().getSimpleName() + "[" + e.getMessage() + "]");
                } else {
                    System.out.println(e.getMessage());
                }
                return false;
            }
            return true;
        } finally {
            if(client != null){
                client.close();
            }
            LampConfig.destroyInstance();
        }
    }

    private void logException(Exception e){
        String message = e.getMessage();
        if(e instanceof ClientException){
            ClientException clientException = (ClientException) e;
            message = "Code:["+ clientException.getCode()+"] Error:["+ clientException.getMessage()+"]";
        }
        if(!message.contains("RestException")){
            logger.warn(message, e);
        }
        else{
            logger.warn(message);
        }
    }

    private void configureScriptingManager() throws Exception {
        ScriptManager.getInstance().initialize(getScriptsDirectory());
        String engineNamesStr = LampConfig.getInstance().getConfiguration().getProperty("script.engines", "").trim();
        if (engineNamesStr.length() != 0) {
            String[] engineNames = engineNamesStr.split(",");
            for (String engineName : engineNames) {
                String classPropName = "script.engine." + engineName + ".class";
                String className = LampConfig.getInstance().getConfiguration().getProperty(classPropName);
                if (className == null) {
                    throw new Exception("Script engine should be configured properly. Missing [" + classPropName + "]");
                }
                String extensionsPropName = "script.engine." + engineName + ".extensions";
                String extensionsStr = LampConfig.getInstance().getConfiguration().getProperty(extensionsPropName);
                if (extensionsStr == null) {
                    throw new Exception("Script engine should be configured properly. Missing [" + extensionsPropName + "]");
                }
                String[] extensions = extensionsStr.split(",");
                ScriptManager.getInstance().registerScriptingLanguage(engineName, className, extensions);

            }
        }
    }

    private void loadLogConfiguration() {
        File logConfFile = new File(new File(getConfigDirectory()), "log.properties");
        if (logConfFile.exists()) {
            PropertyConfigurator.configure(logConfFile.getPath());
        } else {
            org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
            org.apache.log4j.Logger.getLogger("org.apache.http").setLevel(org.apache.log4j.Level.OFF);
            org.apache.log4j.Logger.getLogger("script").setLevel(org.apache.log4j.Level.OFF);
            Logger.getLogger("org.apache.http").setLevel(Level.OFF);
        }
    }

    private void loadConfiguration() {
        File confDir = new File(getConfigDirectory());
        LampConfig.getInstance().init(new File(confDir, "lamp.conf").getPath());
    }

    private void addCommands(JCommander commander) {
        helpCommand = new HelpCommand(commander);
        addOpsGenieCommand(commander, helpCommand);
        addOpsGenieCommand(commander, new CreateAlertCommand(commander));
        addOpsGenieCommand(commander, new ExecuteScriptCommand(commander));
        addOpsGenieCommand(commander, new CloseAlertCommand(commander));
        addOpsGenieCommand(commander, new DeleteAlertCommand(commander));
        addOpsGenieCommand(commander, new AcknowledgeCommand(commander));
        addOpsGenieCommand(commander, new TakeOwnershipCommand(commander));
        addOpsGenieCommand(commander, new AssignCommand(commander));
        addOpsGenieCommand(commander, new AddRecipientCommand(commander));
        addOpsGenieCommand(commander, new AddNoteCommand(commander));
        addOpsGenieCommand(commander, new RenotifyCommand(commander));
        addOpsGenieCommand(commander, new ExecuteAlertActionCommand(commander));
        addOpsGenieCommand(commander, new AttachCommand(commander));
        addOpsGenieCommand(commander, new HeartbeatCommand(commander));
        addOpsGenieCommand(commander, new VersionCommand());
        addOpsGenieCommand(commander, new GetAlertCommand(commander));
        addOpsGenieCommand(commander, new EnableCommand(commander));
        addOpsGenieCommand(commander, new DisableCommand(commander));

        addStatusSirenCommand(commander, new CreateIncidentCommand(commander));
        addStatusSirenCommand(commander, new ResolveIncidentCommand(commander));
        addStatusSirenCommand(commander, new UpdateIncidentCommand(commander));
        addStatusSirenCommand(commander, new DeleteIncidentCommand(commander));
        addStatusSirenCommand(commander, new GetIncidentCommand(commander));
        addStatusSirenCommand(commander, new ListIncidentsCommand(commander));
    }

    private void addOpsGenieCommand(JCommander commander, Command command) {
        opsGenieCommands.put(command.getName(), command);
        commander.addCommand(command.getName(), command);
    }

    private void addStatusSirenCommand(JCommander commander, Command command) {
        statusSirenCommands.put(command.getName(), command);
        commander.addCommand(command.getName(), command);
    }

    public String getConfigDirectory() {
        return System.getProperty(LAMP_CONF_DIR_SYSTEM_PROPERTY);
    }

    public String getScriptsDirectory() {
        return System.getProperty(LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY);
    }

    protected IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
        return CommandUtils.createOpsGenieClient(clientConfiguration);
    }

    protected IStatusSirenClient createStatusSirenClient(ClientConfiguration clientConfiguration){
        return CommandUtils.createStatusSirenClient(clientConfiguration);
    }
}
