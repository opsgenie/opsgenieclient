package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;

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
