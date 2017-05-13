@ECHO off
SET appname=Lolita
SET dependant=HttpClient

REM search the executable file (java byte code)
SET executable_name=%appname%.class
SET check_exe=exe_data.txt
DIR | FINDSTR %executable_name% > %check_exe%
FOR %%F IN (%check_exe%) DO IF %%~zF==0 goto Compile

REM check the executable's time stanp
SET return_label=Dependant
SET FILE1=%appname%.class
SET FILE2=%appname%.java
GOTO Compare
:Dependant
SET return_label=Execute
SET FILE1=%dependant%.class
SET FILE2=%dependant%.java
GOTO Compare


:Execute
java %appname%

:CleanUp
DEL %check_exe% %check_src%
GOTO End




REM subroutine

:Compile
javac -deprecation %appname%.java
goto Execute

: Compare
SET TIMESTAMP1=""
SET TIMESTAMP2=""

REM get the last edited time
FOR %%a IN (%FILE1%) DO SET TIMESTAMP1=%%~ta
FOR %%b IN (%FILE2%) DO SET TIMESTAMP2=%%~tb


REM compare the time stanps
IF "%TIMESTAMP1%" == "%TIMESTAMP2%" (
    REM same
    GOTO %return_label%
) ELSE (
    IF "%TIMESTAMP1%" GTR "%TIMESTAMP2%" (
        REM executable file is newer
        GOTO %return_label%
    ) ELSE (
        REM source file is newer
        GOTO Compile
    )
)

:End