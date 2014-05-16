package com.ifountain.opsgenie.client.cli.commands.statussiren;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.beans.Incident;
import com.ifountain.client.statussiren.model.beans.IncidentUpdate;
import com.ifountain.client.statussiren.model.incident.GetIncidentRequest;
import com.ifountain.client.statussiren.model.incident.GetIncidentResponse;
import com.ifountain.client.util.JsonUtils;
import com.ifountain.client.util.Strings;
import com.ifountain.opsgenie.client.cli.commands.BaseCommand;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.NullSplitter;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 13.5.2014 10:22
 */
@Parameters(commandDescription = "Retrieves specified incident with details from StatusSiren.")
public class GetIncidentCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + ClientConstants.API.SERVICE, description = "Service that incident belongs to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> service;

    @Parameter(names = "--" + ClientConstants.API.INCIDENT_ID, description = "Id of the incident that will be retrieved.", variableArity = true, splitter = NullSplitter.class)
    private long incidentId;

    public GetIncidentCommand(JCommander commander){
        super(commander);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public String getName() {
        return "getIncident";
    }

    @Override
    protected void doExecute(IClient client) throws Exception {
        GetIncidentRequest request = new GetIncidentRequest();
        request.setApiKey(commonOptions.getApiKey());
        request.setIncidentId(incidentId);
        if(service != null) request.setService(Strings.join(service," "));
        GetIncidentResponse response = ((IStatusSirenClient) client).getIncident(request);
//        Incident incident = response.getIncident();
//        System.out.println("Incident:");
//        System.out.println("id:" + incident.getId());
//        System.out.println("message " + incident.getMessage());
//        System.out.println("status: " + incident.getStatus());
//        System.out.println("state: " + incident.getState());
//        System.out.println("createdAt: " + incident.getCreatedAt());
//        System.out.println("updates:");
//        for(IncidentUpdate update : incident.getUpdates()){
//            System.out.println("message: " + update.getMessage() + " createdAt: " + update.getCreatedAt());
//        }
        System.out.println(JsonUtils.toJson(response.getIncident().toMap()));
    }
}
