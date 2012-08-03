@echo on

set LAMP_HOME=C:\tools\lamp

rem call :GET_TEMP_FILENAME
cmd /c %LAMP_HOME%\lamp.bat executeScript --name createAlert.groovy
