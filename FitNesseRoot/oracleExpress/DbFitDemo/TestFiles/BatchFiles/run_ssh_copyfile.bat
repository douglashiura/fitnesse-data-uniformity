@echo off

::This file should be located in .\fitnesse.server\FitNesseRoot\DbFitDemo\TestFiles\BatchFiles\
::pscp file should be located in .\fitnesse.server\utils\
::Private Key file should be located in .\fitnesse.server\utils\
SET pathxyz=%~dp0..\..\..\..\utils\

IF "%~1"=="" goto NoParam
IF "%~2"=="" goto NoParam
IF "%~3"=="" goto NoParam


IF "%~1"=="1" goto CopyFrom
IF "%~1"=="2" (
goto CopyTo
) ELSE (
goto WrongParam
)

:NoParam
Echo Missing parameters!!!
exit /b 1

:WrongParam
Echo Wrong parameters (only 1 and 2 are supported)!!!
exit /b 99

:CopyFrom

ECHO SSH Connection Start
ECHO ==================================
call %PSCP_PATH% -i %pathxyz%private_key.ppk %SSH_CONN_STR%:%~2 %~3
SET IRESULT=%ERRORLEVEL%

ECHO ==================================
ECHO SSH Connection End

exit /b %IRESULT%

:CopyTo

ECHO SSH Connection Start
ECHO ==================================
call %PSCP_PATH% -i %pathxyz%private_key.ppk %~2 %SSH_CONN_STR%:%~3
SET IRESULT=%ERRORLEVEL%

ECHO ==================================
ECHO SSH Connection End

exit /b %IRESULT%