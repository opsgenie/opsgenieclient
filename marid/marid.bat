@echo off

SETLOCAL

if NOT DEFINED JAVA_HOME goto err

set SCRIPT_DIR=%~dp0
for %%I in ("%SCRIPT_DIR%") do set MARID_HOME=%%~dpfI

set JAVA_OPTS=%JAVA_OPTS% -Xmx512m
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxPermSize=128m
set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError
set JAVA_OPTS=%JAVA_OPTS% -server
set JAVA_OPTS=%JAVA_OPTS% -Dmarid.conf.dir="%MARID_HOME%/conf/"
set JAVA_OPTS=%JAVA_OPTS% -Dmarid.logs.dir="%MARID_HOME%/logs/"
set JAVA_OPTS=%JAVA_OPTS% -Dmarid.scripts.dir="%MARID_HOME%/scripts/"

set MARID_CLASSPATH=%MARID_CLASSPATH%;%MARID_HOME%/lib/*;

"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp "%MARID_CLASSPATH%" "com.ifountain.opsgenie.client.marid.Bootstrap" %*
goto finally

:err
echo JAVA_HOME environment variable must be set!
goto finally

:finally
ENDLOCAL
