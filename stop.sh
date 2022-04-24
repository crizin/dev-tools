#!/usr/bin/env bash

kill -15 "$(jps|grep dev-tools|awk '{print $1}')" 2> /dev/null
