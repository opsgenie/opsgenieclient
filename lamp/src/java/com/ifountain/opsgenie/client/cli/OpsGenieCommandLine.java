package com.ifountain.opsgenie.client.cli;

import com.ifountain.client.ClientException;
import com.ifountain.opsgenie.client.cli.commands.*;
import com.beust.jcommander.JCommander;
import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.client.opsgenie.OpsGenieClient;
import com.ifountain.client.util.ClientConfiguration;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.client.util.ClientProxyConfiguration;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
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
    public static final String LAMP_CONF_DIR_SYSTEM_PROPERTY = "lamp.conf.dir";
    public static final String LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY = "lamp.scripts.dir";
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
                    commands.get(parsedCommand).printUsage();
                } else {
                    helpCommand.printUsage();
                }
                return false;
            }
            Command command = commands.get(commander.getParsedCommand());
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
                command.execute(opsGenieClient);
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
            opsGenieClient.close();
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

    private IOpsGenieClient configureClient() {
        ClientConfiguration clientConfig = new ClientConfiguration();

        if (LampConfig.getInstance().getConfiguration().containsKey("proxyHost") && LampConfig.getInstance().getConfiguration().containsKey("proxyPort")) {
            String host = LampConfig.getInstance().getConfiguration().getProperty("proxyHost");
            int port = Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("proxyPort"));
            ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(host, port);
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyUsername")) {
                clientProxyConfiguration.setProxyUsername(LampConfig.getInstance().getConfiguration().getProperty("proxyUsername"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyPassword")) {
                clientProxyConfiguration.setProxyPassword(LampConfig.getInstance().getConfiguration().getProperty("proxyPassword"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyDomain")) {
                clientProxyConfiguration.setProxyDomain(LampConfig.getInstance().getConfiguration().getProperty("proxyDomain"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("authMethod")) {
                String authMethod = LampConfig.getInstance().getConfiguration().getProperty("authMethod", ClientProxyConfiguration.AuthType.NT.name());
                ClientProxyConfiguration.AuthType authTypeEnum;
                try{
                    authTypeEnum = ClientProxyConfiguration.AuthType.valueOf(authMethod);
                }catch (Throwable t){
                    throw new RuntimeException("Invalid authMethod ["+authMethod+"]");
                }
                clientProxyConfiguration.setAuthType(authTypeEnum);
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyWorkstation")) {
                clientProxyConfiguration.setProxyWorkstation(LampConfig.getInstance().getConfiguration().getProperty("proxyWorkstation"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyProtocol")) {
                clientProxyConfiguration.setProxyProtocol(LampConfig.getInstance().getConfiguration().getProperty("proxyProtocol"));
            }
            clientConfig.setClientProxyConfiguration(clientProxyConfiguration);
        }
        clientConfig.setSocketTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketTimeout", "30")) * 1000);
        clientConfig.setConnectionTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("connectionTimeout", "30")) * 1000);
        clientConfig.setMaxConnections(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("maxConnectionCount", "50")));
        if (LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint") != null) {
            clientConfig.setSocketReceiveBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint")));
        }
        if (LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint") != null) {
            clientConfig.setSocketSendBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint")));
        }
        clientConfig.setUserAgent(ClientConfiguration.createUserAgentFromManifest(OpsGenieCommandLine.class));
        IOpsGenieClient opsGenieClient = createOpsGenieClient(clientConfig);
        if (LampConfig.getInstance().getConfiguration().containsKey("opsgenie.api.url")) {
            opsGenieClient.setRootUri(LampConfig.getInstance().getConfiguration().getProperty("opsgenie.api.url"));
        }
        return opsGenieClient;
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
        addCommand(commander, helpCommand);
        addCommand(commander, new CreateAlertCommand(commander));
        addCommand(commander, new ExecuteScriptCommand(commander));
        addCommand(commander, new CloseAlertCommand(commander));
        addCommand(commander, new DeleteAlertCommand(commander));
        addCommand(commander, new AcknowledgeCommand(commander));
        addCommand(commander, new TakeOwnershipCommand(commander));
        addCommand(commander, new AssignCommand(commander));
        addCommand(commander, new AddRecipientCommand(commander));
        addCommand(commander, new AddNoteCommand(commander));
        addCommand(commander, new RenotifyCommand(commander));
        addCommand(commander, new ExecuteAlertActionCommand(commander));
        addCommand(commander, new AttachCommand(commander));
        addCommand(commander, new HeartbeatCommand(commander));
        addCommand(commander, new VersionCommand());
        addCommand(commander, new GetAlertCommand(commander));
        addCommand(commander, new EnableCommand(commander));
        addCommand(commander, new DisableCommand(commander));
    }

    private void addCommand(JCommander commander, Command command) {
        commands.put(command.getName(), command);
        commander.addCommand(command.getName(), command);
    }

    public String getConfigDirectory() {
        return System.getProperty(LAMP_CONF_DIR_SYSTEM_PROPERTY);
    }

    public String getScriptsDirectory() {
        return System.getProperty(LAMP_SCRIPTS_DIR_SYSTEM_PROPERTY);
    }

    protected IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
        return new OpsGenieClient(clientConfiguration);
    }
}
