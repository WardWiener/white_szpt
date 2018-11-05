#!/bin/bash
if [ -z "${SZPT_SURVEILLANCE_HOME}" ]; then
  export SZPT_SURVEILLANCE_HOME="$(cd "`dirname "$0"`"/..; pwd)"
fi

pid_file=$SZPT_SURVEILLANCE_HOME/run_pid
PID=

if test -f "$pid_file"
then
	PID=`cat "$pid_file"`
	if kill -0 $PID > /dev/null 2> /dev/null
	then
	  echo "process pid('$PID') is runing, start to kill it"
	  kill -9 $PID
	  exit 1
	else
	  echo "pid file exists but process('$PID') not run"
	fi	
fi	

