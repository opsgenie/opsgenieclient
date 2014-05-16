package com.ifountain.opsgenie.client.cli.commands.statussiren;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.model.beans.ListIncident;
import com.ifountain.client.statussiren.model.incident.ListIncidentsRequest;
import com.ifountain.client.statussiren.model.incident.ListIncidentsResponse;
import com.ifountain.client.util.JsonUtils;
import com.ifountain.client.util.Strings;
import com.ifountain.opsgenie.client.cli.commands.BaseCommand;
import com.ifountain.opsgenie.client.cli.commands.CommonCommandOptions;
import com.ifountain.opsgenie.client.cli.commands.NullSplitter;

import java.util.List;

/**
 * @author Tuba Ozturk
 * @version 13.5.2014 10:31
 */
@Parameters(commandDescription = "Retrieves list of incidents from StatusSiren.")
public class ListIncidentsCommand extends BaseCommand {
    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    @Parameter(names = "--" + ClientConstants.API.SERVICE, description = "Service that incidents belongs to.", variableArity = true, splitter = NullSplitter.class)
    private List<String> service;

    @Parameter(names = "--" + ClientConstants.API.LIMIT, description = "Number of incidents to be retrieved. Default value is 20.")
    private int limit;

    @Parameter(names = "--" + ClientConstants.API.CREATED_AFTER, description = "If set, response contains incidents which created after specified time.")
    private long createdAfter;

    @Parameter(names = "--" + ClientConstants.API.CREATED_BEFORE, description = "If set, response contains incidents which created before specified time.")
    private long createdBefore;

    @Parameter(names = "--" + ClientConstants.API.ORDER, description = "Order of incidents to be retrieved. Can be asc or desc.")
    private String order;

    public ListIncidentsCommand(JCommander commander) {
        super(commander);
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }

    @Override
    public String getName() {
        return "listIncidents";
    }

    @Override
    protected void doExecute(IClient client) throws Exception {
        ListIncidentsRequest request = new ListIncidentsRequest();
        request.setApiKey(commonOptions.getApiKey());
        if(service != null) request.setService(Strings.join(service, " "));
        if(limit != 0) request.setLimit(limit);
        if(createdAfter != 0) request.setCreatedAfter(createdAfter);
        if(createdBefore != 0) request.setCreatedBefore(createdBefore);
        if(order != null) request.setOrder(ListIncidentsRequest.Order.valueOf(order));
        ListIncidentsResponse response = ((IStatusSirenClient) client).listIncidents(request);
        List<ListIncident> incidents = response.getIncidents();
        for(ListIncident incident: incidents){
            System.out.println(JsonUtils.toJson(incident.toMap()));
        }
    }
}
