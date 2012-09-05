package com.ifountain.opsgenie.client.marid.http.action;

import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ifountain.opsgenie.client.marid.http.HTTPRequest;
import com.ifountain.opsgenie.client.marid.http.HTTPResponse;
import com.ifountain.opsgenie.client.marid.http.HttpController;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.script.util.ScriptProxy;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/15/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptAction extends AbstractMaridKeyAction{

    @Override
    public HTTPResponse doExecute(HTTPRequest request) throws Exception {
        ScriptResponseVariable scriptResponseVariable = new ScriptResponseVariable();
        String script = request.getParameter(OpsgenieClientApplicationConstants.Marid.SCRIPT_NAME_PARAMETER);
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(request.getParameters());
        params.remove(OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER);
        Map<String, Object> bindings = new HashMap<String, Object>();
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT, new ScriptProxy(MaridConfig.getInstance().getOpsGenieClient(), MaridConfig.getInstance().getCustomerKey()));
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_PARAMS, params);
        Properties maridConfProps = new Properties();
        if(MaridConfig.getInstance().getConfiguration() != null){
            maridConfProps.putAll(MaridConfig.getInstance().getConfiguration());
        }
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_CONF, maridConfProps);
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_RESPONSE, scriptResponseVariable);
        bindings.put(OpsgenieClientApplicationConstants.ScriptProxy.BINDING_REQUEST, request);
        ScriptManager.getInstance().runScript(script, bindings);
        HTTPResponse httpResponse = HttpActionUtils.createDefaultHttpResponse();
        if(scriptResponseVariable.content != null){
            httpResponse.setContent(scriptResponseVariable.content);
            if(scriptResponseVariable.contentType != null){
                httpResponse.setContentType(scriptResponseVariable.contentType);
            }
            else{
                httpResponse.setContentType("application/json; charset=UTF-8");
            }
        }
        if(scriptResponseVariable.status != -1){
            httpResponse.setStatus(HttpResponseStatus.valueOf(scriptResponseVariable.status));
        }
        return httpResponse;
    }

    @Override
    public void register() throws Exception {
        HttpController.getInstance().register(this, "/script/{" + OpsgenieClientApplicationConstants.Marid.SCRIPT_NAME_PARAMETER + "}", HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST);
    }
    
    public static class ScriptResponseVariable{
        public String content = null;
        public int status = -1;
        public String contentType = null;

    }
}
