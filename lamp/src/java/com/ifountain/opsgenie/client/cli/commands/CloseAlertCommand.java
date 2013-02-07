package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.CloseAlertRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:16 AM
 */
@Parameters(commandDescription = "Closes an alert at OpsGenie.")
public class CloseAlertCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that will be closed. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that will be closed. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.NOTE, description = "User note.", variableArity = true, splitter = NullSplitter.class)
    private List<String> note;

    public CloseAlertCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "closeAlert";
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        CloseAlertRequest request = new CloseAlertRequest();
        request.setCustomerKey(commonOptions.getCustomerKey());
        request.setAlertId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (note != null) request.setNote(Strings.join(note, " "));
        if (commonOptions.getUser() != null) request.setUser(commonOptions.getUser());
        opsGenieClient.closeAlert(request);
    }
}
