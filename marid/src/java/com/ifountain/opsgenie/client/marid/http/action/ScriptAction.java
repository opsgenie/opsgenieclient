package com.ifountain.opsgenie.client.marid.http.action;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ifountain.opsgenie.client.marid.http.HTTPRequest;
import com.ifountain.opsgenie.client.marid.http.HTTPResponse;
import com.ifountain.opsgenie.client.marid.http.HttpController;
import com.ifountain.opsgenie.client.marid.http.RequestAction;
import com.ifountain.opsgenie.client.script.ScriptManager;
import com.ifountain.opsgenie.client.script.util.ScriptProxy;
import com.ifountain.opsgenie.client.util.JsonUtils;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/15/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptAction implements RequestAction {
    public static final String SCRIPT_NAME_PARAMETER = "scriptName";
    public static final String MARID_KEY_PARAMETER = "maridKey";

    @Override
    public HTTPResponse execute(HTTPRequest request) {
        HTTPResponse response = createDefaultHttpResponse();
        try {
            authenticate(request);
            String script = request.getParameter(SCRIPT_NAME_PARAMETER);
            Map<String, Object> params = new HashMap<String, Object>();
            params.putAll(request.getParameters());
            Map<String, Object> bindings = new HashMap<String, Object>();
            bindings.put(OpsGenieClientConstants.ScriptProxy.BINDING_OPSGENIE_CLIENT, new ScriptProxy(MaridConfig.getOpsGenieClient(), MaridConfig.getCustomerKey()));
            bindings.put(OpsGenieClientConstants.ScriptProxy.BINDING_PARAMS, params);
            bindings.put(OpsGenieClientConstants.ScriptProxy.BINDING_RESPONSE, response);
            bindings.put(OpsGenieClientConstants.ScriptProxy.BINDING_REQUEST, request);
            ScriptManager.getInstance().runScript(script, bindings);
        } catch (Throwable t) {
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("success", false);
            res.put("error", detailedMessage(t));
            response = createResponseAsJson(res, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private void authenticate(HTTPRequest request) throws Exception {
        String requestMaridKey = request.getParameters().remove(MARID_KEY_PARAMETER);
        if(!(MaridConfig.getMaridKey() == null || MaridConfig.getMaridKey().length() == 0 || MaridConfig.getMaridKey().equals(requestMaridKey))){
            throw new Exception("invalid "+MARID_KEY_PARAMETER);
        }
    }


    private HTTPResponse createDefaultHttpResponse() {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        return createResponseAsJson(res, HttpResponseStatus.OK);
    }

    private HTTPResponse createResponseAsJson(Map<String, Object> json, HttpResponseStatus status) {
        HTTPResponse response = new HTTPResponse();
        response.setStatus(status);
        try {
            byte[] contentBytes = JsonUtils.toJsonAsBytes(json);
            response.setContent(contentBytes);
            response.setContentType("application/json; charset=UTF-8");
        } catch (IOException ignored) {
        }
        return response;
    }

    private String detailedMessage(Throwable t) {
        if (t == null) {
            return "Unknown";
        }
        if (t.getCause() != null) {
            StringBuilder sb = new StringBuilder();
            while (t != null) {
                if (t.getMessage() != null) {
                    sb.append(t.getClass().getSimpleName()).append("[");
                    sb.append(t.getMessage());
                    sb.append("]");
                    sb.append("; ");
                }
                t = t.getCause();
                if (t != null) {
                    sb.append("nested: ");
                }
            }
            return sb.toString();
        } else {
            return t.getClass().getSimpleName() + "[" + t.getMessage() + "]";
        }
    }

    @Override
    public void register() throws Exception {
        HttpController.getInstance().register(this, "/script/{" + SCRIPT_NAME_PARAMETER + "}", HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST);
    }
}
