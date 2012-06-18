package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.cli.utils.OpsGenieClientScriptingProxy;
import com.ifountain.opsgenie.client.cli.utils.ScriptManager;

import java.util.HashMap;
import java.util.Map;

@Parameters(commandDescription = "Executes a script on lamp.")
public class ExecuteScriptCommand extends BaseCommand{
    private CommonCommandOptions commonOptions = new CommonCommandOptions();
    @Parameter(names = "--" + OpsGenieClientConstants.API.NAME, description = "Name of script to be executed", splitter = NullSplitter.class, required = true)
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
        Map<String, Object> confBindings = new HashMap<String, Object>();
        confBindings.put(OpsGenieClientConstants.API.CUSTOMER_KEY, commonOptions.getCustomerKey());
        confBindings.put(OpsGenieClientConstants.API.USER, commonOptions.getUser());
        bindings.put(OpsGenieClientConstants.ScriptBindings.CONF, confBindings);
        bindings.put(OpsGenieClientConstants.ScriptBindings.OPSGENIE_CLIENT, new OpsGenieClientScriptingProxy(opsgenieClient, commonOptions));
        bindings.put(OpsGenieClientConstants.ScriptBindings.PARAMS, params);
        ScriptManager.getInstance().execute(script, bindings);
    }
}
