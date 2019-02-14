@echo off
SET root=%~dp0
ECHO root folder %root%
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set dt=%%G

SET LDR_LOG_PATH="%root%..\..\..\..\log"
SET LDR_LOG="%LDR_LOG_PATH%\runworkflow.%dt%.log"


echo autogenerating loader control file for dynamic file path
echo into %LDR_LOG_PATH%
echo and %LDR_LOG%

echo load data> %LDR_LOG%
echo	arg1: $1 >> %LDR_LOG%
echo	arg2: $2 >> %LDR_LOG%
