package com.ifountain.opsgenie.client.script;

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
        String START_HOUR = "startHour";
        String START_MINUTE = "startMinute";
        String END_HOUR = "endHour";
        String END_MINUTE = "endMinute";
        String BINDING_SOURCE = "source";
    }

    interface Marid {
        String SCRIPT_NAME_PARAMETER = "scriptName";
        String MARID_KEY_PARAMETER = "maridKey";
    }
}
