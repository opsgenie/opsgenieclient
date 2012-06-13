package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.GetAlertRequest;
import com.ifountain.opsgenie.client.model.GetAlertResponse;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:56 AM
 */
@Parameters(commandDescription = "Gets an alert content from OpsGenie.")
public class GetAlertCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that will be retrieved. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that will be retrieved. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    public GetAlertCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "getAlert";
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        GetAlertRequest request = new GetAlertRequest();
        request.setCustomerKey(commonOptions.getCustomerKey());
        request.setAlertId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        GetAlertResponse response = opsGenieClient.getAlert(request);
        System.out.println(response.getJson());
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }
}
