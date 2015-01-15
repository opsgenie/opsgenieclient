package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.customer.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.customer.HeartbeatResponse;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:46 AM
 */
@Parameters(commandDescription = "Sends heartbeat to Opsgenie.")
public class HeartbeatCommand extends BaseCommand{
    @Parameter(names = "--" + OpsGenieClientConstants.API.SOURCE, description = "Deprecated use --name paramater.", variableArity = true, splitter = NullSplitter.class)
    private List<String> source;

    @Parameter(names = "--" + OpsGenieClientConstants.API.NAME, description = "Name of action.", variableArity = true, splitter = NullSplitter.class)
    private List<String> name;

    @ParametersDelegate
    private CommonCommandOptions commonOptions = new CommonCommandOptions();

    public HeartbeatCommand(JCommander commander) {
        super(commander);
    }

    @Override
    public String getName() {
        return "heartbeat";
    }

    @Override
    public void doExecute(IOpsGenieClient opsGenieClient) throws Exception {
        HeartbeatRequest request = new HeartbeatRequest();
        request.setApiKey(commonOptions.getApiKey());
        if (source != null) request.setName(Strings.join(source, " "));
        if (name != null) request.setName(Strings.join(name, " "));
        HeartbeatResponse response = opsGenieClient.heartbeat(request);
        System.out.println("heartbeat=" + response.getHeartbeat());
    }

    @Override
    protected CommonCommandOptions getCommonCommandOptions() {
        return commonOptions;
    }
}
