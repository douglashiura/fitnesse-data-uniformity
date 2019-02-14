@echo off
SET root=%~dp0
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set dt=%%G

ECHO ==================================
ECHO root folder
ECHO %root%
ECHO arg0:	%0
ECHO arg1:	%1
ECHO arg2:	%2
ECHO arg3:	%3
ECHO current timestamp: %dt%

SET LDR_LOG_PATH="%root%..\..\..\..\log"
SET LDR_LOG="%LDR_LOG_PATH%\runworkflow.%dt%.log"

echo writting to log file
echo %LDR_LOG%
ECHO ==================================

ECHO ==================================		> %LDR_LOG%
ECHO root folder:%root%						>> %LDR_LOG%
ECHO current timestamp: dt					>> %LDR_LOG%
ECHO arg1:	%1								>> %LDR_LOG%
ECHO arg2:	%2								>> %LDR_LOG%
ECHO arg3:	%3								>> %LDR_LOG%
ECHO ==================================		>> %LDR_LOG%
