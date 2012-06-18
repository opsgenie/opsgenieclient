logger.warn("Creating alert with message ${params.message}");
def res = opsgenie.createAlert([message:params.message, recipients:["all"]]);
logger.warn("Alert is created with id :"+res.alertId);