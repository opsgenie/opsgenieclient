package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.alertpolicy.EnableAlertPolicyRequest;
import com.ifountain.opsgenie.client.model.integration.EnableIntegrationRequest;
import com.ifountain.opsgenie.client.util.Strings;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:46 AM
 */
@Parameters(commandDescription = "Disables Opsgenie Integration and Policy.")
public class DisableCommand extends EnableCommand{

    public DisableCommand(JCommander commander) {
        super(commander);
    }

    @Override
    protected boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return "disable";
    }
}
