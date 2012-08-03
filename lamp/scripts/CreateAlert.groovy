logger.warn("Creating alert with message ${params["message"]}");
alertprops = {}
alertprops["message"] = params["message"];
alertprops["recipients"] = "all"
def response = opsgenie.createAlert(alertprops);
logger.warn("Alert is created with id ${response["alertId"]}")
