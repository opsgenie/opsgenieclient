package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.model.HeartbeatRequest;
import com.ifountain.opsgenie.client.model.HeartbeatResponse;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:46 AM
 */
@Parameters(commandDescription = "Sends heartbeat to Opsgenie.")
public class HeartbeatCommand extends BaseCommand{
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
        request.setCustomerKey(commonOptions.getCustomerKey());
        HeartbeatResponse response = opsGenieClient.heartbeat(request);
        System.out.println("heartbeat=" + response.getHeartbeat());
    }

    @Override
    protected boolean isHelp() {
        return commonOptions.isHelp();
    }
}
