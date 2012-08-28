package com.ifountain.opsgenie.client.script;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 8/27/12
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OpsgenieClientApplicationConstants {
    interface ScriptProxy {
        String BINDING_CONF = "conf";
        String BINDING_PARAMS = "params";
        String BINDING_ALERT = "alert";
        String BINDING_ACTION = "action";
        String BINDING_RESPONSE = "response";
        String BINDING_REQUEST = "request";
        String BINDING_OPSGENIE_CLIENT = "opsgenie";
        String BINDING_LOGGER = "logger";
        String INPUT_STREAM = "stream";
        String FILE_NAME = "fileName";
        String SUCCESS = "success";
    }

    interface Marid {
        String SCRIPT_NAME_PARAMETER = "scriptName";
        String MARID_KEY_PARAMETER = "maridKey";
    }
}
