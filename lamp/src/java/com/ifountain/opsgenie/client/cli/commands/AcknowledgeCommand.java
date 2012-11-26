package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.AcknowledgeRequest;
import com.ifountain.opsgenie.client.model.AssignRequest;
import com.ifountain.opsgenie.client.model.CloseAlertRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 11/26/12 4:57 PM
 */
@Parameters(commandDescription = "Assigns the ownership of an alert to the specified user.")
public class AcknowledgeCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that will be acknowledged. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that will be acknowledged. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.OWNER, description = "The user who will be the owner of the alert after the execution.", variableArity = true, splitter = NullSplitter.class)
    private List<String> owner;

    public AcknowledgeCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "assign";
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        AssignRequest request = new AssignRequest();
        request.setCustomerKey(commonOptions.getCustomerKey());
        request.setAlertId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (owner != null) request.setOwner(Strings.join(owner, " "));
        if (commonOptions.getUser() != null) request.setUser(commonOptions.getUser());
        opsGenieClient.assign(request);
    }
}
