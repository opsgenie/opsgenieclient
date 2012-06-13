package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.AttachRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.io.File;
import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:33 AM
 */
@Parameters(commandDescription = "Attaches files to an alert.")
public class AttachCommand extends BaseCommand{
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALERT_ID, description = "Id of the alert that the file will be attached. Either this or alias should be given.", splitter = NullSplitter.class)
    private String alertId;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ALIAS, description = "Alias of the alert that the file will be attached. Either this or alertId should be given.", variableArity = true, splitter = NullSplitter.class)
    private List<String> alias;

    @Parameter(names = "--" + OpsGenieClientConstants.API.ATTACHMENT, description = "Path to the file.", variableArity = true, splitter = NullSplitter.class)
    private List<String> attachment;

    @Parameter(names = "--" + OpsGenieClientConstants.API.INDEX_FILE, description = "", variableArity = true, splitter = NullSplitter.class, hidden = true)
    private List<String> indexFile;

    public AttachCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "attachFile";
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        AttachRequest request = new AttachRequest();
        request.setCustomerKey(commonOptions.getCustomerKey());
        request.setAlertId(alertId);
        if (alias != null) request.setAlias(Strings.join(alias, " "));
        if (indexFile != null) request.setIndexFile(Strings.join(indexFile, " "));
        if (commonOptions.getUser() != null) request.setUser(Strings.join(commonOptions.getUser(), " "));
        if (attachment != null) request.setFile(new File(Strings.join(attachment, " ")));
        opsGenieClient.attach(request);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }
}
