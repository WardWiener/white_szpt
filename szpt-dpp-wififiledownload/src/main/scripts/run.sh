#!/bin/bash
if [ -z "${WIFIFTP_HOME}" ]; then
  export WIFIFTP_HOME="$(cd "`dirname "$0"`"/..; pwd)"
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
CLASSPATH=$WIFIFTP_HOME/classes
CLASSPATH=$CLASSPATH:$WIFIFTP_HOME/classes/*
#echo $CLASSPATH
for JAR in `ls $WIFIFTP_HOME/lib/*.jar`;do
  CLASSPATH=$CLASSPATH:$JAR
done
echo "------------------------------------"
export CLASSPATH
export
RUNNER="$JAVA_HOME/bin/java"
EXECUTABLE="$RUNNER -Dfile.encoding=UTF-8 com.taiji.pubsec.szpt.dpp.wififilerecv.Bootstrap"
#echo $EXECUTABLE
cd ${WIFIFTP_HOME}
echo ${WIFIFTP_HOME}
exec $EXECUTABLE 
