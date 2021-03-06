#!/bin/bash
if [ -z "${FILETP_HOME}" ]; then
  export SZPT_SURVEILLANCE_HOME="$(cd "`dirname "$0"`"/..; pwd)"
fi

pid_file=$SZPT_SURVEILLANCE_HOME/run_pid
PID=

if test -f "$pid_file"
then
	PID=`cat "$pid_file"`
	if kill -0 $PID > /dev/null 2> /dev/null
	then
	  echo "process pid('$PID') already runing"
	  exit 1
	else
	  echo "pid file exists but process('$PID') not run, good to go"
	fi	
fi	

# Find the java binary
if [ -n "${JAVA_HOME}" ]; then
  RUNNER="${JAVA_HOME}/bin/java"
else
  if [ `command -v java` ]; then
    RUNNER="java"
  else
    echo "JAVA_HOME is not set" >&2
    exit 1
  fi
fi
CLASSPATH=$SZPT_SURVEILLANCE_HOME/classes
CLASSPATH=$CLASSPATH:$SZPT_SURVEILLANCE_HOME/classes/*
#echo $CLASSPATH
for JAR in `ls $SZPT_SURVEILLANCE_HOME/lib/*.jar`;do
  CLASSPATH=$CLASSPATH:$JAR
done
echo "------------------------------------"
export CLASSPATH
export
RUNNER="$JAVA_HOME/bin/java"
EXECUTABLE="$RUNNER -Dfile.encoding=UTF-8  com.taiji.pubsec.szpt.dpp.surveillance.SurveillanceBootstrap"
#EXECUTABLE="$RUNNER -agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=n -Dfile.encoding=UTF-8  com.taiji.pubsec.szpt.dpp.surveillance.SurveillanceBootstrap"
#echo $EXECUTABLE
cd ${SZPT_SURVEILLANCE_HOME}
echo ${SZPT_SURVEILLANCE_HOME}
exec $EXECUTABLE &
