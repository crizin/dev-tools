#!/usr/bin/env bash

./gradlew clean bootJar

docker build . -t dev-tools