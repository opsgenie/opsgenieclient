import java.util.concurrent.TimeUnit

/**
 * Created by ismet on 11/22/13.
 */

def today = System.currentTimeMillis()
def day = params["day"] != null ? params["day"] : "2"
def mode = params["mode"] != null ? params["mode"] : "close"
def test = params["test"] != null ? params["test"] : "false"
def name = params["name"] != null ? params["name"] : today
def status = params["status"] != null ? params["status"] : "open"

enum Actions {
    CLOSE,
    ACK,
    DELETE,
}

if (!(status.equals("open") || status.equals("closed")))
    status = "open"
if (!(mode.equals("delete")) && status.equals("closed")) {
    logger.warn("You cannot close|ack closed alerts. Status changed to OPEN")
    status = "open"
}

def testPrefix = test.equals("true") ? "[TEST MODE]" : ""
def statusPrefix = status.equals("open") ? "[OPEN]" : "[CLOSED]"
logger.warn(testPrefix + "<<<******Processing " + statusPrefix + " Alerts (" + name + ") ******>>>");
def dayLong = Long.parseLong(day)
logger.warn("[" + mode + "]Action will be run on alerts older than ${dayLong} days");
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
alerts = opsgenie.listAlerts(listParams)
if (alerts.size() == 100)
    logger.warn("alerts size: >" + alerts.size())
else
    logger.warn("alerts size: " + alerts.size())

if (test.equals("true"))
    logger.warn("[" + mode + "] Action will be run on alerts:")


for (alerts = opsgenie.listAlerts(listParams); alerts.size() > 0; alerts = opsgenie.listAlerts(listParams)) {
    lastCreatedAt = alerts[alerts.size() - 1].get("createdAt")
    listParams["createdBefore"] = lastCreatedAt
    alerts.each { alert ->
        if (test.equals("true"))
            logger.warn(alert.get("message"))
        else {
            Actions action = Actions.valueOf(mode.toUpperCase())
            switch (action) {
                case Actions.CLOSE:
                    logger.warn("Closing alert" + alert.get("message"))
                    opsgenie.closeAlert([id: alert.get("id")])
                    break
                case Actions.ACK:
                    logger.warn("Acknowledging alert" + alert.get("message"))
                    opsgenie.acknowledge([id: alert.get("id")])
                    break
                case Actions.DELETE:
                    logger.warn("Deleting alert" + alert.get("message"))
                    opsgenie.deleteAlert([id: alert.get("id")])
                    break
            }
        }
        count++
    }
}

logger.warn("${count} Alerts processed!")

