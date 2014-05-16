package com.ifountain.opsgenie.client.cli.commands.statussiren;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.Status;
import com.ifountain.client.statussiren.model.incident.CreateIncidentRequest;
import com.ifountain.client.statussiren.model.incident.CreateIncidentResponse;
import com.ifountain.client.util.Strings;
import com.ifountain.opsgenie.client.cli.commands.BaseCommand;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.NullSplitter;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 12.5.2014 17:58
 */
@Parameters(commandDescription = "Creates an incident at StatusSiren.")
public class CreateIncidentCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + ClientConstants.API.SERVICE, description = "Service that incident belongs to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> service;

    @Parameter(names = "--" + ClientConstants.API.MESSAGE, description = "Incident text.", variableArity = true, splitter = NullSplitter.class)
    private List<String> message;

    @Parameter(names = "--" + ClientConstants.API.DESCRIPTION, description = "Detailed incident information.", variableArity = true, splitter = NullSplitter.class)
    private List<String> description;

    @Parameter(names = "--" + ClientConstants.API.STATUS, description = "Status of the incident.", variableArity = true, splitter = NullSplitter.class)
    private String status;

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    public CreateIncidentCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "createIncident";
    }

    @Override
    protected void doExecute(IClient client) throws Exception {
        CreateIncidentRequest request = new CreateIncidentRequest();
        request.setApiKey(commonOptions.getApiKey());
        if (service != null) request.setService(Strings.join(service, " "));
        if (message != null) request.setMessage(Strings.join(message, " "));
        if (description != null) request.setDescription(Strings.join(description, " "));
        if (status != null) request.setStatus(Status.findByKey(status));
        CreateIncidentResponse response = ((IStatusSirenClient) client).createIncident(request);
        System.out.println("incidentId: " + response.getIncidentId());
    }
}
