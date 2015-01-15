package com.ifountain.opsgenie.client.marid.http.action;

import com.ifountain.opsgenie.client.marid.alert.AlertActionUtils;
import com.ifountain.opsgenie.client.marid.alert.NoActionSpecifiedException;
import com.ifountain.opsgenie.client.marid.http.HTTPRequest;
import com.ifountain.opsgenie.client.marid.http.HTTPResponse;
import com.ifountain.opsgenie.client.marid.http.HttpController;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/15/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlertAction extends AbstractMaridKeyAction {
    @Override
    public HTTPResponse doExecute(HTTPRequest request) throws Exception {
        HTTPResponse response = HttpActionUtils.createDefaultHttpResponse();
        String jsonContent = request.getContent();
        try {
            AlertActionUtils.AlertActionBean bean = AlertActionUtils.AlertActionBean.createAlertAction(jsonContent);
            AlertActionUtils.executeActionScript(bean);
        } catch (IllegalArgumentException e) {
            if (e instanceof NoActionSpecifiedException || e.getCause() instanceof NoActionSpecifiedException) {
                Map<String, Object> res = new HashMap<String, Object>();
                res.put("success", true);
                res.put("message", e.getMessage());
                response = HttpActionUtils.createResponseAsJson(res, HttpResponseStatus.OK);
            } else {
                throw e;
            }
        }
        return response;
    }

    @Override
    public void register() throws Exception {
        HttpController.getInstance().register(this, "/alert/action", HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST);
    }
}
