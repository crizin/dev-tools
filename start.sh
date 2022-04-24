#!/usr/bin/env bash

./gradlew clean bootJar
./stop.sh
rm nohup.out 2> /dev/null
nohup java -XX:+UseZGC -Xms32m -Xmx32m -jar build/libs/dev-tools-0.0.1-SNAPSHOT.jar &
sleep 1
tail -f nohup.out
