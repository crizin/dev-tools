#!/usr/bin/env bash

kill -15 "$(jps|grep cozo|awk '{print $1}')" 2> /dev/null
