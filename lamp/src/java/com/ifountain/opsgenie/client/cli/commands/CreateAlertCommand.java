package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.*;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.alert.CreateAlertRequest;
import com.ifountain.opsgenie.client.model.alert.CreateAlertResponse;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 3:30 PM
 */
@Parameters(commandDescription = "Creates an alert at OpsGenie.")
public class CreateAlertCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.MESSAGE, description = "Alert text limited to 130 characters", variableArity = true, splitter = NullSplitter.class)
    private List<String> message;

    @Parameter(names = "--" + OpsGenieClientConstants.API.SOURCE, description = "Field to specify source of alert. By default, it will be assigned to IP address of incoming request.", variableArity = true, splitter = NullSplitter.class)
    private List<String> source;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ENTITY, description = "The entity the alert is related to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> entity;

    @Parameter(names = "--" + OpsGenieClientConstants.API.DESCRIPTION, description = "Alert text in long form.", variableArity = true, splitter = NullSplitter.class)
    private List<String> description;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "A user defined identifier for the alert.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.TAGS, description = "A comma separated list of labels attached to the alert.", variableArity = true, splitter = NullSplitter.class)
    private List<String> tags;

    @Parameter(names = "--" + OpsGenieClientConstants.API.RECIPIENTS, description = "The user names of individual users or names of groups that the alert is assigned.", variableArity = true, splitter = NullSplitter.class)
    private List<String> recipients;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ACTIONS, description = "A comma separated list of actions that can be executed.", variableArity = true, splitter = NullSplitter.class)
    private List<String> actions;

    @Parameter(names = "--" + OpsGenieClientConstants.API.NOTE, description = "User note.", variableArity = true, splitter = NullSplitter.class)
    private List<String> note;

    @DynamicParameter(names = "-D", description = "Additional alert properties.")
    private Map<String, String> details = new HashMap<String, String>();

    public CreateAlertCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "createAlert";
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        CreateAlertRequest request = new CreateAlertRequest();
        request.setApiKey(commonOptions.getApiKey());
        if (message != null) request.setMessage(Strings.join(message, " "));
        if (description != null) request.setDescription(Strings.join(description, " "));
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (source != null) request.setSource(Strings.join(source, " "));
        if (entity != null) request.setEntity(Strings.join(entity, " "));
        if (note != null) request.setNote(Strings.join(note, " "));
        if (commonOptions.getUser() != null) request.setUser(commonOptions.getUser());
        if (tags != null) request.setTags(Arrays.asList(Strings.join(tags, " ").split(",")));
        if (actions != null) request.setActions(Arrays.asList(Strings.join(actions, " ").split(",")));
        if (recipients != null) request.setRecipients(Arrays.asList(Strings.join(recipients, " ").split(",")));
        request.setDetails(details);
        CreateAlertResponse response = opsGenieClient.alert().createAlert(request);
        System.out.println("alertId=" + response.getAlertId());
    }
}
