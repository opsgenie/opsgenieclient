package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionRequest;
import com.ifountain.opsgenie.client.model.alert.ExecuteAlertActionResponse;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 10/30/12 5:24 PM
 */

@Parameters(commandDescription = "Executes alert actions at OpsGenie.")
public class ExecuteAlertActionCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that the action will be executed on. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that the action will be executed on. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ACTION, description = "Action to execute.", variableArity = true, splitter = NullSplitter.class)
    private List<String> action;

    @Parameter(names = "--" + OpsGenieClientConstants.API.NOTE, description = "User note.", variableArity = true, splitter = NullSplitter.class)
    private List<String> note;

    @Parameter(names = "--" + OpsGenieClientConstants.API.SOURCE, description = "Source of action.", variableArity = true, splitter = NullSplitter.class)
    private List<String> source;

    public ExecuteAlertActionCommand(JCommander commander) {
        super(commander);
    }

    @Override
    protected void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        ExecuteAlertActionRequest request = new ExecuteAlertActionRequest();
        request.setApiKey(commonOptions.getApiKey());
        request.setId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (action != null) request.setAction(Strings.join(action, " "));
        if (note != null) request.setNote(Strings.join(note, " "));
        if (commonOptions.getUser() != null) request.setUser(commonOptions.getUser());
        if (source != null) request.setSource(Strings.join(source, " "));
        ExecuteAlertActionResponse response = opsGenieClient.alert().executeAlertAction(request);
        System.out.println("result=" + response.getResult());
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public String getName() {
        return "executeAction";
    }
}
