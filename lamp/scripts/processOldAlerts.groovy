import java.util.concurrent.TimeUnit

/*
Usage :

Default:  closes open alerts older than 2 days
lamp executeScript --name processOldAlerts.groovy

Day Param : closes open alerts older than 8 days
lamp executeScript --name processOldAlerts.groovy -Dday=8

Action Param : acknowledges open alerts older than 5 days
lamp executeScript --name processOldAlerts.groovy -Daction=ack -Dday=5

Status Param : deletes closed alerts older than 2 days
lamp executeScript --name processOldAlerts.groovy -Daction=ack -Dstatus=closed

Test Param : Lists open alerts older than 2 days but does not perform any actions
lamp executeScript --name processOldAlerts.groovy -Dtest=true
*/

def today = System.currentTimeMillis()
def day = params.day ? params.day : "2"
def action = params.action ? params.action : "close"
def test = params.test ? params.test : "false"
def logLabel = params.logLabel ? params.logLabel : today
def status = params.status ? params.status : "open"

def validActionList = ["ack","close","delete"];
def validStatusList = ["open","closed"];

//validations
def dayLong = 2;
try{
   dayLong = Long.parseLong(day);
}
catch(NumberFormatException ne){
    def message = "Can not parse day parameter, should be number, value is [$day]"
    logger.warn(message)
    throw new Exception(message);
}

if (!validActionList.contains(action))
{
    def message = "Invalid action parameter value:${action}, expected values:${validActionList}"
    logger.warn(message)
    throw new Exception(message);
}

if (!validStatusList.contains(status))
{
    def message = "Invalid status parameter value:${status}, expected values:${validStatusList}"
    logger.warn(message)
    throw new Exception(message);
}

if (action != "delete" && status == "closed") {
    def message = "You cannot close|ack closed alerts. Status changed to OPEN";
    logger.warn(message)
    throw new Exception(message);
}

//action starts
def logPrefix =  ( test == true ? "[TEST MODE]" : "")
logger.warn(logPrefix + "<<<******Processing ${status} Alerts (logLabel:${logLabel}) ******>>>");
logger.warn(logPrefix+"[${action}] Action will be run on ${status} alerts older than ${dayLong} days");
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
    logger.warn(logPrefix+" Got ${alerts.size} alerts")
    lastCreatedAt = alerts[alerts.size() - 1].get("createdAt")
    listParams["createdBefore"] = lastCreatedAt
    alerts.each { alert ->
        logger.warn(logPrefix+"running ${action} on alert ${alert.message}")
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

logger.warn("${count} Alerts processed!")
