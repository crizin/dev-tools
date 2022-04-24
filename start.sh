#!/usr/bin/env bash

./gradlew clean bootJar
./stop.sh
rm nohup.out 2> /dev/null
nohup java -XX:+UseZGC -Xms128m -Xmx512m -jar build/libs/cozo-0.0.1-SNAPSHOT.jar &
sleep 1
tail -f nohup.out
