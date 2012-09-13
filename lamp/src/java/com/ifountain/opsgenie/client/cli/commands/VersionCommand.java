package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.Parameters;
import com.ifountain.opsgenie.client.IOpsGenieClient;
import com.ifountain.opsgenie.client.util.ManifestUtils;

import java.util.jar.Manifest;


@Parameters(commandDescription = "Prints lamp version")
public class VersionCommand implements Command{

    @Override
    public void printUsage() {
    }

    @Override
    public void execute(IOpsGenieClient opsGenieClient) throws Exception {
        Manifest maridManifest = ManifestUtils.loadManifest(VersionCommand.class);
        String version = maridManifest.getMainAttributes().getValue("Implementation-Version");
        if(version == null){
            version = "UNKNOWN";
        }
        System.out.println("lamp version \""+version+"\"");
    }

    @Override
    public void setCustomerKey(String customerKey) {
    }

    @Override
    public void setUser(String user) {
    }


    @Override
    public String getName() {
        return "version";
    }
}
