package com.ifountain.opsgenie.client.cli.commands;

import com.beust.jcommander.Parameter;
import com.ifountain.client.ClientConstants;
import com.ifountain.client.util.Strings;

import java.util.List;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/4/12
 * Time: 9:17 AM
 */
public class CommonCommandOptions {
    @Parameter(names = "--" + ClientConstants.API.API_KEY, description = "Api key used for authenticating API requests.", splitter = NullSplitter.class)
    private String apiKey;

    @Parameter(names = "--" + ClientConstants.API.CUSTOMER_KEY, description = "Api key used for authenticating API requests.", splitter = NullSplitter.class, hidden = true, required = false)
    private String customerKey;

    @Parameter(names = "--" + ClientConstants.API.USER, description = "Owner of the execution.", splitter = NullSplitter.class, variableArity = true)
    private List<String> user;

    @Parameter(names = "--" + ClientConstants.API.HELP, description = "Displays usage for this command.")
    private boolean isHelp = false;

    public String getApiKey() {
        if(customerKey != null){
            return customerKey;
        }
        return apiKey;
    }

    public boolean isHelp() {
        return isHelp;
    }

    public String getUser() {
        if(user == null){
            return null;
        }
        else{
            return Strings.join(user, " ");
        }
    }
    public List<String> getUserList() {
        return user;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }
}
