import java.util.concurrent.TimeUnit

/*
Usage :

Default:  closes open alerts older than 2 days
lamp executeScript --name processOldAlerts.groovy

Test Param : Lists open alerts older than 2 days but does not perform any actions
lamp executeScript --name processOldAlerts.groovy -Dtest=true

Day Param : closes open alerts older than 8 days
lamp executeScript --name processOldAlerts.groovy -Dday=8

Action Param : acknowledges open alerts older than 5 days
lamp executeScript --name processOldAlerts.groovy -Daction=ack -Dday=5

Status Param : deletes closed alerts older than 2 days
lamp executeScript --name processOldAlerts.groovy -Daction=delete -Dstatus=closed

*/

def today = System.currentTimeMillis()
def day = params.day ? params.day : "2"
def action = params.action ? params.action : "close"
def test = params.test ? params.test : "false"
def logLabel = params.logLabel ? params.logLabel : today
def status = params.status ? params.status : "open"

def validActionList = ["ack","close","delete"];
def validStatusList = ["open","closed"];
def actionLabels = ["ack":"acking","close":"closing","delete":"deleting"];

logPrefix =  ( test != "true" ? "[ProcessOldAlerts] " : "[ProcessOldAlerts-TestMode] ")
consolePrefix = ( test != "true" ? "" : "[TestMode] ")

//validations
def dayLong = 2;
try{
   dayLong = Long.parseLong(day);
}
catch(NumberFormatException ne){
    def message = "Can not parse day parameter, should be number, value is [$day]"
    logWarn(message);
    throw new Exception(message);
}

if (!validActionList.contains(action))
{
    def message = "Invalid action parameter value:${action}, expected values:${validActionList}"
    logWarn(message);
    throw new Exception(message);
}

if (!validStatusList.contains(status))
{
    def message = "Invalid status parameter value:${status}, expected values:${validStatusList}"
    logWarn(message);
    throw new Exception(message);
}

if (action != "delete" && status == "closed") {
    def message = "You cannot close|ack closed alerts";
    logWarn(message);
    throw new Exception(message);
}

//action starts
logWarn("<<<***************Processing ${status} Alerts (logLabel:${logLabel}) ******>>>");
logWarn("[${action}] Action will be run on ${status} alerts older than ${dayLong} days");
def twoday = TimeUnit.MILLISECONDS.convert(dayLong, TimeUnit.DAYS)
def dayBefore = today - twoday

dayBefore = TimeUnit.NANOSECONDS.convert(dayBefore, TimeUnit.MILLISECONDS)
listParams = [:]
listParams["createdBefore"] = (String) dayBefore;

listParams["status"] = status
listParams["limit"] = 100;
def lastCreatedAt;
def alerts
def count = 0

for (alerts = opsgenie.listAlerts(listParams); alerts.size() > 0; alerts = opsgenie.listAlerts(listParams)) {
    logWarn("***************Got ${alerts.size} alerts")
    lastCreatedAt = alerts[alerts.size() - 1].get("createdAt")
    listParams["createdBefore"] = lastCreatedAt
    alerts.each { alert ->
        def actionLabel = actionLabels[action];
        if(!actionLabel) actionLabel = action;
        logWarn("${actionLabel} alert [${alert.message}] id:[${alert.id}]")
        if (test != "true"){
            switch (action) {
                case "close":
                    opsgenie.closeAlert([id: alert.id])
                    break
                case "ack":
                    opsgenie.acknowledge([id: alert.id])
                    break
                case "delete":
                    opsgenie.deleteAlert([id: alert.id])
                    break
            }
        }
        count++
    }
}

logWarn("${count} Alerts processed!")

def logWarn(message){
    logger.warn(logPrefix+message);
    println consolePrefix+message;
}
