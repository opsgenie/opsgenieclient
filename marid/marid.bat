@echo off

SETLOCAL

if NOT DEFINED JAVA_HOME goto err

set SCRIPT_DIR=%~dp0
for %%I in ("%SCRIPT_DIR%") do set MARID_HOME=%%~dpfI

set JAVA_OPTS=%JAVA_OPTS% -Xmx512m
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxPermSize=128m
set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError
set JAVA_OPTS=%JAVA_OPTS% -server
set JAVA_OPTS=%JAVA_OPTS% -Dmaridhome="%MARID_HOME%/"

set MARID_CLASSPATH=%MARID_CLASSPATH%;%MARID_HOME%/lib/*;

"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp "%MARID_CLASSPATH%" "com.ifountain.opsgenie.marid.Bootstrap" %*
goto finally

:err
echo JAVA_HOME environment variable must be set!
goto finally

:finally
ENDLOCAL
