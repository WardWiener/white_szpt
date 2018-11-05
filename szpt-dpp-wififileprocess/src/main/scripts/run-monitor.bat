rem @echo off

set "CURRENT_DIR=%cd%"
if not "%WIFIFTP_HOME%" == "" goto gotHome
if exist "%WIFIFTP_HOME%\bin\run.bat" goto okHome
rem 假定当前目录是安装目录下的bin目录
cd ..
set "WIFIFTP_HOME=%cd%"

cd "%CURRENT_DIR%"
:gotHome
if exist "%WIFIFTP_HOME%\bin\run.bat" goto okHome
echo The WIFIFTP_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

cd "%WIFIFTP_HOME%"

if not "%JRE_HOME%" == "" goto gotJreHome
if not "%JAVA_HOME%" == "" goto gotJavaHome
echo Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
echo At least one of these environment variable is needed to run this program
goto exit

:gotJavaHome
rem No JRE given, use JAVA_HOME as JRE_HOME
set "JRE_HOME=%JAVA_HOME%"

:gotJreHome
rem Check if we have a usable JRE
if not exist "%JRE_HOME%\bin\java.exe" goto noJreHome
if not exist "%JRE_HOME%\bin\javaw.exe" goto noJreHome
goto okJava

:noJreHome
rem Needed at least a JRE
echo The JRE_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto exit

:okJava
set _RUNJAVA="%JRE_HOME%\bin\java"

setlocal enabledelayedexpansion
set CLASSPATH=.
for /f "delims=" %%a in ('dir /b "%WIFIFTP_HOME%\lib\*.jar"') do (
  if "!CLASSPATH!" == "" set "CLASSPATH=!WIFIFTP_HOME!\lib\%%a
  if not "!CLASSPATH!" == "" set "CLASSPATH=!CLASSPATH!;!WIFIFTP_HOME!\lib\%%a"
)
set CLASSPATH=%CLASSPATH%;%WIFIFTP_HOME%\classes

set EXECUTABLE=%_RUNJAVA% -Dfile.encoding=UTF-8 -classpath "%CLASSPATH%" com.taiji.pubsec.szpt.dpp.wififilerecv.Monitor

set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

call %EXECUTABLE% %CMD_LINE_ARGS%

goto end

:exit
exit /b 1

:end
exit /b 0
