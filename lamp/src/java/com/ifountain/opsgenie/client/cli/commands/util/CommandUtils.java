package com.ifountain.opsgenie.client.cli.commands.util;

import com.ifountain.client.opsgenie.IOpsGenieClient;
import com.ifountain.client.opsgenie.OpsGenieClient;
import com.ifountain.client.statussiren.IStatusSirenClient;
import com.ifountain.client.statussiren.StatusSirenClient;
import com.ifountain.client.util.ClientConfiguration;
import com.ifountain.client.util.ClientProxyConfiguration;
import com.ifountain.opsgenie.client.cli.LampConfig;
import com.ifountain.opsgenie.client.cli.OpsGenieCommandLine;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * @author Tuba Ozturk
 * @version 15.5.2014 18:08
 */
public class CommandUtils {

    public static IOpsGenieClient createOpsGenieClient(ClientConfiguration clientConfiguration) {
        OpsGenieClient opsGenieClient = new OpsGenieClient(clientConfiguration);
        if (LampConfig.getInstance().getConfiguration().containsKey("opsgenie.api.url")) {
            opsGenieClient.setRootUri(LampConfig.getInstance().getConfiguration().getProperty("opsgenie.api.url"));
        }
        return opsGenieClient;
    }

    public static IStatusSirenClient createStatusSirenClient (ClientConfiguration clientConfiguration) {
        StatusSirenClient statusSirenClient = new StatusSirenClient(clientConfiguration);
        if (LampConfig.getInstance().getConfiguration().containsKey("statussiren.api.url")) {
            statusSirenClient.setRootUri(LampConfig.getInstance().getConfiguration().getProperty("statussiren.api.url"));
        }
        return statusSirenClient;
    }

    public static ClientConfiguration configureClient() {
        ClientConfiguration clientConfig = new ClientConfiguration();

        if (LampConfig.getInstance().getConfiguration().containsKey("proxyHost") && LampConfig.getInstance().getConfiguration().containsKey("proxyPort")) {
            String host = LampConfig.getInstance().getConfiguration().getProperty("proxyHost");
            int port = Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("proxyPort"));
            ClientProxyConfiguration clientProxyConfiguration = new ClientProxyConfiguration(host, port);
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyUsername")) {
                clientProxyConfiguration.setProxyUsername(LampConfig.getInstance().getConfiguration().getProperty("proxyUsername"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyPassword")) {
                clientProxyConfiguration.setProxyPassword(LampConfig.getInstance().getConfiguration().getProperty("proxyPassword"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyDomain")) {
                clientProxyConfiguration.setProxyDomain(LampConfig.getInstance().getConfiguration().getProperty("proxyDomain"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("authMethod")) {
                String authMethod = LampConfig.getInstance().getConfiguration().getProperty("authMethod", ClientProxyConfiguration.AuthType.NT.name());
                ClientProxyConfiguration.AuthType authTypeEnum;
                try{
                    authTypeEnum = ClientProxyConfiguration.AuthType.valueOf(authMethod);
                }catch (Throwable t){
                    throw new RuntimeException("Invalid authMethod ["+authMethod+"]");
                }
                clientProxyConfiguration.setAuthType(authTypeEnum);
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyWorkstation")) {
                clientProxyConfiguration.setProxyWorkstation(LampConfig.getInstance().getConfiguration().getProperty("proxyWorkstation"));
            }
            if (LampConfig.getInstance().getConfiguration().containsKey("proxyProtocol")) {
                clientProxyConfiguration.setProxyProtocol(LampConfig.getInstance().getConfiguration().getProperty("proxyProtocol"));
            }
            clientConfig.setClientProxyConfiguration(clientProxyConfiguration);
        }
        clientConfig.setSocketTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketTimeout", "30")) * 1000);
        clientConfig.setConnectionTimeout(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("connectionTimeout", "30")) * 1000);
        clientConfig.setMaxConnections(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("maxConnectionCount", "50")));
        if (LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint") != null) {
            clientConfig.setSocketReceiveBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketReceiveBufferSizeHint")));
        }
        if (LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint") != null) {
            clientConfig.setSocketSendBufferSizeHint(Integer.parseInt(LampConfig.getInstance().getConfiguration().getProperty("socketSendBufferSizeHint")));
        }
        clientConfig.setUserAgent(ClientConfiguration.createUserAgentFromManifest(OpsGenieCommandLine.class));
        return clientConfig;
    }
}
