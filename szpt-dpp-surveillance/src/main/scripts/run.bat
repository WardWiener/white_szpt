rem @echo off

title surveillance

rem 更改编码为utf8
chcp 65001  

set "CURRENT_DIR=%cd%"
if not "%SZPT_SURVEILLANCE_HOME%" == "" goto gotHome
if exist "%SZPT_SURVEILLANCE_HOME%\bin\run.bat" goto okHome
rem 假定当前目录是安装目录下的bin目录
cd ..
set "SZPT_SURVEILLANCE_HOME=%cd%"

cd "%CURRENT_DIR%"
:gotHome
if exist "%SZPT_SURVEILLANCE_HOME%\bin\run.bat" goto okHome
echo The SZPT_SURVEILLANCE_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

cd "%SZPT_SURVEILLANCE_HOME%"

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
for /f "delims=" %%a in ('dir /b "%SZPT_SURVEILLANCE_HOME%\lib\*.jar"') do (
  if "!CLASSPATH!" == "" set "CLASSPATH=!SZPT_SURVEILLANCE_HOME!\lib\%%a
  if not "!CLASSPATH!" == "" set "CLASSPATH=!CLASSPATH!;!SZPT_SURVEILLANCE_HOME!\lib\%%a"
)
set CLASSPATH=%CLASSPATH%;%SZPT_SURVEILLANCE_HOME%\classes

set EXECUTABLE=%_RUNJAVA% -Dfile.encoding=UTF-8 com.taiji.pubsec.szpt.dpp.surveillance.SurveillanceBootstrap
#set EXECUTABLE=%_RUNJAVA% -Dfile.encoding=UTF-8 -agentlib:jdwp=transport=dt_socket,address=8001,suspend=n,server=y com.taiji.pubsec.szpt.dpp.surveillance.SurveillanceBootstrap

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
