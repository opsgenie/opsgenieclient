package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.Parameter;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:17 AM
 */
public class CommonCommandOptions {
    @Parameter(names = "--" + OpsGenieClientConstants.API.CUSTOMER_KEY, description = "Customer key used for authenticating API requests.", splitter = NullSplitter.class)
    private String customerKey;

    @Parameter(names = "--" + OpsGenieClientConstants.API.USER, description = "Owner of the execution.", splitter = NullSplitter.class)
    private List<String> user;

    @Parameter(names = "--" + OpsGenieClientConstants.API.HELP, description = "Displays usage for this command.")
    private boolean isHelp = false;

    public String getCustomerKey() {
        return customerKey;
    }

    public boolean isHelp() {
        return isHelp;
    }

    public List<String> getUser() {
        return user;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }
}
