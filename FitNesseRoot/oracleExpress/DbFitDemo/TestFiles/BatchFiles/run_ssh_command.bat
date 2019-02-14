@echo off

::This file should be located in .\fitnesse.server\FitNesseRoot\DbFitDemo\TestFiles\BatchFiles\
::Plink file should be located in .\fitnesse.server\utils\
::Private Key file should be located in .\fitnesse.server\utils\
SET pathxyz=%~dp0..\..\..\..\utils\
echo "%~1"
IF "%~1"=="" goto NoParam

SET "command=%~1"
IF "%command:~0,2%"=="-m" (
set command=%command%
echo 1
) ELSE (
set command="%command%"
echo 2
)

ECHO SSH Connection Start
ECHO ==================================
call %PLINK_PATH% -ssh %SSH_CONN_STR% -i %pathxyz%private_key.ppk %command%
SET IRESULT=%ERRORLEVEL%

ECHO ==================================
ECHO SSH Connection End

exit /b %IRESULT%

:NoParam
Echo Missing parameters!!!
exit /b 1