package com.ifountain.opsgenie.client.cli.commands.statussiren;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.incident.ResolveIncidentRequest;
import com.ifountain.client.statussiren.model.incident.ResolveIncidentResponse;
import com.ifountain.client.util.Strings;
import com.ifountain.opsgenie.client.cli.commands.BaseCommand;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.NullSplitter;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 13.5.2014 09:34
 */
@Parameters(commandDescription = "Resolves an incident at StatusSiren.")
public class ResolveIncidentCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + ClientConstants.API.SERVICE, description = "Service that incident belongs to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> service;

    @Parameter(names = "--" + ClientConstants.API.INCIDENT_ID, description = "Id of the incident that will be deleted.", variableArity = true, splitter = NullSplitter.class)
    private long incidentId;

    @Parameter(names = "--" + ClientConstants.API.MESSAGE, description = "Resolve message for incident.", variableArity = true, splitter = NullSplitter.class)
    private List<String> message;

    public ResolveIncidentCommand(JCommander commander){
        super(commander);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions ;
    }

    @Override
    public String getName() {
        return "resolveIncident";
    }

    @Override
    protected void doExecute(IClient client) throws Exception {
        ResolveIncidentRequest request = new ResolveIncidentRequest();
        request.setApiKey(commonOptions.getApiKey());
        request.setIncidentId(incidentId);
        if(service != null) request.setService(Strings.join(service, " "));
        if (message != null) request.setMessage(Strings.join(message, " "));
        ((IStatusSirenClient) client).resolveIncident(request);
    }
}
