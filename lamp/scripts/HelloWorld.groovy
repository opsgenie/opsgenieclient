logger.warn("Parameter list:");
params.each{paramName, paramValue->
    logger.warn("${paramName}:${paramValue}");
}
