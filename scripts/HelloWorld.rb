#Download latest JRuby Complete jar from http://jruby.org/download
#Put the jar file into lamp lib directory
$logger.warn("Parameter list from RUBY:");
$params.each do |paramName, paramValue|
   $logger.warn(paramName+":"+paramValue);
end
