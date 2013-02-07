package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.AddRecipientRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 1/9/13 4:18 PM
 */
@Parameters(commandDescription = "Adds a new recipient to an alert.")
public class AddRecipientCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that the new recipient will be added. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that the new recipient will be added. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.RECIPIENT, description = "The recipient that will be added to the alert.", variableArity = true, splitter = NullSplitter.class)
    private List<String> recipient;

    @Parameter(names = "--" + OpsGenieClientConstants.API.NOTE, description = "User note.", variableArity = true, splitter = NullSplitter.class)
    private List<String> note;

    public AddRecipientCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "addRecipient";
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        AddRecipientRequest request = new AddRecipientRequest();
        request.setCustomerKey(commonOptions.getCustomerKey());
        request.setAlertId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (note != null) request.setNote(Strings.join(note, " "));
        if (recipient != null) request.setRecipient(Strings.join(recipient, " "));
        if (commonOptions.getUser() != null) request.setUser(commonOptions.getUser());
        opsGenieClient.addRecipient(request);
    }
}
