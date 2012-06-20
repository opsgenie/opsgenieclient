$logger.warn("Parameter list from RUBY:");
$params.each do |paramName, paramValue|
   $logger.warn(paramName+":"+paramValue);
end

alertProps = {}
alertProps["message"] = $params["nameOfSearch"] + " " + $params["numberOfEvents"]
alertProps["recipients"] = "Operations"
alertProps["details"] = {}
alertProps["details"]["browserUrl"] = $params["browserUrl"]
alertProps["details"]["queryString"] = $params["queryString"]
alertProps["details"]["searchTerms"] = $params["searchTerms"]
alertProps["details"]["numberOfEvents"] = $params["numberOfEvents"]
alertProps["details"]["nameOfSearch"] = $params["nameOfSearch"]

response = $opsgenie.createAlert(alertProps)
$logger.warn("Alert is created with id #{response["alertId"]}")