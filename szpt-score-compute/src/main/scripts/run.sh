#!/bin/bash
if [ -z "${SCORECOMPUTE_HOME}" ]; then
  export SCORECOMPUTE_HOME="$(cd "`dirname "$0"`"/..; pwd)"
fi

pid_file=$SCORECOMPUTE_HOME/run_pid
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
CLASSPATH=$SCORECOMPUTE_HOME/classes
CLASSPATH=$CLASSPATH:$SCORECOMPUTE_HOME/classes/*
#echo $CLASSPATH
for JAR in `ls $SCORECOMPUTE_HOME/lib/*.jar`;do
  CLASSPATH=$CLASSPATH:$JAR
done
echo "------------------------------------"
export CLASSPATH
export
RUNNER="$JAVA_HOME/bin/java"
EXECUTABLE="$RUNNER -Dfile.encoding=UTF-8 com.taiji.pubsec.szpt.score.compute.ScoreComputeBootstrap"
#EXECUTABLE="$RUNNER -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n -Dfile.encoding=UTF-8 com.taiji.pubsec.szpt.score.compute.ScoreComputeBootstrap"
#echo $EXECUTABLE
cd ${SCORECOMPUTE_HOME}
echo ${SCORECOMPUTE_HOME}
exec $EXECUTABLE &
