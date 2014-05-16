package com.ifountain.opsgenie.client.cli.script;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.IClient;
import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.client.model.BaseRequest;
import com.ifountain.opsgenie.client.script.util.ScriptBridgeUtils;
import com.ifountain.opsgenie.client.script.util.ScriptProxy;

import java.util.Map;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/3/12 5:54 PM
 */
public class LampScriptProxy extends ScriptProxy {
    private String user;

    public LampScriptProxy(IOpsGenieClient opsGenieClient, String apiKey, String user) {
        super(opsGenieClient, apiKey);
        this.user = user;
    }

    @Override
    protected void populateCommonProps(BaseRequest request, Map params) {
        super.populateCommonProps(request, params);
        String usr = ScriptBridgeUtils.getAsString(params, ClientConstants.API.USER);
        if (usr == null) {
            params.put(ClientConstants.API.USER, user);
        }
    }
}
