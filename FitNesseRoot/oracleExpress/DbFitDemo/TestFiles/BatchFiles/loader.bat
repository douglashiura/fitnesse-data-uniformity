@echo off
:: load config parameters

SET root=%~dp0
ECHO root folder %root%
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set dt=%%G
SET LDR_CTRL="%root%loader_control.auto.txt"
SET LDR_DAT="%root%data.txt"
SET LDR_LOG_PATH="%root%root%\..\..\..\..\..\log"
SET LDR_LOG="%LDR_LOG_PATH%\load.%dt%.log"

if not exist "%LDR_LOG_PATH%" mkdir %LDR_LOG_PATH%

echo autogenerating loader control file for dynamic file path
echo ctrl file: %LDR_CTRL%
echo data file: %LDR_DAT%
echo log file:  %LDR_LOG%

echo load data> %LDR_CTRL%
echo		infile %LDR_DAT%>> %LDR_CTRL%
echo		into table emp>> %LDR_CTRL%
echo		fields terminated by ",">> %LDR_CTRL%
echo		(id,name)>> %LDR_CTRL%


sqlldr %CONN_STR% control='%LDR_CTRL%', LOG='%LDR_LOG%'
