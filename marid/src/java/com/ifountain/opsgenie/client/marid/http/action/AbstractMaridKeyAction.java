package com.ifountain.opsgenie.client.marid.http.action;

import com.ifountain.opsgenie.client.marid.MaridConfig;
import com.ifountain.opsgenie.client.marid.http.HTTPRequest;
import com.ifountain.opsgenie.client.marid.http.HTTPResponse;
import com.ifountain.opsgenie.client.marid.http.RequestAction;
import com.ifountain.opsgenie.client.script.OpsgenieClientApplicationConstants;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 8/28/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMaridKeyAction  implements RequestAction {
    @Override
    public HTTPResponse execute(HTTPRequest request) {
        try {
            authenticate(request);
            return  doExecute(request);
        } catch (Throwable t) {
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("success", false);
            res.put("error", HttpActionUtils.detailedMessage(t));
            return HttpActionUtils.createResponseAsJson(res, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected abstract HTTPResponse doExecute(HTTPRequest request) throws Exception;

    private void authenticate(HTTPRequest request) throws Exception {
        String requestMaridKey = request.getParameter(OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER);
        if(!(MaridConfig.getInstance().getMaridKey() == null || MaridConfig.getInstance().getMaridKey().length() == 0 || MaridConfig.getInstance().getMaridKey().equals(requestMaridKey))){
            throw new Exception("invalid "+OpsgenieClientApplicationConstants.Marid.MARID_KEY_PARAMETER);
        }
    }
}
