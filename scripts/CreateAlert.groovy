logger.warn("Creating alert with message ${params}");

def alertProps = [:]
alertProps.message = params.nameOfSearch + " has " + params.numberOfEvents + " events"
alertProps.recipients = "Operations"
alertProps.details = [:]
alertProps.details.searchTerms = params.searchTerms
alertProps.details.browserUrl = params.browserUrl
alertProps.details.queryString = params.queryString
alertProps.details.numberOfEvents = params.numberOfEvents
alertProps.details.nameOfSearch = params.nameOfSearch

def response = opsgenie.createAlert(alertProps)
logger.warn("Alert is created with id :"+response.alertId);