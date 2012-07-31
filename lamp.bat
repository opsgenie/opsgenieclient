@echo off

SETLOCAL

if NOT DEFINED JAVA_HOME goto err

set SCRIPT_DIR=%~dp0
for %%I in ("%SCRIPT_DIR%") do set LAMP_HOME=%%~dpfI

set JAVA_OPTS=%JAVA_OPTS% -Xmx128m
set JAVA_OPTS=%JAVA_OPTS% -Dlamphome="%LAMP_HOME%/"

set LAMP_CLASSPATH=%LAMP_CLASSPATH%;%LAMP_HOME%/lib/*;

"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp "%LAMP_CLASSPATH%" "com.ifountain.opsgenie.client.cli.OpsGenieCommandLine" %*
goto finally

:err
echo JAVA_HOME environment variable must be set!
goto finally

:finally
ENDLOCAL
