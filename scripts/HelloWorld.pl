#Download latest bsfperl.jar from http://bsfperl.sourceforge.net
#Put the jar file into lamp lib directory
#add following configuration items to lamp config file
#   script.engines=perl
#   script.engine.perl.class=net.sourceforge.bsfperl.PerlEngineImpl
#   script.engine.perl.extensions=pl
$logger->warn("Hello World from PERL")