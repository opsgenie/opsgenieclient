package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.*;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.opsgenie.client.cli.LampConfig;
import com.ifountain.opsgenie.client.cli.script.LampScriptProxy;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.script.ScriptManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Parameters(commandDescription = "Executes a script on lamp.")
public class ExecuteScriptCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();
    @Parameter(names = "--" + ClientConstants.API.NAME, description = "Name of script to be executed", splitter = NullSplitter.class, required = true)
    private String script;
    @DynamicParameter(names = "-D", description = "Script parameters.")
    private Map<String, String> details = new HashMap<String, String>();

    public ExecuteScriptCommand(JCommander commander) {
        super(commander);
    }

    @Override
    protected void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        executeScript(script, opsGenieClient, commonOptions, details);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public String getName() {
        return "executeScript";
    }

    public static void executeScript(String script, IOpsGenieClient opsgenieClient, CommonCommandOptions commonOptions, Map params) throws Exception {
        Map<String, Object> bindings = new HashMap<String, Object>();
        Properties confBindings = new Properties();
        confBindings.putAll(LampConfig.getInstance().getConfiguration());
        if(commonOptions.getApiKey() != null){
            confBindings.setProperty(ClientConstants.API.API_KEY, commonOptions.getApiKey());
        }
        if(commonOptions.getUser() != null){
            confBindings.setProperty(ClientConstants.API.USER, commonOptions.getUser());
        }
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF, confBindings);
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT, new LampScriptProxy(opsgenieClient, commonOptions.getApiKey(), commonOptions.getUser()));
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS, params);
        ScriptManager.getInstance().runScript(script, bindings);
        System.out.println("Successfully executed " + script);
    }
}
