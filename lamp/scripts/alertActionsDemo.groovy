/*
opsgenie is a variable to easily execute alert actions on OpsGenie
Detailed documentation available on : http://support.opsgenie.com/customer/portal/articles/1404981-script-proxy-for-lamp-marid
opsgenie is an instance of class com.ifountain.opsgenie.client.script.util.ScriptProxy
Source code at : https://github.com/opsgenie/opsgenieclient/blob/master/common/src/java/com/ifountain/opsgenie/client/script/util/ScriptProxy.java
 */

def user = "username@yourdomain.com";

def response = opsgenie.createAlert([
        "message": "DemoAlert_${new Date()}",
        "recipients": [user],
        "actions": ["ping"],
        "tags": ["demo","sample"],
        "alias":"demo"
]);
def alertId = response.id;
logger.warn("Alert is created with id ${alertId}");

opsgenie.acknowledge(["id":alertId, "user":user])

opsgenie.addRecipient(["id":alertId, "recipient":"newRecipient@yourdomain.com"])

opsgenie.assign(["id":alertId, "owner":"otherUser@yourdomain.com"])

opsgenie.renotify(["id":alertId,"recipients":["user1@xyz.com", "group1"] ]);

opsgenie.takeOwnership(["id":alertId, "user":user])

opsgenie.addNote(["id":alertId, "note":"Sample Note"]);

opsgenie.executeAlertAction(["id":alertId, "action":"ping"])

opsgenie.closeAlert(["id":alertId])

def alertDetails = opsgenie.getAlert(["id":alertId]);
logger.warn("Alert Details from OpsGenie : ${alertDetails}");

def alertLogs = opsgenie.listAlertLogs(["id":alertId])
alertLogs.logs.each{ alertLog ->
    logger.warn("Alert Log from OpsGenie : ${alertLog}")
}

def alertRecipients = opsgenie.listAlertRecipients(["id":alertId])
logger.warn("Alert Recipients from OpsGenie : ${alertRecipients}")

//Deletion commented out
//opsgenie.deleteAlert(["id":alertId])

opsgenie.listAlerts(["limit":10]).each{ alert ->
    logger.warn("Found alert ${alert.message}");
}







