package com.ifountain.opsgenie.client.cli.commands.statussiren;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.incident.DeleteIncidentRequest;
import com.ifountain.client.util.Strings;
import com.ifountain.opsgenie.client.cli.commands.BaseCommand;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.NullSplitter;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 13.5.2014 09:54
 */
@Parameters(commandDescription = "Deletes an incident at StatusSiren.")
public class DeleteIncidentCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + ClientConstants.API.SERVICE, description = "Service that incident belongs to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> service;

    @Parameter(names = "--" + ClientConstants.API.INCIDENT_ID, description = "Id of the incident that will be deleted.", variableArity = true, splitter = NullSplitter.class)
    private long incidentId;

    public DeleteIncidentCommand(JCommander commander){
        super(commander);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions ;
    }

    @Override
    public String getName() {
        return "deleteIncident";
    }

    @Override
    protected void doExecute(IClient client) throws Exception {
        DeleteIncidentRequest request = new DeleteIncidentRequest();
        request.setApiKey(commonOptions.getApiKey());
        request.setIncidentId(incidentId);
        if(service != null) request.setService(Strings.join(service, " "));
        ((IStatusSirenClient) client).deleteIncident(request);
    }
}
